package es.alavpa.brastlewark.data.api;

import android.util.Log;

import com.google.gson.Gson;

import es.alavpa.brastlewark.Config;
import es.alavpa.brastlewark.data.api.retrofit.RetrofitRest;
import es.alavpa.brastlewark.data.api.retrofit.RxErrorHandlingCallAdapterFactory;
import es.alavpa.brastlewark.data.model.Population;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by alavpa on 14/9/16.
 */
public class RestApi implements ApiInterface {

    private RetrofitRest retrofitRest;
    private RetrofitRest getRetrofitRest() {
        if(retrofitRest == null){
            retrofitRest = getApiInterface(Config.BASE_URL);
        }
        return retrofitRest;
    }

    private RetrofitRest getApiInterface(final String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create());

        Retrofit retrofit = builder.build();

        return retrofit.create(RetrofitRest.class);
    }


    @Override
    public Observable<Population> getPopulation() {

        Log.d("Retrofit", "getPopulation");
        return getRetrofitRest().getPopulation();

    }
}
