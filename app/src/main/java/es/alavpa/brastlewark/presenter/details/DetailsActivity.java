package es.alavpa.brastlewark.presenter.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import es.alavpa.brastlewark.R;
import es.alavpa.brastlewark.interactors.model.GnomeDetails;
import es.alavpa.brastlewark.interactors.model.GnomeItem;
import es.alavpa.brastlewark.presenter.App;
import es.alavpa.brastlewark.presenter.BaseActivity;
import es.alavpa.brastlewark.presenter.Navigation;
import es.alavpa.brastlewark.presenter.adapters.GnomeItemAdapter;
import es.alavpa.brastlewark.presenter.adapters.GnomeItemAdapterOnClickListener;

/**
 * Created by alavpa on 15/9/16.
 */
public class DetailsActivity extends BaseActivity implements DetailsView {

    @BindView(R.id.iv_header)
    ImageView iv_header;

    @BindView(R.id.tv_age)
    TextView tv_age;

    @BindView(R.id.tv_weight)
    TextView tv_weight;

    @BindView(R.id.tv_height)
    TextView tv_height;

    @BindView(R.id.tv_hair)
    TextView tv_hair;

    @BindView(R.id.tv_professions)
    TextView tv_professions;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_items)
    RecyclerView rv_items;

    @BindView(R.id.ll_friends)
    LinearLayout ll_friends;

    @BindView(R.id.ll_professions)
    LinearLayout ll_professions;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    GnomeItemAdapter adapter;

    GnomeItemAdapterOnClickListener adapterListener = new GnomeItemAdapterOnClickListener() {
        @Override
        public void onClick(GnomeItem item) {
            presenter.onItemClick(item.getId());
        }
    };


    RecyclerView.LayoutManager layoutManager;

    DetailsPresenter presenter;

    @BindView(R.id.layout_appbar)
    AppBarLayout layout_appbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        presenter = new DetailsPresenter(this);
        long id = getIntent().getLongExtra(Navigation.EXTRA_ID,-1);
        presenter.init(id);

        layout_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset==0){
                    getSupportActionBar().setHomeButtonEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                }else{
                    getSupportActionBar().setHomeButtonEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                }
            }
        });

    }

    @Override
    public void fill(GnomeDetails details) {
        Glide.with(App.get())
                .load(details.getThumbnail())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(iv_header);

        collapsing_toolbar.setTitle(details.getName());

        tv_age.setText(details.getAge());
        tv_height.setText(details.getHeight());
        tv_age.setText(details.getAge());
        tv_weight.setText(details.getWeight());
        tv_hair.setText(details.getHairColor());
        if(TextUtils.isEmpty(details.getProfessions())){
            ll_professions.setVisibility(View.GONE);
        }else {
            tv_professions.setText(details.getProfessions());
            ll_professions.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setList(List<GnomeItem> list) {
        if(layoutManager ==null) {
            layoutManager = new LinearLayoutManager(this);
            rv_items.setLayoutManager(layoutManager);
        }

        if(list.size()>0) {
            ll_friends.setVisibility(View.VISIBLE);
            rv_items.setHasFixedSize(true);
            adapter = new GnomeItemAdapter(list, adapterListener);
            rv_items.setAdapter(adapter);
        }else{
            ll_friends.setVisibility(View.GONE);
        }
    }

    @Override
    public void gotoDetails(long id) {
        navigation.openDetails(id);
    }
}
