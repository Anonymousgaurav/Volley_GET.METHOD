package com.example.volley_bike;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.android.volley.toolbox.Volley;
import com.example.volley_bike.Adapter.CustomAdapter;
import com.example.volley_bike.Database.Database;
import com.example.volley_bike.Model.Bike;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.WanderingCubes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rview;
    ArrayList<Bike> bikes = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    RequestQueue mRequestQueue;
    String request_url = "https://rhtvhdthuh.execute-api.ap-south-1.amazonaws.com/dev/bikemodels";
    Database database;
    ArrayList<Bike> arrayList = new ArrayList<>();
    ProgressBar progressBar;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.spin_kit);
        WanderingCubes fc = new WanderingCubes();
        progressBar.setIndeterminateDrawable(fc);


        builder = new AlertDialog.Builder(this);

        database = new Database(getApplicationContext());
        database.getWritableDatabase();

        rview = findViewById(R.id.rview);
        rview.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rview.setLayoutManager(layoutManager);


        mRequestQueue = Volley.newRequestQueue(this);

        checkConnection();

    }

    public void checkConnection() {

        if (isOnline()) {
            sendRequest();
        } else {

            progressBar.setVisibility(View.INVISIBLE);

            arrayList = database.fetchData();
            if (isOffline() && arrayList.size() == 0) {
                builder.setMessage("Please Enable Your Data Connection To Load Your Data. ")
                        .setCancelable(false)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("You Are Not Connected To Internet");
                alertDialog.show();
            } else {

                progressBar.setVisibility(View.INVISIBLE);

                arrayList = database.fetchData();
                mAdapter = new CustomAdapter(MainActivity.this, arrayList);
                rview.setAdapter(mAdapter);
            }
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isOffline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            return true;
        }
        return false;
    }

    private void sendRequest() {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, request_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressBar.setVisibility(View.INVISIBLE);

                            JSONArray jsonArray = response.getJSONArray("Bike");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                Long bikemodel = hit.getLong("BikeModelID");
                                Long brandid = hit.getLong("BrandID");
                                String modelname = hit.getString("ModelName");

                                database.insertData(bikemodel, brandid, modelname);
                                bikes.add(new Bike(bikemodel, brandid, modelname));
                            }

                            arrayList = database.fetchData();
                            mAdapter = new CustomAdapter(MainActivity.this, arrayList);
                            rview.setAdapter(mAdapter);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
    }
}


