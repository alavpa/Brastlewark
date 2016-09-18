package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.DataProvider;
import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;

/**
 * Created by alavpa on 15/9/16.
 */
public class GetGnomes {

    DataProvider dataProvider = DataProvider.get();

    public Observable<List<Gnome>> execute(){
        return dataProvider.getGnomes();

    }
}
