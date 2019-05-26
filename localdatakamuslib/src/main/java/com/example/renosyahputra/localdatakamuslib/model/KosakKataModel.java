package com.example.renosyahputra.localdatakamuslib.model;

import java.io.Serializable;

public class KosakKataModel implements Serializable {

    public String BahasaInggris = "";
    public String keteranganBahasaInggris = "";

    public String BahasaIndonesia = "";
    public String keteranganBahasaIndonesia = "";

    public String BahasaMandarin = "";
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
