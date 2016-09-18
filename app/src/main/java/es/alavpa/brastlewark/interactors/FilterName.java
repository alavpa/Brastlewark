package es.alavpa.brastlewark.interactors;

import android.text.TextUtils;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 16/9/16.
 */
public class FilterName {
    List<Gnome> toFilter;
    String text;

    public FilterName(String text, List<Gnome> toFilter) {
        this.toFilter = toFilter;
        this.text = text;
    }

    public Observable<List<Gnome>> execute(){
        return Observable.from(toFilter)
                .filter(new Func1<Gnome, Boolean>() {
                    @Override
                    public Boolean call(Gnome gnome) {
                        if (TextUtils.isEmpty(text)){
                            return true;
                        }

                        return gnome.getName().toLowerCase().contains(text.toLowerCase());
                    }
                })
                .toList();


    }
}
