package es.alavpa.brastlewark.interactors.errors;

import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 17/9/16.
 */
public class NoGnomeFoundedException extends Throwable{
    public NoGnomeFoundedException(){
        super(App.get().getString(R.string.no_gnome_founded_exception));
    }
}
