package com.bluestar.bluestar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener ,View.OnTouchListener {

    NavigationView navigationView;
    EditText et_search_bar;
    ImageView imv_search, imv_cart;
    TextView tv_blue_star;
    ViewPager viewPager, viewPager1, viewPager2,viewPager3, viewPager4;
    PagerAdapter adapter;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;
    LinearLayout ll_search_bar;
    TabLayout tab_layout;
    int currentPage = 0, currentPage1 = 0, currentPage2 = 10, currentPage3 = 0, currentPage4 = 10;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    private static final int NUM_PAGES = 10;
    Timer swipeTimer ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        updateXml();
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            imageSliderView();
            imageSliderView1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showAlert(String title, String message, Activity context)
    {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            AlertDialog ad = builder.create();
            ad.setTitle(title);
            ad.setMessage(message);
            ad.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void imageSliderView1()
    {
        try{

            // Locate the ViewPager in viewpager_main.xml
            viewPager = (ViewPager) findViewById(R.id.pager);
            // Pass results to ViewPagerAdapter Class
            adapter = new ViewPagerItemAdapter(BaseActivity.this, rank, country, population, flag);
            // Binds the Adapter to the ViewPager  setOnItemClickListener
            viewPager.setAdapter(adapter);


            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES-1) {
                        currentPage = 0;
                    }

                    viewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer = new Timer(); // This will create a new Thread
            timer .schedule(new TimerTask() { // task to be scheduled

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 500, 3000);

//            swipeTimer = new Timer();
//            swipeTimer.schedule(new TimerTask() {
//
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (currentPage == NUM_PAGES) {
//                                currentPage = 0;
//                            }
//                            viewPager.setCurrentItem(currentPage++, true);
//                        }
//                    });
//                }
//            }, 500, 3000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void imageSliderView() {
        try {
//            ll_view_pager_imageSlider.removeAllViews();
//            View view = getLayoutInflater().inflate(R.layout.view_pager_image_slider, null);
            // Locate the ViewPager in viewpager_main.xml
            viewPager1 = (ViewPager) findViewById(R.id.pager);
            // Pass results to ViewPagerAdapter Class
            adapter = new ViewPagerItemAdapter(BaseActivity.this, rank, country, population, flag);
            // Binds the Adapter to the ViewPager  setOnItemClickListener
            viewPager1.setAdapter(adapter);


           viewPager2 = (ViewPager) findViewById(R.id.pager1);
            // Pass results to ViewPagerAdapter Class
            adapter = new ViewPagerItemAdapter(BaseActivity.this, rank, country, population, flag);
            // Binds the Adapter to the ViewPager
            viewPager2.setAdapter(adapter);

            viewPager3 = (ViewPager) findViewById(R.id.pager2);
            // Pass results to ViewPagerAdapter Class
            adapter = new ViewPagerItemAdapter(BaseActivity.this, rank, country, population, flag);
            // Binds the Adapter to the ViewPager
            viewPager3.setAdapter(adapter);

            viewPager4 = (ViewPager) findViewById(R.id.pager3);
            // Pass results to ViewPagerAdapter Class
            adapter = new ViewPagerItemAdapter(BaseActivity.this, rank, country, population, flag);
            // Binds the Adapter to the ViewPager
            viewPager4.setAdapter(adapter);
            tab_layout.setupWithViewPager(viewPager4, true);

//            ll_view_pager_imageSlider.addView(view);

            /*After setting the adapter use the timer */
            final Handler handler1 = new Handler();
            final Runnable Update1 = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES-2) {
                        currentPage = 0;
                    }
                    if (currentPage1 == NUM_PAGES-1) {
                        currentPage1 = 0;
                    }if (currentPage2 == NUM_PAGES-11) {
                        currentPage2 = 10;
                    }if (currentPage3 == NUM_PAGES-1) {
                        currentPage3 = 0;
                    }if (currentPage4 == NUM_PAGES-11) {
                        currentPage4 = 10;
                    }
                    viewPager1.setCurrentItem(currentPage1++, true);
                    viewPager2.setCurrentItem(currentPage2--, true);
                    viewPager3.setCurrentItem(currentPage3++, true);
                    viewPager4.setCurrentItem(currentPage4--, true);
                }
            };

            timer = new Timer(); // This will create a new Thread
            timer .schedule(new TimerTask() { // task to be scheduled

                @Override
                public void run() {
                    handler1.post(Update1);
                }
            }, 500, 3000);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {
                case R.id.imv_search:
                    ll_search_bar.setVisibility(View.VISIBLE);
                    imv_search.setVisibility(View.GONE);

                    break;
                case R.id.imv_cart:
                    ll_search_bar.setVisibility(View.GONE);
                    imv_search.setVisibility(View.VISIBLE);
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateXml()
    {
        et_search_bar= (EditText) findViewById(R.id.et_search_bar);
        et_search_bar.setOnClickListener(this);
        imv_search = (ImageView)  findViewById(R.id.imv_search);
        imv_search.setOnClickListener(this);
        imv_cart = (ImageView)  findViewById(R.id.imv_cart);
        imv_cart.setOnClickListener(this);
        ll_search_bar = (LinearLayout) findViewById(R.id.ll_search_bar);
        ll_search_bar.setOnClickListener(this);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);


        rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        country = new String[] { "China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan" };

        population = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000" };

        flag = new int[] { R.drawable.c1, R.drawable.c2,
                R.drawable.c3, R.drawable.c4,
                R.drawable.c5, R.drawable.c6, R.drawable.c7,
                R.drawable.c8, R.drawable.c9, R.drawable.cloths };

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
            if (id == R.id.action_settings1) {
                return true;
            }
            if (id == R.id.action_settings2) {
                return true;
            }
            if (id == R.id.action_settings3) {
                return true;
            }
            if (id == R.id.action_settings4) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        try {
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                Intent intent = new Intent(this, Gallary.class);
                startActivity(intent);
                // Handle the camera action
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }else if (id == R.id.nav_view) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
