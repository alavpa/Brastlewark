package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.DataProvider;
import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.interactors.model.GnomeDetails;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 15/9/16.
 */
public class GetGnomeDetailsById {

    DataProvider dataProvider = DataProvider.get();
    long id;

    public GetGnomeDetailsById(long id){
        this.id = id;
    }

    public Observable<GnomeDetails> execute(){
        return dataProvider.getGnomes()
                .flatMap(new Func1<List<Gnome>, Observable<GnomeDetails>>() {
                    @Override
                    public Observable<GnomeDetails> call(List<Gnome> gnomes) {

                        return Observable.from(gnomes)
                                .filter(new Func1<Gnome, Boolean>() {
                                    @Override
                                    public Boolean call(Gnome gnome) {
                                        return gnome.getId()==id;
                                    }
                                })
                                .flatMap(new Func1<Gnome, Observable<GnomeDetails>>() {
                                    @Override
                                    public Observable<GnomeDetails> call(Gnome gnome) {
                                        return new GetGnomeDetails(gnome).execute();
                                    }
                                });
                    }
                });

    }
}
