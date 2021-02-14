package com.example.marketapp;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Siparis
{
    private UUID siparisID;//random olarak belirlenen unique id
    public String siparisAdresi;
    public double siparisTutari;
    public Date siparisTarihi;
    public ArrayList<Post> siparisUrunleri;

    @SuppressLint("NewApi")
    public Siparis(String siparisAdresi, double siparisTutari,
                   ArrayList<Post> siparisUrunleri)
    {
        this.siparisID=UUID.randomUUID();
        this.siparisUrunleri=siparisUrunleri;
        this.siparisAdresi=siparisAdresi;
        this.siparisTutari=siparisTutari;
        this.siparisTarihi= Calendar.getInstance().getTime();
    }
}
