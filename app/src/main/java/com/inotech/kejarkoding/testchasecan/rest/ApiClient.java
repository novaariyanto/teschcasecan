package com.inotech.kejarkoding.testchasecan.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
   public static final String base_url ="https://spooker.000webhostapp.com/api/perpustakaan/";
   private static Retrofit retrofit= null;
   public static Retrofit getClient() {
      if(retrofit== null){
         retrofit = new Retrofit.Builder()
                 .baseUrl(base_url)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
      }
      return retrofit;
   }


}
