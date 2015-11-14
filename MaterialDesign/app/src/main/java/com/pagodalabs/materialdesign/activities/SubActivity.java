package com.pagodalabs.materialdesign.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.fragments.FragmentJobs;
import com.pagodalabs.materialdesign.fragments.FragmentProfession;
import com.pagodalabs.materialdesign.fragments.FragmentSearch;
import com.pagodalabs.materialdesign.utils.Utils;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class SubActivity extends AppCompatActivity implements MaterialTabListener, View.OnClickListener{

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    private static final int  PROFESSIONS_LIST = 0;
    private static final int PROFESSION_SEARCH_RESULT =1;
    private static final int JOBS_LIST = 2;


    private ImageView mainIcon;

    private final String TAG_ICON1 ="icon1";
    private final String TAG_ICON2 = "icon2";
    private final String TAG_ICON3 = "icon3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabHost = (MaterialTabHost)findViewById(R.id.materialTabHost);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            //for Text Tab
                            //.setText(adapter.getPageTitle(i))
                            //for Icon tab
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this)
            );
        }


        //Floating Action Button
        mainIcon = new ImageView(this);
        mainIcon.setImageResource(R.drawable.user);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(mainIcon)
                .build();

        ImageView subIcon1 = new ImageView(this);
        subIcon1.setImageResource(R.drawable.user);
        ImageView subIcon2 = new ImageView(this);;
        subIcon2.setImageResource(R.drawable.user);
        ImageView subIcon3= new ImageView(this);;
        subIcon3.setImageResource(R.drawable.user);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton btnIcon1 = itemBuilder.setContentView(subIcon1).build();
        SubActionButton btnIcon2 = itemBuilder.setContentView(subIcon2).build();
        SubActionButton btnIcon3 = itemBuilder.setContentView(subIcon3).build();

        btnIcon1.setTag(TAG_ICON1);
        btnIcon2.setTag(TAG_ICON2);
        btnIcon3.setTag(TAG_ICON3);

        btnIcon1.setOnClickListener(this);
        btnIcon2.setOnClickListener(this);
        btnIcon3.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnIcon1)
                .addSubActionView(btnIcon2)
                .addSubActionView(btnIcon3)
                        // ...
                .attachTo(actionButton)
                .build();

        //Utils.testJsonObject();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onClick(View v) {

        //FOR FLOATING BUTTON
        if(v.getTag().equals(TAG_ICON1)){
            Utils.toastMessage(this, "icon1");
        }else if(v.getTag().equals(TAG_ICON2)){
            Utils.toastMessage(this, "icon2");

        }else if(v.getTag().equals(TAG_ICON3)){
            Utils.toastMessage(this, "icon3 ");

        }


    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        //Icons for tabs are created on this method
        int icons[] = {R.drawable.user, R.drawable.user, R.drawable.user};

        public Drawable getIcon(int position){
            return getResources().getDrawable(icons[position]);
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int num) {
            Fragment fragment = null;
            switch (num) {
                case PROFESSIONS_LIST:
                    fragment = FragmentProfession.newInstance("", "");
                    break;
                case PROFESSION_SEARCH_RESULT:

                    fragment = FragmentSearch.newInstance("","");
                    break;
                case JOBS_LIST:
                    fragment = FragmentJobs.newInstance("", "");
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }
    }

    public void onDrawerItemClick(int position){
        viewPager.setCurrentItem(position);
    }
}
