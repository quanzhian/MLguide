package com.mlxing.mlguide;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.mlxing.mlguide.activity.BaseActivity;
import com.mlxing.mlguide.activity.setting.SettingActivity;
import com.mlxing.mlguide.fragment.main.FragmentAdapter;
import com.mlxing.mlguide.fragment.main.PageChangeImpl;
import com.mlxing.mlguide.utils.UIHelp;
import com.mlxing.mlguide.widget.APSTSViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    public AdvancedPagerSlidingTabStrip mAPSTS;
    public APSTSViewPager mVP;

    private ImageView mIvCenterBtn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        findViews();
        init();
    }

    private void findViews(){
        mAPSTS = (AdvancedPagerSlidingTabStrip)findViewById(R.id.tabs);
        mVP = (APSTSViewPager)findViewById(R.id.vp_main);
        mIvCenterBtn = (ImageView)findViewById(R.id.ivCenterBtn);
    }

    private void init(){
        mIvCenterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelp.toastMessage("中间点击了");
            }
        });
        int mSize = getResources().getDimensionPixelSize(R.dimen.weibo_tab_size);
        mVP.setOffscreenPageLimit(FragmentAdapter.VIEW_SIZE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(this,fragmentManager,mSize);

        mVP.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        mAPSTS.setViewPager(mVP);
        mAPSTS.setOnPageChangeListener(new PageChangeImpl(adapter));
        mVP.setCurrentItem(FragmentAdapter.VIEW_FIRST);
        mAPSTS.showDot(FragmentAdapter.VIEW_SECOND,"99+");
    }


//    @OnClick(R.id.fab)
//    public void onClick(View view){
//        Snackbar.make(view, "弹出提示", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_search:
                UIHelp.toastMessage("搜索");
                return true;
            case R.id.action_home:
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if(id == R.id.nav_setting){
            goTo(SettingActivity.class);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
