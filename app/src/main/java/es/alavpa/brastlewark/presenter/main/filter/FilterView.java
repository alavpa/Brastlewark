package es.alavpa.brastlewark.presenter.main.filter;

import java.util.List;

/**
 * Created by alavpa on 18/9/16.
 */
public interface FilterView {

    void addHairColor(List<String> color);
    void addProfession(List<String> profession);

    List<String> getProfessions();
    List<String> getHairColor();
    String getName();
    String getMinAge();
    String getMaxAge();
    String getMinHeight();
    String getMaxHeight();
    String getMinWeight();
    String getMaxWeight();

}
