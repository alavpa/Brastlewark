package es.alavpa.brastlewark.interactors;

import android.text.TextUtils;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 16/9/16.
 */
public class FilterMinAge {
    List<Gnome> toFilter;
    String min;

    public FilterMinAge(String min, List<Gnome> toFilter) {
        this.toFilter = toFilter;
        this.min = min;
    }

    public Observable<List<Gnome>> execute(){
        return Observable.from(toFilter)
                .filter(new Func1<Gnome, Boolean>() {
                    @Override
                    public Boolean call(Gnome gnome) {
                        if (TextUtils.isEmpty(min)){
                            return true;
                        }

                        int minValue = Integer.parseInt(min);
                        return gnome.getAge()>=minValue;

                    }
                })
                .toList();


    }
}
