package es.alavpa.brastlewark.data.api.retrofit;

import es.alavpa.brastlewark.Config;
import es.alavpa.brastlewark.data.model.Population;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by alavpa on 14/9/16.
 */
public interface RetrofitRest {

    @GET(Config.ENDPOINT_DATA)
    Observable<Population> getPopulation();

}
