package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.DataProvider;
import rx.Observable;

/**
 * Created by alavpa on 16/9/16.
 */
public class GetProfessions {

    DataProvider dataProvider = DataProvider.get();

    public Observable<List<String>> execute(){
        return dataProvider.getProfessions();

    }
}
