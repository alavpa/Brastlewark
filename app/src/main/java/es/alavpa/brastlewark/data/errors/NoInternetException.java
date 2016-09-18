package es.alavpa.brastlewark.data.errors;

import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 17/9/16.
 */
public class NoInternetException extends Throwable{

    public NoInternetException(){
        super(App.get().getString(R.string.no_internet_exception));
    }
}
