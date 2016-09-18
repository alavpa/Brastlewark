package es.alavpa.brastlewark.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.alavpa.brastlewark.R;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity implements BaseView{

    protected RelativeLayout rl_content;

    @BindView(R.id.cl_main)
    protected CoordinatorLayout cl_main;
    @BindView(R.id.pb_loader)
    protected ProgressBar pb_loader;

    protected Navigation navigation;
    CompositeSubscription subscriptions;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        super.setContentView(layoutResID);
        super.setContentView(R.layout.activity_base);
        RelativeLayout rl_content = (RelativeLayout) findViewById(R.id.rl_content);
        View view = LayoutInflater.from(this)
                .inflate(layoutResID,rl_content,false);
        rl_content.addView(view,0);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigation = new Navigation(this);
        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!subscriptions.isUnsubscribed()){
            subscriptions.unsubscribe();
        }
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar snackbar = Snackbar.make(cl_main,errorMessage,Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        snackbar.show();
    }

    @Override
    public void startLoading() {

        pb_loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        pb_loader.setVisibility(View.GONE);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(){
        View view = this.getCurrentFocus();
        if(view!=null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
