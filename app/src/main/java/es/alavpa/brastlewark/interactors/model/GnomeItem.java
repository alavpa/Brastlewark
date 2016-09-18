package es.alavpa.brastlewark.interactors.model;

/**
 * Created by alavpa on 14/9/16.
 */
public class GnomeItem{

    private long id;
    private String name;
    private String thumbnail;
    private String height;
    private String weight;
    private String age;
    private String hairColor;
    private String professions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfessions() {
        return professions;
    }

    public void setProfessions(String professions) {
        this.professions = professions;
    }
}
