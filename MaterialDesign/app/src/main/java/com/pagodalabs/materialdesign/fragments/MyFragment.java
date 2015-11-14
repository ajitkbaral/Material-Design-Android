package com.pagodalabs.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.network.VolleySingleton;

/**
 * Created by Ajit Kumar Baral on 6/11/2015.
 */
public  class MyFragment extends Fragment{
    private TextView textView;

    public static MyFragment getInstance(int position){
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_my, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            textView.setText("The Page Currently Selected is " + bundle.getInt("position"));

        }

        //Using Volley Singleton Class

        RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, "http://php.net/"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "RESPONSE", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);

        /*
            //USING VOLLEY LIBRARY without a class

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest request = new StringRequest(Request.Method.GET, "http://php.net/"
                 , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "RESPONSE", Toast.LENGTH_LONG).show();
                    }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                });

            requestQueue.add(request);

            //USING OF VOLLEY
        */

        return layout;


    }
}
