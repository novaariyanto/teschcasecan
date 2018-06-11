package com.inotech.kejarkoding.testchasecan.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inotech.kejarkoding.testchasecan.MainActivity;
import com.inotech.kejarkoding.testchasecan.R;
import com.inotech.kejarkoding.testchasecan.entity.Value;
import com.inotech.kejarkoding.testchasecan.rest.ApiClient;
import com.inotech.kejarkoding.testchasecan.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editpin, editnik;
    private Button btnlayanan;

    ApiInterface apiInterface;

    public static final String myprefence = "myprenik";
    public static final String Nik = "nik";
    public SharedPreferences sharedPreferences;
    ProgressDialog pdialogl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(myprefence, Context.MODE_PRIVATE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        editnik = (EditText) findViewById(R.id.edt_nik);
        editpin = (EditText) findViewById(R.id.edt_pin);

        btnlayanan = (Button) findViewById(R.id.btn_Login);
        Log.d("nik","nik"+sharedPreferences.getString(Nik,"").toString());
        //melihat apakah data username sudah pernah login sebelumnya
        //jika sudah maka otomatis masuk ke Catalogactivity
        if(sharedPreferences.getString(Nik,"").length() > 0){
            startActivity(new Intent(LoginActivity.this,CatalogActivity.class));
            finish();
        }
        btnlayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validasi inputan
                if (TextUtils.isEmpty(editnik.getText().toString().trim())) {
                    editnik.setError("Masukkan Username terlebih dahulu !");
                }if (TextUtils.isEmpty(editpin.getText().toString().trim())) {
                    editpin.setError("Masukkan Password terlebih dahulu !");
                }
                if(TextUtils.isEmpty(editnik.getText().toString().trim()) ){}
                else if(TextUtils.isEmpty(editpin.getText().toString().trim())){}
                else {
                    if (checkInternet1()) {
                        pdialogl = new ProgressDialog(LoginActivity.this);
                        pdialogl.setIndeterminate(false);
                        pdialogl.setCancelable(true);
                        pdialogl.show();
                        layananMandiri();
                    } else {
                        Toast.makeText(LoginActivity.this, "Tidak ada sambungan intenet ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void layananMandiri() {

        final Call<Value>valueCall = apiInterface.Login(editnik.getText().toString().trim(),editpin.getText().toString().trim());
        valueCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                pdialogl.dismiss();
                String nama = response.body().getNama_lengkap();
                String msg = response.body().getMessage().toString();
                if (msg.equals("sukses")){
                    Toast.makeText(LoginActivity.this, "Sukses Login sebagai: "+nama.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,CatalogActivity.class));

                    //memsukkan data yg diperoleh dari response ke session 
                        SharedPreferences.Editor edites = sharedPreferences.edit();
                        edites.putString(Nik,response.body().getNama_lengkap().toString());
                        edites.commit();
                    //menyimpan data session
                }else{
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                pdialogl.dismiss();
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    

    @Override
    protected void onRestart() {
        super.onRestart();
        //melihat apakah data username sudah pernah login sebelumnya
        //jika sudah maka otomatis masuk ke Catalogactivity
        if(sharedPreferences.getString(Nik,"").length() > 0){
            startActivity(new Intent(LoginActivity.this,CatalogActivity.class));
            finish();
        }
    }

    public boolean checkInternet1() {
        //validasi jaringan internet
        boolean connectStatus = true;
        ConnectivityManager ConnectionManager = (ConnectivityManager) LoginActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            connectStatus = true;
        } else {
            connectStatus = false;
        }
        return connectStatus;
    }
}
