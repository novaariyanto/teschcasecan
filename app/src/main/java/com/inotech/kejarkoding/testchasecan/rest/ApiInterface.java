package com.inotech.kejarkoding.testchasecan.rest;

import com.inotech.kejarkoding.testchasecan.entity.Pesan;
import com.inotech.kejarkoding.testchasecan.entity.ValBuku;
import com.inotech.kejarkoding.testchasecan.entity.Value;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("login.php")
    Call<Value> Login(@Query("username")String username,@Query("password")String password);

    @GET("getDataBuku.php")
    Call<List<ValBuku>>getbukulist();

    @Multipart
    @GET("tambahBuku.php")
    Call<Pesan>tambahbuku(
            @PartMap() Map<String ,String>partMap,
            @Part("cover_buku") MultipartBody.Part file);
}
