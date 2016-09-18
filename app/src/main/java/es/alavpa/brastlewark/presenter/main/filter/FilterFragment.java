package es.alavpa.brastlewark.presenter.main.filter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.presenter.App;

/**
 * Created by alavpa on 18/9/16.
 */
public class FilterFragment extends Fragment implements FilterView{

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_min_age)
    EditText et_min_age;

    @BindView(R.id.et_max_age)
    EditText et_max_age;

    @BindView(R.id.et_min_height)
    EditText et_min_height;

    @BindView(R.id.et_max_height)
    EditText et_max_height;

    @BindView(R.id.et_min_weight)
    EditText et_min_weight;

    @BindView(R.id.et_max_weight)
    EditText et_max_weight;

    @BindView(R.id.ll_hair_color)
    LinearLayout ll_hair_color;

    @BindView(R.id.ll_professions)
    LinearLayout ll_professions;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.btn_search)
    Button btn_search;

    FilterPresenter presenter;
    FilterParentView parent;

    List<String> selectedHair = new ArrayList<>();
    List<String> selectedProfession = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (FilterParentView)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_filter,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FilterPresenter(this,parent);
        presenter.init();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.search();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cancel();
            }
        });
    }

    @Override
    public String getName() {
        return et_name.getText().toString();
    }

    @Override
    public String getMinAge() {
        return et_min_age.getText().toString();
    }

    @Override
    public String getMaxAge() {
        return et_max_age.getText().toString();
    }

    @Override
    public String getMinHeight() {
        return et_min_height.getText().toString();
    }

    @Override
    public String getMaxHeight() {
        return et_max_height.getText().toString();
    }

    @Override
    public String getMinWeight() {
        return et_min_weight.getText().toString();
    }

    @Override
    public String getMaxWeight() {
        return et_max_weight.getText().toString();
    }


    @Override
    public void addHairColor(List<String> color) {

        if(ll_hair_color.getChildCount()==0) {
            fillCheckBoxes(color, ll_hair_color, selectedHair);
        }

    }

    @Override
    public void addProfession(List<String> profession) {

        if(ll_professions.getChildCount()==0) {
            fillCheckBoxes(profession, ll_professions, selectedProfession);
        }
    }

    @Override
    public List<String> getProfessions() {
        List<String> professions = new ArrayList<>();
        getCheckBoxesText(ll_professions,professions,selectedProfession);
        return professions;
    }

    @Override
    public List<String> getHairColor() {
        List<String> colors = new ArrayList<>();
        getCheckBoxesText(ll_hair_color,colors,selectedHair);
        return colors;
    }

    public void getCheckBoxesText(LinearLayout ll_parent,List<String> list,List<String> selected){

        selected.clear();
        for(int i = 0;i<ll_parent.getChildCount();i++){
            LinearLayout ll = (LinearLayout) ll_parent.getChildAt(i);

            for(int j = 0;j<ll.getChildCount();j++){
                CheckBox chk = (CheckBox) ll.getChildAt(j);
                if(chk!=null) {
                    if(chk.isChecked()) {
                        selected.add(chk.getText().toString());
                        list.add(chk.getText().toString());
                    }
                }
            }
        }
    }


    public void fillCheckBoxes(List<String> texts, LinearLayout container, List<String> selected){

        LinearLayout ll  = (LinearLayout) LayoutInflater.from(App.get())
                .inflate(R.layout.layout_checkbox,container,false);
        container.addView(ll);

        for(int i=0;i<texts.size();i++){
            if(i%2==0){
                CheckBox chk_text1 = (CheckBox)ll.findViewById(R.id.chk_text1);
                chk_text1.setVisibility(View.VISIBLE);
                chk_text1.setText(texts.get(i));
                if(selected.contains(texts.get(i))){
                    chk_text1.setChecked(true);
                }
            }else{
                CheckBox chk_text2 = (CheckBox)ll.findViewById(R.id.chk_text2);
                chk_text2.setVisibility(View.VISIBLE);
                chk_text2.setText(texts.get(i));
                if(selected.contains(texts.get(i))){
                    chk_text2.setChecked(true);
                }

                ll  = (LinearLayout) LayoutInflater.from(App.get())
                        .inflate(R.layout.layout_checkbox,container,false);
                container.addView(ll);
            }
        }
    }
}
