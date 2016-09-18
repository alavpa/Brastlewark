package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 16/9/16.
 */
public class FilterProfessions {
    List<Gnome> toFilter;
    List<String> professions;

    public FilterProfessions(List<String> professions, List<Gnome> toFilter) {
        this.toFilter = toFilter;
        this.professions = professions;
    }

    public Observable<List<Gnome>> execute(){
        return Observable.from(toFilter)
                .filter(new Func1<Gnome, Boolean>() {
                    @Override
                    public Boolean call(Gnome gnome) {

                        if(professions.size() == 0) return true;

                        for(String profession : professions){
                            return gnome.getProfessions().contains(profession);
                        }

                        return false;
                    }
                })
                .toList();


    }
}
