package es.alavpa.brastlewark.interactors;

import java.util.List;

import es.alavpa.brastlewark.data.model.Gnome;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alavpa on 16/9/16.
 */
public class Filter {

    String name;
    String minAge;
    String maxAge;
    String minHeight;
    String maxHeight;
    String minWeight;
    String maxWeight;
    List<String> colors;
    List<String> professions;

    public Filter(String name,
                  String minAge,
                  String maxAge,
                  String minHeight,
                  String maxHeight,
                  String minWeight,
                  String maxWeight,
                  List<String> colors,
                  List<String> professions) {

        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.colors = colors;
        this.professions = professions;
    }

    public Observable<List<Gnome>> execute(){
        return new GetGnomes().execute()
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterName(name,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterMinAge(minAge,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterMaxAge(maxAge,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterMinHeight(minHeight,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterMaxHeight(maxHeight,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterMinWeight(minWeight,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterMaxWeight(maxWeight,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterHairColor(colors,gnomes).execute();
                    }
                })
                .flatMap(new Func1<List<Gnome>, Observable<List<Gnome>>>() {
                    @Override
                    public Observable<List<Gnome>> call(List<Gnome> gnomes) {
                        return new FilterProfessions(professions,gnomes).execute();
                    }
                });


    }
}
