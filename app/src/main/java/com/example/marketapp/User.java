package com.example.marketapp;

import android.content.Context;
import android.view.ViewParent;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class User
{
    public final String userID="0020";//user ın authentication id si
    public final String userName , userMail, userTel;
    public ArrayList<Post> userPosts=new ArrayList<>();//user ın kendi paylaştığı post lar
    public ArrayList<Siparis> siparisler =new ArrayList<>();//user ın siparişleri
    public boolean saticiLisansi;


    //Eager Singleton
    //Uygulamayı mevcut cihazda kullanan sadece bir adet user kullanabilir.
    private static final User user= new User("Mert Erdem", "user2021@user.com", "500 000 0000");

    private User(String userName, String userMail, String userTel)
    {
        this.userName = userName;
        this.userMail=userMail;
        this.userTel=userTel;
        this.saticiLisansi=false;//Kullanıcının başlangıçta satıcı lisansı yok
    }

    public static User getUser()
    {
        return user;
    }
}

