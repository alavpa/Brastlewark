package es.alavpa.brastlewark.presenter.main;

import java.util.List;

import es.alavpa.brastlewark.presenter.BaseView;
import es.alavpa.brastlewark.interactors.model.GnomeItem;

/**
 * Created by alavpa on 14/9/16.
 */
public interface MainView extends BaseView{

    void setList(List<GnomeItem> list);
    void showFilter();
    void hideFilter();
    void gotoDetails(long id);
}
