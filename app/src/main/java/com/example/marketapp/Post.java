package com.example.marketapp;

import android.content.Context;

import java.util.ArrayList;

public class Post
{
    public String postID;//ürünü satışa çıkartan User ın ID si
    public String saticiIsmi;
    public String urunAdi, urunAciklamasi;
    public double urunFiyati;
    public ArrayList<Sepet> observers=new ArrayList<>();//sepetteki ürünlerin fiyatları değiştiğinde user a bildirim vericek(Observer Design Pattern)

    public Post(String postID, String sellerName,String urunAdi, String urunAciklamasi, double urunFiyati)
    {
        this.postID=postID;
        this.saticiIsmi=sellerName;
        this.urunAdi = urunAdi;
        this.urunAciklamasi = urunAciklamasi;
        this.urunFiyati = urunFiyati;
    }

    public void fiyatDegistir(double urunFiyati)
    {
        this.urunFiyati=urunFiyati;
        notifyUsers();
    }

    private void notifyUsers()
    {
        for (Sepet sepet:observers
             )
        {
            sepet.bildirFiyatDegisikligi(this);
        }
    }
}
