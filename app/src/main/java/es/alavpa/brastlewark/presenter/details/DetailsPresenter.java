package es.alavpa.brastlewark.presenter.details;

import java.util.List;

import es.alavpa.brastlewark.interactors.GetGnomeDetailsById;
import es.alavpa.brastlewark.interactors.GetGnomeItemFriends;
import es.alavpa.brastlewark.interactors.model.GnomeDetails;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alavpa on 15/9/16.
 */
public class DetailsPresenter {

    DetailsView view;
    public DetailsPresenter(DetailsView view){
        this.view = view;
    }

    public void init(long id){
        new GetGnomeDetailsById(id)
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GnomeDetails>() {
                    @Override
                    public void onCompleted() {
                        if(view!=null){
                            view.stopLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view!=null){
                            view.showError(e.getMessage());
                            view.stopLoading();
                        }
                    }

                    @Override
                    public void onNext(GnomeDetails details) {
                        if(details!=null){
                            if(view!=null){
                                view.fill(details);
                                setFriends(details);
                            }
                        }
                    }
                });
    }

    public void setFriends(GnomeDetails details){

        new GetGnomeItemFriends(details.getFriends()).execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GnomeItem>>() {
                    @Override
                    public void onCompleted() {
                        if(view!=null){
                            view.stopLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view!=null){
                            view.showError(e.getMessage());
                            view.stopLoading();
                        }
                    }

                    @Override
                    public void onNext(List<GnomeItem> gnomeItems) {
                        if(view!=null){
                            view.setList(gnomeItems);
                        }
                    }
                });
    }

    public void onItemClick(long id) {
        if(view!=null){
            view.gotoDetails(id);
        }
    }
}
