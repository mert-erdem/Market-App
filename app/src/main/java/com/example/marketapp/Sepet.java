package com.example.marketapp;

import android.content.Context;

import java.util.ArrayList;

//Eager Singleton;
public class Sepet
{
    private String sepetID=User.getUser().userID;//bir user ın bir sepeti
    public ArrayList<Post> urunler=new ArrayList<>();
    public double tutar;

    private static Sepet sepet=new Sepet();

    private Sepet() { }

    public static Sepet getSepet()
    {
        return sepet;
    }

    //Post sınıfındaki notifyUsers() metodundan çağırılan metod(Observer);
    public void bildirFiyatDegisikligi(Post degisenPost)
    {
        //Başta default olarak Toast mesajı gösterilir;
        Decorator decorator =new Bildirim();

        Decorator sms=new Sms(decorator);
        Decorator mail=new Mail(decorator);

        sms.gonder();
        mail.gonder();
    }

    public  void sepetTutariHesapla()
    {
        double sepetTutari=0;

        for (Post p:Sepet.getSepet().urunler
        )
        {
            sepetTutari+=p.urunFiyati;
        }

        tutar=sepetTutari;
    }
}
