package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.DataProvider;
import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 15/9/16.
 */
public class GetGnomeItemFriends {

    DataProvider dataProvider = DataProvider.get();
    List<String> names;

    public GetGnomeItemFriends(List<String> names){
        this.names = names;
    }

    public Observable<List<GnomeItem>> execute(){
        return dataProvider.getGnomes()
                .flatMap(new Func1<List<Gnome>, Observable<List<GnomeItem>>>() {
                    @Override
                    public Observable<List<GnomeItem>> call(List<Gnome> gnomes) {

                        return Observable.from(gnomes)
                                .filter(new Func1<Gnome, Boolean>() {
                                    @Override
                                    public Boolean call(Gnome gnome) {
                                        return names.contains(gnome.getName());
                                    }
                                })
                                .flatMap(new Func1<Gnome, Observable<GnomeItem>>() {
                                    @Override
                                    public Observable<GnomeItem> call(Gnome gnome) {
                                        return new GetGnomeItem(gnome).execute();
                                    }
                                })
                                .toList();

                    }
                });

    }
}
