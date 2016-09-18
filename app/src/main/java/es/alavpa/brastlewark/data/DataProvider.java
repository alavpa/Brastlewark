package es.alavpa.brastlewark.data;

import java.util.ArrayList;
import java.util.List;

import es.alavpa.brastlewark.data.api.RestApi;
import es.alavpa.brastlewark.data.errors.NoDataFoundedException;
import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.data.model.Population;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 15/9/16.
 */
public class DataProvider {

    private static DataProvider INSTANCE;
    public static DataProvider get(){
        if(INSTANCE == null){
            INSTANCE = new DataProvider();
        }
        return INSTANCE;
    }

    RestApi restApi;

    List<Gnome> gnomes;
    List<String> hairColors;
    List<String> professions;

    private DataProvider(){
        restApi = new RestApi();
        gnomes = null;
        hairColors = null;
        professions = null;
    }

    /**
     * Syncronized to avoid concurrent calls
     * @return list of gnomes from service
     */
    public synchronized Observable<List<Gnome>> getGnomes(){
        if(gnomes!=null){
            return Observable.just(gnomes);
        }else{
            return restApi.getPopulation()
                    .flatMap(new Func1<Population, Observable<List<Gnome>>>() {
                        @Override
                        public Observable<List<Gnome>> call(Population population) {
                            if(population!=null) {
                                gnomes = population.getBrastlewark();
                                return Observable.just(gnomes);
                            }else{
                                return Observable.error(new NoDataFoundedException());
                            }
                        }
                    });
        }
    }

    public Observable<List<String>> getHairColors() {
        if (hairColors != null) {
            return Observable.just(hairColors);
        } else {
            return getGnomes()
                    .flatMap(new Func1<List<Gnome>, Observable<List<String>>>() {
                        @Override
                        public Observable<List<String>> call(List<Gnome> gnomes) {
                            hairColors = new ArrayList<>();
                            for (Gnome gnome : gnomes) {
                                if (!hairColors.contains(gnome.getHairColor())) {
                                    hairColors.add(gnome.getHairColor());
                                }
                            }
                            return Observable.just(hairColors);
                        }
                    });
        }
    }

    public Observable<List<String>> getProfessions() {
        if (professions != null) {
            return Observable.just(professions);
        } else {
            return getGnomes()
                    .flatMap(new Func1<List<Gnome>, Observable<List<String>>>() {
                        @Override
                        public Observable<List<String>> call(List<Gnome> gnomes) {
                            professions = new ArrayList<>();
                            for (Gnome gnome : gnomes) {

                                for(String profession : gnome.getProfessions()){
                                    if (!professions.contains(profession)) {
                                        professions.add(profession);
                                    }
                                }

                            }
                            return Observable.just(professions);
                        }
                    });
        }
    }

}
