package es.alavpa.brastlewark.interactors;

import android.text.TextUtils;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 16/9/16.
 */
public class FilterMaxAge {
    List<Gnome> toFilter;
    String max;

    public FilterMaxAge(String max, List<Gnome> toFilter) {
        this.toFilter = toFilter;
        this.max = max;
    }


    public Observable<List<Gnome>> execute(){
        return Observable.from(toFilter)
                .filter(new Func1<Gnome, Boolean>() {
                    @Override
                    public Boolean call(Gnome gnome) {
                        if (TextUtils.isEmpty(max)){
                            return true;
                        }

                        int maxValue = Integer.parseInt(max);
                        return gnome.getAge() <= maxValue;

                    }
                })
                .toList();


    }
}
