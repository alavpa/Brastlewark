package es.alavpa.brastlewark.interactors.errors;

import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 17/9/16.
 */
public class GnomeItemConverterException extends Throwable {
    public GnomeItemConverterException(){
        super(App.get().getString(R.string.converter_item_exception));
    }
}
