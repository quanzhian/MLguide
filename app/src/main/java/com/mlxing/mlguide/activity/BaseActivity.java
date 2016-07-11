package com.mlxing.mlguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mlxing.mlguide.R;

/**
 * Created by quan on 2016/6/18.
 */
public class BaseActivity extends AppCompatActivity {


    protected void goTo(Class<?> activity){
        if(activity.getSimpleName().equals(this.getClass().getSimpleName())){
            return;
        }
        startActivity(new Intent(this,activity));
    }

}
