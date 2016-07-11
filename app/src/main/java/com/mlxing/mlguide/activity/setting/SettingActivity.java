package com.mlxing.mlguide.activity.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mlxing.mlguide.R;
import com.mlxing.mlguide.activity.BaseActivity;
import com.mlxing.mlguide.fragment.setting.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quan on 2016/6/21.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar_preference)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initToolbar();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingFragment()).commit();

    }


    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolbar.setTitle(getResources().getString(R.string.action_setting));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 选项菜单
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }


}
