package es.alavpa.brastlewark.presenter.main.filter;

import java.util.List;

import es.alavpa.brastlewark.interactors.GetHairColor;
import es.alavpa.brastlewark.interactors.GetProfessions;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 18/9/16.
 */
public class FilterPresenter {

    FilterView view;
    FilterParentView parent;
    public FilterPresenter(FilterView view, FilterParentView parent){
        this.view = view;
        this.parent = parent;
    }

    public void init(){
        fillColors();
        fillProfessions();
    }
    public void fillColors() {
        Subscription subscription = new GetHairColor().execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (parent != null) {
                            parent.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<String> colors) {
                        if (view != null) {
                            view.addHairColor(colors);
                        }
                    }
                });

        if(parent!=null){
            parent.addSubscription(subscription);
        }

    }

    public void fillProfessions() {
        Subscription subscription = new GetProfessions().execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (parent != null) {
                            parent.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<String> professions) {
                        if (view != null) {
                            view.addProfession(professions);
                        }
                    }
                });

        if(parent!=null){
            parent.addSubscription(subscription);
        }
    }

    public void search() {
        if(view!=null) {
            String name = view.getName();
            String minAge = view.getMinAge();
            String maxAge = view.getMaxAge();
            String minHeight = view.getMinHeight();
            String maxHeight = view.getMaxHeight();
            String minWeight = view.getMinWeight();
            String maxWeight = view.getMaxWeight();
            List<String> colors = view.getHairColor();
            List<String> professions = view.getProfessions();

            if(parent!=null){
                parent.search(name,
                        minAge,
                        maxAge,
                        minHeight,
                        maxHeight,
                        minWeight,
                        maxWeight,
                        colors,
                        professions);
            }
        }

    }

    public void cancel() {
        if(parent!=null){
            parent.cancel();
        }
    }
}
