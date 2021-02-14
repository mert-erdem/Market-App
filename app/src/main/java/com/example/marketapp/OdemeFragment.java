package com.example.marketapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class OdemeFragment extends Fragment
{
    TextView adresText;
    Button siparisiTamamlaButton;
    RadioGroup odemeSecenekleriRadioG;
    RadioButton radioButton;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.fragment_odeme, container, false);

        adresText=view.findViewById(R.id.adresText);
        siparisiTamamlaButton =view.findViewById(R.id.siparisiTamamla);
        odemeSecenekleriRadioG =view.findViewById(R.id.odemeSecenekleri);

        siparisiTamamlaButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!TextUtils.isEmpty(adresText.getText().toString()) && odemeSecenekleriRadioG.getCheckedRadioButtonId()!=-1)
                {
                    odemeYap();//Odeme yapılıyor
                }
                else
                {
                    Toast.makeText(MainActivity.context, "Lütfen gerekli bilgileri giriniz", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    //Strategy Design Pattern kullanımı;
    private void odemeYap()
    {
        int id= odemeSecenekleriRadioG.getCheckedRadioButtonId();

        radioButton=view.findViewById(id);

        OdemeYontemi odemeYontemi = null;

        switch (radioButton.getText().toString())
        {
            case "Kredi kartı":
                odemeYontemi=new OdemeYontemi(new KrediKarti("000"));
                break;
            case "Banka kartı":
                odemeYontemi=new OdemeYontemi(new BankaKarti("000"));
                break;
            case "Kapıda ödeme":
                odemeYontemi=new OdemeYontemi(new KapidaOdeme());
                break;
        }
        odemeYontemi.strategyUygula();
        odemeEkIslemler();
    }
    private void odemeEkIslemler()
    {
        //Sipariş kayıt ediliyor;
        Siparis siparis=new Siparis(adresText.getText().toString(), Sepet.getSepet().tutar,
                Sepet.getSepet().urunler);
        User.getUser().siparisler.add(siparis);//User ın siparişleri

        //Sepeti temizle;
        Sepet.getSepet().urunler.clear();
        Sepet.getSepet().tutar=0;

        Fragment sepetFragment=new SepetFragment();
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_space, sepetFragment).commit();
    }
}