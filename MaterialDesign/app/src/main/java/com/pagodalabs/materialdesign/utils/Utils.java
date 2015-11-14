package com.pagodalabs.materialdesign.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.pagodalabs.materialdesign.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ajit Kumar Baral on 6/12/2015.
 */
public class Utils {

    public static void toastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void testJsonObject(){

        try {
            String testJson = "{\"hello\":\"null\",\"world\":null}";
            JSONObject jsonObject = new JSONObject(testJson);

            String data = jsonObject.getString("hello");
            Log.d("JSON TEST", data);


        }catch (JSONException jsonException){

        }
    }

    public static void handleVolleyErrors(VolleyError error, TextView tvVolleyError){
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
