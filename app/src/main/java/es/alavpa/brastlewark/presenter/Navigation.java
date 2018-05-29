package es.alavpa.brastlewark.presenter;

import android.content.Context;
import android.content.Intent;

import es.alavpa.brastlewark.presenter.details.DetailsActivity;
import es.alavpa.brastlewark.presenter.main.MainActivity;

/**
 * Created by alavpa on 15/9/16.
 */
public class Navigation {

    public static String EXTRA_ID = "EXTRA_ID";

    Context ctx;

    public Navigation(Context context){
        ctx = context;
    }


    public void openDetails(long id){

        Intent intent = new Intent(ctx, DetailsActivity.class);
        intent.putExtra(EXTRA_ID,id);
        ctx.startActivity(intent);
    }

    public void openMain(){
        ctx.startActivity(new Intent(ctx, MainActivity.class));
        //Test4
        //Test5
    }

}
