package com.pagodalabs.materialdesign.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pagodalabs.materialdesign.R;
import com.pagodalabs.materialdesign.activities.ProfessionalDetailActivity;
import com.pagodalabs.materialdesign.adapters.ProfessionalAdapter;
import com.pagodalabs.materialdesign.constant.JsonKeys;
import com.pagodalabs.materialdesign.constant.URL;
import com.pagodalabs.materialdesign.dao.RecyclerClassListener;
import com.pagodalabs.materialdesign.entities.Professional;
import com.pagodalabs.materialdesign.listeners.RecyclerTouchListener;
import com.pagodalabs.materialdesign.network.VolleySingleton;
import com.pagodalabs.materialdesign.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentProfessional extends Fragment {

    private static final String PREF_FILE_NAME = "professional";

    private TextView tvVolleyError;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private RecyclerView rvProfessional;
    
    private ProfessionalAdapter professionalAdapter;
    private ArrayList<Professional> professionalList;
    
    private ProgressDialog progressDialog;
    
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static FragmentProfessional newInstance(String param1, String param2) {
        FragmentProfessional fragment = new FragmentProfessional();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentProfessional() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        
        volleySingleton = VolleySingleton.getsInstance();
        requestQueue = volleySingleton.getRequestQueue();
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_professional, container, false);
        rvProfessional = (RecyclerView)view.findViewById(R.id.rvProfessional);
        tvVolleyError = (TextView)view.findViewById(R.id.tvVolleyError);
        rvProfessional.setLayoutManager(new LinearLayoutManager(getActivity()));
        professionalAdapter = new ProfessionalAdapter(getActivity());
        rvProfessional.setAdapter(professionalAdapter);
        sendJsonRequest();
        rvProfessional.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvProfessional, new RecyclerClassListener() {
            @Override
            public void onClick(View v, int position) {
                startActivity(new Intent(getActivity(), ProfessionalDetailActivity.class));
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));
        return view;
    }

    private void sendJsonRequest(){
        int professionId = getActivity().getIntent().getIntExtra("categoryId",0);
        if(professionId != 0){
            SharedPref.saveToPreferences(getActivity(),PREF_FILE_NAME, "professional", ""+professionId);
        }else{
            professionId = Integer.parseInt(SharedPref.readFromPreferences(getActivity(), PREF_FILE_NAME, "professional", ""));
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL.PROFESSIONALS_CATEGORY_URL+professionId,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        tvVolleyError.setVisibility(View.GONE);
                        parseJSONResponse(response);
                        professionalAdapter.setProfessionalList(professionalList);
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        handleVolleyErrors(error);
                        progressDialog.dismiss();


                    }
                });
        requestQueue.add(request);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Content....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    private ArrayList<Professional> parseJSONResponse(JSONObject response){
        professionalList = new ArrayList<Professional>();
        try{
            if(response.has(JsonKeys.professionals)) {
                JSONArray arrayProfessional = response.getJSONArray(JsonKeys.professionals);

                for (int i = 0; i < arrayProfessional.length(); i++) {
                    JSONObject professionalJson = arrayProfessional.getJSONObject(i);
                    Professional professional = new Professional();
                    professional.setProfessionalId(professionalJson.getInt(JsonKeys.professionalId));
                    professional.setCategoryId(professionalJson.getInt(JsonKeys.categoryId));
                    professional.setFirstName(professionalJson.getString(JsonKeys.firstName));
                    professional.setLastName(professionalJson.getString(JsonKeys.lastName));
                    professional.setEmail(professionalJson.getString(JsonKeys.email));
                    professional.setPhone(professionalJson.getString(JsonKeys.phone));
                    professional.setDescription(professionalJson.getString(JsonKeys.description));
                    professional.setActivation(professionalJson.getInt(JsonKeys.activation));
                    professionalList.add(professional);

                }
            }

        }catch(JSONException j){

        }
        return  professionalList;
    }

    private void handleVolleyErrors(VolleyError error){
        tvVolleyError.setVisibility(View.VISIBLE);

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            tvVolleyError.setText(R.string.error_timeout);
        }else if (error instanceof AuthFailureError) {
            tvVolleyError.setText(R.string.error_auth_failure);

        } else if (error instanceof ServerError) {
            tvVolleyError.setText(R.string.error_server);

        } else if (error instanceof NetworkError) {
            tvVolleyError.setText(R.string.error_network);

        } else if (error instanceof ParseError) {
            tvVolleyError.setText(R.string.error_parse);

        }
    }

}
