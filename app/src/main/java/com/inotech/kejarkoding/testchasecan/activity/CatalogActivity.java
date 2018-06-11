package com.inotech.kejarkoding.testchasecan.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.inotech.kejarkoding.testchasecan.MainActivity;
import com.inotech.kejarkoding.testchasecan.R;
import com.inotech.kejarkoding.testchasecan.adapter.bukuadapter;
import com.inotech.kejarkoding.testchasecan.entity.ValBuku;
import com.inotech.kejarkoding.testchasecan.entity.Value;
import com.inotech.kejarkoding.testchasecan.rest.ApiClient;
import com.inotech.kejarkoding.testchasecan.rest.ApiInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CatalogActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    List<ValBuku> valBukus = null;
    ApiInterface apiInterface;
    bukuadapter bukuadapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        getbukuList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CatalogActivity.this,AddBookActivity.class));
            }
        });


    }

    private void getbukuList() {
        try{
            Call<List<ValBuku>> call = apiInterface.getbukulist();
            call.enqueue(new Callback<List<ValBuku>>() {
                @Override
                public void onResponse(Call<List<ValBuku>> call, retrofit2.Response<List<ValBuku>> response) {
                    valBukus = response.body();
                    Log.d("123","user list response body");

                    RecyclerView recyclerView  =(RecyclerView)findViewById(R.id.rc_buku);
                    bukuadapters = new bukuadapter(getApplicationContext(),valBukus);
                    layoutManager = new LinearLayoutManager(CatalogActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    recyclerView.setAdapter(bukuadapters);

                }

                @Override
                public void onFailure(Call<List<ValBuku>> call, Throwable t) {
                Log.d("123",t.getMessage());
                }
            });
        }catch (Exception e){
            Log.d("1234","Exception");
        }
    }
}