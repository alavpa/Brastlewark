package es.alavpa.brastlewark.interactors;

import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.interactors.errors.GnomeDetailsConverterException;
import es.alavpa.brastlewark.interactors.mappers.GnomeDetailsConverter;
import es.alavpa.brastlewark.interactors.model.GnomeDetails;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 15/9/16.
 */
public class GetGnomeDetails {
    Gnome gnome;

    public GetGnomeDetails(Gnome gnome) {
        this.gnome = gnome;
    }

    public Observable<GnomeDetails> execute() {

        return Observable.just(gnome)
                .flatMap(new Func1<Gnome, Observable<GnomeDetails>>() {
                    @Override
                    public Observable<GnomeDetails> call(Gnome gnome) {
                        try {
                            GnomeDetails details = GnomeDetailsConverter.convertToGnomeDetail(gnome);
                            return Observable.just(details);
                        } catch (Throwable e) {
                            return Observable.error(new GnomeDetailsConverterException());
                        }
                    }
                });

    }
}
