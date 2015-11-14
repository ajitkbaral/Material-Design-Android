package com.pagodalabs.materialdesign.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.activities.MainActivity;
import com.pagodalabs.materialdesign.adapters.DrawerAdapter;
import com.pagodalabs.materialdesign.dao.RecyclerClassListener;
import com.pagodalabs.materialdesign.entities.Information;
import com.pagodalabs.materialdesign.listeners.RecyclerTouchListener;
import com.pagodalabs.materialdesign.maps.MapActivity;
import com.pagodalabs.materialdesign.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment{


    private static final String PREF_FILE_NAME = "zyami_navigation";
    private static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private View containerView;

    private RecyclerView recyclerView;

    private DrawerAdapter drawerAdapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);


        drawerAdapter = new DrawerAdapter(getActivity(), getData());


        recyclerView.setAdapter(drawerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerClassListener() {

            //Starting an activity or giving an action to the navigation bar items
            @Override
            public void onClick(View v, int position) {
                if(position!=0 && position <=3){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    ((MainActivity)getActivity()).onDrawerItemClick(position-1);
                }else {
                    startActivity(new Intent(getActivity(), MapActivity.class));
                }

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));

        return layout;
    }


    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.people,R.drawable.job, R.drawable.user, R.drawable.maps};
        String[] titles = {"Home", "Jobs","Profile", "Maps"};
        for(int i = 0; i<titles.length && i<icons.length; i++){
            Information current = new Information();
            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            data.add(current);
        }
        /*for(int i = 0; i<100; i++){
            Information current = new Information();
            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            data.add(current);
        }*/
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(SharedPref.readFromPreferences(getActivity(), PREF_FILE_NAME, KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState = true;
        }
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    SharedPref.saveToPreferences(getActivity(), PREF_FILE_NAME, KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                ((MainActivity)getActivity()).onDrawerSlide(slideOffset);
                toolbar.setAlpha(1 - slideOffset/2);

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();

                if(!mUserLearnedDrawer && !mFromSavedInstanceState){
                    mDrawerLayout.openDrawer(containerView);
                }

            }
        });

    }








}
