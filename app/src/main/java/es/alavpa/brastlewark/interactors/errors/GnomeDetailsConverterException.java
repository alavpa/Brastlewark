package es.alavpa.brastlewark.interactors.errors;

import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 17/9/16.
 */
public class GnomeDetailsConverterException extends Throwable {
    public GnomeDetailsConverterException(){
        super(App.get().getString(R.string.converter_details_exception));
    }

}
