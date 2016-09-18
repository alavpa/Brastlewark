package es.alavpa.brastlewark.presenter;

import android.app.Application;
import android.content.Context;

/**
 * Created by alavpa on 14/9/16.
 */
public class App extends Application {

    private static Context INSTANCE;

    public static Context get(){
        return INSTANCE;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
