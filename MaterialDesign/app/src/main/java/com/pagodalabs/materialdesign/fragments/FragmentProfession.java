package com.pagodalabs.materialdesign.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.pagodalabs.materialdesign.activities.ProfessionalActivity;
import com.pagodalabs.materialdesign.adapters.ProfessionAdapter;
import com.pagodalabs.materialdesign.constant.JsonKeys;
import com.pagodalabs.materialdesign.constant.URL;
import com.pagodalabs.materialdesign.dao.RecyclerClassListener;
import com.pagodalabs.materialdesign.entities.Profession;
import com.pagodalabs.materialdesign.listeners.RecyclerTouchListener;
import com.pagodalabs.materialdesign.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentProfession extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_PROFESSION = "state_profession";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView tvVolleyError;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private RecyclerView rvProfession;

    private ProfessionAdapter professionAdapter;

    private ArrayList<Profession> professionList;

    private SwipeRefreshLayout srLayout;

    private ProgressDialog progressDialog;


    public static FragmentProfession newInstance(String param1, String param2) {
        FragmentProfession fragment = new FragmentProfession();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentProfession() {
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_PROFESSION, professionList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profession, container, false);

        rvProfession = (RecyclerView) view.findViewById(R.id.rvProfession);
        tvVolleyError = (TextView)view.findViewById(R.id.tvVolleyError);
        srLayout = (SwipeRefreshLayout) view.findViewById(R.id.srLayout);
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(srLayout.isRefreshing()) {
                    sendJsonRequest();
                    srLayout.setRefreshing(false);
                }
            }
        });
        rvProfession.setLayoutManager(new LinearLayoutManager(getActivity()));
        professionAdapter = new ProfessionAdapter(getActivity());
        rvProfession.setAdapter(professionAdapter);
        if(savedInstanceState!=null){
            professionList = savedInstanceState.getParcelableArrayList(STATE_PROFESSION);
            professionAdapter.setProfessionList(professionList);
        }else {
                sendJsonRequest();
        }
        rvProfession.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvProfession, new RecyclerClassListener() {
            @Override
            public void onClick(View v, int position) {
                Profession profession = professionList.get(position);

                    Intent intentProfessional = new Intent(getActivity(), ProfessionalActivity.class);
                    intentProfessional.putExtra("categoryId", profession.getCategoryId());
                    startActivity(intentProfessional);


            }

            @Override
            public void onLongClick(View v, int position) {

            }

        }));
        return view;
    }

    private void sendJsonRequest(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL.CATEGORIES_API_URL,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        tvVolleyError.setVisibility(View.GONE);
                        parseJSONResponse(response);
                        professionAdapter.setProfessionList(professionList);
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

    private ArrayList<Profession> parseJSONResponse(JSONObject response) {

       professionList = new ArrayList<Profession>();
        if (response != null || response.length() > 0){
            try {

                if (response.has(JsonKeys.categories)) {
                    JSONArray arrayCategories = response.getJSONArray(JsonKeys.categories);

                    for (int i = 0; i < arrayCategories.length(); i++) {
                        JSONObject professionJson = arrayCategories.getJSONObject(i);
                        Profession profession = new Profession();
                        profession.setCategoryId(professionJson.getInt(JsonKeys.categoryId));
                        profession.setJobType(professionJson.getString(JsonKeys.jobName));
                        profession.setUrlImage(professionJson.getString(JsonKeys.urlImage));
                        professionList.add(profession);

                    }
                }

            } catch (JSONException jsonException) {

            }
        }
        return professionList;
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
