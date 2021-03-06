package es.alavpa.brastlewark.interactors.mappers;

import android.text.TextUtils;

import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.data.model.Gnome;
import es.alavpa.brastlewark.interactors.model.GnomeDetails;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 14/9/16.
 */
public class GnomeDetailsConverter {
    public static GnomeDetails convertToGnomeDetail(Gnome gnome){
        GnomeDetails item = new GnomeDetails();
        item.setId(gnome.getId());
        item.setName(gnome.getName());
        item.setThumbnail(gnome.getThumbnail());
        item.setAge(String.format("%d %s",gnome.getAge(), App.get().getString(R.string.years_label)));
        item.setWeight(String.format("%.2f %s",gnome.getWeight(), App.get().getString(R.string.grams_label)));
        item.setHeight(String.format("%.2f %s",gnome.getHeight(), App.get().getString(R.string.milimeters_label)));
        item.setHairColor(gnome.getHairColor());
        item.setFriends(gnome.getFriends());
        String professions = TextUtils.join(", ", gnome.getProfessions());
        item.setProfessions(professions);
        return item;
    }
}
