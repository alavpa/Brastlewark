package es.alavpa.brastlewark.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by alavpa on 15/9/16.
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigation.openMain();
        finish();


    }
}
