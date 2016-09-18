package es.alavpa.brastlewark.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alavpa on 15/9/16.
 */
public class Population {

    @SerializedName("Brastlewark")
    private
    List<Gnome> brastlewark;

    public List<Gnome> getBrastlewark() {
        return brastlewark;
    }

    public void setBrastlewark(List<Gnome> brastlewark) {
        this.brastlewark = brastlewark;
    }
}
