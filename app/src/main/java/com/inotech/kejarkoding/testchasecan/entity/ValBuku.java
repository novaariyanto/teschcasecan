package com.inotech.kejarkoding.testchasecan.entity;

import com.google.gson.annotations.SerializedName;

public class ValBuku {
    @SerializedName("BIB_ID")
    String bib_id;
    @SerializedName("judul")
    String judul;
    @SerializedName("pengarang")
    String pengarang;
    @SerializedName("penerbit")
    String penerbit;
    @SerializedName("tahun_terbit")
    String tahun_terbit;
    @SerializedName("stock")
    String stock;
    @SerializedName("tempat")
    String tempat;
    @SerializedName("sinopsis")
    String sinopsis;
    @SerializedName("gambar")
    String gambar;
    public ValBuku(String bib_id,String judul,String pengarang,String penerbit,String tahun_terbit,String stock,String tempat,String sinopsis,String gambar){
        this.bib_id = bib_id;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun_terbit = tahun_terbit;
        this.stock = stock;
        this.tempat = tempat;
        this.sinopsis = sinopsis;
        this.gambar = gambar;
    }

    public String getBib_id() {
        return bib_id;
    }

    public void setBib_id(String bib_id) {
        this.bib_id = bib_id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahun_terbit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(String tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
