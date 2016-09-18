package es.alavpa.brastlewark.presenter.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import es.alavpa.brastlewark.presenter.BaseActivity;
import es.alavpa.brastlewark.presenter.adapters.GnomeItemAdapter;
import es.alavpa.brastlewark.presenter.adapters.GnomeItemAdapterOnClickListener;
import es.alavpa.brastlewark.presenter.main.filter.FilterFragment;
import es.alavpa.brastlewark.presenter.main.filter.FilterParentView;

public class MainActivity extends BaseActivity implements MainView,FilterParentView {

    public static final String TAG_FILTER = "TAG_FILTER";

    FilterFragment fr_filter;

    @BindView(R.id.rv_items)
    RecyclerView rv_items;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RecyclerView.LayoutManager layoutManager;

    MainPresenter presenter;
    GnomeItemAdapter adapter;

    GnomeItemAdapterOnClickListener adapterListener = new GnomeItemAdapterOnClickListener() {
        @Override
        public void onClick(GnomeItem item) {
            presenter.onItemClick(item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);


        presenter = new MainPresenter(this);
        presenter.init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_filter){
            presenter.onClickActionFilter();
        }
        return true;
    }

    @Override
    public void setList(List<GnomeItem> list) {

        if(layoutManager ==null) {
            layoutManager = new LinearLayoutManager(this);
            rv_items.setLayoutManager(layoutManager);
        }

        adapter = new GnomeItemAdapter(list,adapterListener );
        rv_items.setAdapter(adapter);

    }



    @Override
    public void showFilter() {

        if(fr_filter==null) {
            fr_filter = (FilterFragment) getSupportFragmentManager().findFragmentByTag(TAG_FILTER);
            if (fr_filter == null) {
                fr_filter = new FilterFragment();
            }
        }

        if (!fr_filter.isVisible()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.fr_container, fr_filter, TAG_FILTER)
                    .show(fr_filter)
                    .addToBackStack(null)
                    .commit();
        } else {
            hideFilter();
        }



    }

    @Override
    public void hideFilter() {

        if(fr_filter!=null) {
            getSupportFragmentManager().popBackStack();

            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .hide(fr_filter)
                    .commit();

            hideKeyboard();
        }

    }

    @Override
    public void gotoDetails(long id) {
        navigation.openDetails(id);
    }

    @Override
    public void search(String name, String minAge, String maxAge, String minHeight, String maxHeight, String minWeight, String maxWeight, List<String> colors, List<String> professions) {
        presenter.search(name,
                minAge,
                maxAge,
                minHeight,
                maxHeight,
                minWeight,
                maxWeight,
                colors,
                professions);
    }

    @Override
    public void cancel() {
        presenter.cancel();
    }
}
