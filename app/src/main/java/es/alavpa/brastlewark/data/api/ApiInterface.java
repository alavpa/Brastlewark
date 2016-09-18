package es.alavpa.brastlewark.data.api;

import es.alavpa.brastlewark.data.model.Population;
import rx.Observable;

/**
 * Created by alavpa on 14/9/16.
 */
public interface ApiInterface {

    Observable<Population> getPopulation();
}
