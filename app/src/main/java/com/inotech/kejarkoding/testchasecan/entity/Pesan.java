package com.inotech.kejarkoding.testchasecan.entity;

import com.google.gson.annotations.SerializedName;

public class Pesan {
    @SerializedName("kodePesan")
    String kodepesan;
    @SerializedName("pesan")
    String pesan;
    public Pesan(String kodepesan,String pesan){
        this.kodepesan = kodepesan;
        this.pesan = pesan;
    }
    public String getKodepesan() {
        return kodepesan;
    }

    public String getPesan() {
        return pesan;
    }
}
