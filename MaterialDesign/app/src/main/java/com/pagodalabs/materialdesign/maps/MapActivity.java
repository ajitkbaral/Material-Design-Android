package com.pagodalabs.materialdesign.maps;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.gps.GPSTracker;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
        }if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()) {
            double lat = gps.getLatitude();
            double lon = gps.getLongitude();


            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15);
            googleMap.animateCamera(cameraUpdate);
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title("You Are Here")
                    .draggable(true)).showInfoWindow();
            googleMap.setMyLocationEnabled(true);


            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude))
                            .title("You are Here too").draggable(true).snippet("Your Location: "+latLng.latitude+",\n "+latLng.longitude)).showInfoWindow();
                }
            });

        }else{
            gps.showSettingsAlert();
        }
    }


}
