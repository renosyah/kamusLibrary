package com.example.renosyahputra.localdatakamuslib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KosakKataModel implements Serializable {

    @SerializedName("bahasa_inggris")
    @Expose
    public String BahasaInggris = "";

    @SerializedName("keterangan_bahasa_inggris")
    @Expose
    public String keteranganBahasaInggris = "";

    @SerializedName("bahasa_indonesia")
    @Expose
    public String BahasaIndonesia = "";

    @SerializedName("keterangan_bahasa_indonesia")
    @Expose
    public String keteranganBahasaIndonesia = "";

    @SerializedName("bahasa_mandarin")
    @Expose
    public String BahasaMandarin = "";

    @SerializedName("keterangan_bahasa_mandarin")
    @Expose
    public String KeteranganBahasaMandarin = "";

    public KosakKataModel() {
        super();
    }

    public KosakKataModel(String bahasaInggris, String bahasaIndonesia) {
        this.BahasaInggris = bahasaInggris;
        this.BahasaIndonesia = bahasaIndonesia;
    }

    public KosakKataModel(String bahasaInggris, String bahasaIndonesia, String bahasaMandarin) {
        this.BahasaInggris = bahasaInggris;
        this.BahasaIndonesia = bahasaIndonesia;
        this.BahasaMandarin = bahasaMandarin;
    }

    public KosakKataModel(String bahasaInggris, String keteranganBahasaInggris, String bahasaIndonesia, String keteranganBahasaIndonesia) {
        this.BahasaInggris = bahasaInggris;
        this.keteranganBahasaInggris = keteranganBahasaInggris;
        this.BahasaIndonesia = bahasaIndonesia;
        this.keteranganBahasaIndonesia = keteranganBahasaIndonesia;
    }

    public KosakKataModel(String bahasaInggris, String keteranganBahasaInggris, String bahasaIndonesia, String keteranganBahasaIndonesia, String bahasaMandarin, String keteranganBahasaMandarin) {
        BahasaInggris = bahasaInggris;
        this.keteranganBahasaInggris = keteranganBahasaInggris;
        BahasaIndonesia = bahasaIndonesia;
        this.keteranganBahasaIndonesia = keteranganBahasaIndonesia;
        BahasaMandarin = bahasaMandarin;
        KeteranganBahasaMandarin = keteranganBahasaMandarin;
    }
}
