package es.alavpa.brastlewark.presenter.details;

import java.util.List;

import es.alavpa.brastlewark.interactors.model.GnomeDetails;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import es.alavpa.brastlewark.presenter.BaseView;

/**
 * Created by alavpa on 15/9/16.
 */
public interface DetailsView extends BaseView{

    void fill(GnomeDetails details);
    void setList(List<GnomeItem> items);
    void gotoDetails(long id);
}
