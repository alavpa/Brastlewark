package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 16/9/16.
 */
public class FilterHairColor {
    List<Gnome> toFilter;
    List<String> colors;

    public FilterHairColor(List<String> colors, List<Gnome> toFilter) {
        this.toFilter = toFilter;
        this.colors = colors;
    }


    public Observable<List<Gnome>> execute(){
        return Observable.from(toFilter)
                .filter(new Func1<Gnome, Boolean>() {
                    @Override
                    public Boolean call(Gnome gnome) {

                        if(colors.size() == 0) return true;

                        for(String color : colors){
                            return gnome.getHairColor().contains(color);
                        }

                        return false;
                    }
                })
                .toList();


    }
}
