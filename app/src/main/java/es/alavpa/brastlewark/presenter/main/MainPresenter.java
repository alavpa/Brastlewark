package es.alavpa.brastlewark.presenter.main;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.interactors.Filter;
import es.alavpa.brastlewark.interactors.GetGnomeItem;
import es.alavpa.brastlewark.interactors.GetGnomes;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 14/9/16.
 */
public class MainPresenter {

    MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void init() {

        if (view != null) {
            view.startLoading();
        }

        Subscription subscription = new GetGnomes().execute()
                .flatMap(new Func1<List<Gnome>, Observable<List<GnomeItem>>>() {
                    @Override
                    public Observable<List<GnomeItem>> call(List<Gnome> gnomes) {
                        return Observable.from(gnomes)
                                .flatMap(new Func1<Gnome, Observable<GnomeItem>>() {
                                    @Override
                                    public Observable<GnomeItem> call(Gnome gnome) {
                                        return new GetGnomeItem(gnome).execute();
                                    }
                                })
                                .toList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GnomeItem>>() {
                    @Override
                    public void onCompleted() {
                        if (view != null) {
                            view.stopLoading();
                            //fillColors();
                            //fillProfessions();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.showError(e.getMessage());
                            view.stopLoading();
                        }
                    }

                    @Override
                    public void onNext(List<GnomeItem> gnomeItems) {
                        if (view != null) {
                            view.setList(gnomeItems);
                        }
                    }
                });

        if(view!=null){
            view.addSubscription(subscription);
        }


    }


    public void search(String name,
                       String minAge,
                       String maxAge,
                       String minHeight,
                       String maxHeight,
                       String minWeight,
                       String maxWeight,
                       List<String> colors,
                       List<String> professions) {
        if (view != null) {

            view.startLoading();
            view.hideFilter();

            Subscription subscription = new Filter(name,
                    minAge,
                    maxAge,
                    minHeight,
                    maxHeight,
                    minWeight,
                    maxWeight,
                    colors,
                    professions).execute()
                    .flatMap(new Func1<List<Gnome>, Observable<List<GnomeItem>>>() {
                        @Override
                        public Observable<List<GnomeItem>> call(List<Gnome> gnomes) {
                            return Observable.from(gnomes)
                                    .flatMap(new Func1<Gnome, Observable<GnomeItem>>() {
                                        @Override
                                        public Observable<GnomeItem> call(Gnome gnome) {
                                            return new GetGnomeItem(gnome).execute();
                                        }
                                    }).toList();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<GnomeItem>>() {
                        @Override
                        public void onCompleted() {
                            if (view != null) {
                                view.stopLoading();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (view != null) {
                                view.showError(e.getMessage());
                                view.stopLoading();
                            }
                        }

                        @Override
                        public void onNext(List<GnomeItem> gnomeItems) {
                            if (view != null) {
                                view.setList(gnomeItems);
                                view.hideFilter();
                            }
                        }
                    });


            view.addSubscription(subscription);

        }
    }


    public void cancel() {
        if (view != null) {
            view.hideFilter();
        }
    }

    public void onClickActionFilter() {
        if (view != null) {
            view.showFilter();
        }
    }

    public void onItemClick(GnomeItem item) {
        if (view != null) {
            view.gotoDetails(item.getId());
        }
    }
}
