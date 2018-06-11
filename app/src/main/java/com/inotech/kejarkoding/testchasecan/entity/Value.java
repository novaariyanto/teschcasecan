package com.inotech.kejarkoding.testchasecan.entity;

import com.google.gson.annotations.SerializedName;

public class Value {
    @SerializedName("message")
    String message;
    @SerializedName("id_user")
    String id_user;
    @SerializedName("username")
    String username;
    @SerializedName("nama_lengkap")
    String nama_lengkap;

    public String getMessage() {
        return message;
    }

    public String getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }
}
