package es.alavpa.brastlewark.presenter.main.filter;

import java.util.List;

import es.alavpa.brastlewark.presenter.BaseView;

/**
 * Created by alavpa on 18/9/16.
 */
public interface FilterParentView extends BaseView{
    void search(String name,
                String minAge,
                String maxAge,
                String minHeight,
                String maxHeight,
                String minWeight,
                String maxWeight,
                List<String> colors,
                List<String> professions);
    void cancel();
}
