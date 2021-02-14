package com.example.marketapp;

import android.widget.Toast;


//ödeme yöntemleri için Strategy Design Pattern
//ilerleyen zamanda farklı ödeme yöntemleri eklenebilir.
//Kullanıcı runtime da ödeme yönteminde değişiklik yapabilir.
public interface Strategy
{
    void odemeYap();
}

class KrediKarti implements Strategy
{
    private String kartNo;

    public KrediKarti(String kartNo)
    {
        this.kartNo=kartNo;
    }

    @Override
    public void odemeYap()
    {
        //ödeme yapılır

        Toast.makeText(MainActivity.context, "Kredi kartı ile ödeme yapıldı", Toast.LENGTH_LONG).show();
    }
}

class BankaKarti implements Strategy
{
    private String kartNo;

    public BankaKarti(String kartNo)
    {
        this.kartNo=kartNo;
    }

    @Override
    public void odemeYap()
    {
        //ödeme yapılır

        Toast.makeText(MainActivity.context, "Banka kartı ile ödeme yapıldı", Toast.LENGTH_LONG).show();
    }
}

class KapidaOdeme implements Strategy
{
    @Override
    public void odemeYap()
    {
        Toast.makeText(MainActivity.context, "İslem tamamlandı", Toast.LENGTH_LONG).show();
    }
}

class OdemeYontemi
{
    private Strategy strategy;

    public OdemeYontemi(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void strategyUygula()
    {
        strategy.odemeYap();
    }
}
