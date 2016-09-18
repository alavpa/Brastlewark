package es.alavpa.brastlewark.interactors;

import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.interactors.errors.GnomeItemConverterException;
import es.alavpa.brastlewark.interactors.mappers.GnomeItemConverter;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 15/9/16.
 */
public class GetGnomeItem {
    Gnome gnome;

    public GetGnomeItem(Gnome gnome) {
        this.gnome = gnome;
    }

    public Observable<GnomeItem> execute(){

        return Observable.just(gnome)
                .flatMap(new Func1<Gnome, Observable<GnomeItem>>() {
                    @Override
                    public Observable<GnomeItem> call(Gnome gnome) {
                        try {
                            GnomeItem list = GnomeItemConverter.convertToGnomeItem(gnome);
                            return Observable.just(list);
                        }catch (Throwable e){
                            return Observable.error(new GnomeItemConverterException());
                        }
                    }
                });
    }
}
