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
import android.widget.TextView;
import android.widget.Toast;


public class AddPostFragment extends Fragment
{
    TextView urunAdiText, urunAciklamasiText, urunFiyatiText;
    Button urunSatButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_add_post, container, false);

        urunAdiText=view.findViewById(R.id.addUrunAdi);
        urunAciklamasiText=view.findViewById(R.id.addUrunAciklamasi);
        urunFiyatiText=view.findViewById(R.id.addUrunFiyati);
        urunSatButton=view.findViewById(R.id.satisYapButton);

        urunSatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkInputs())
                {
                    //postID=userID;
                    Post newPost=new Post(User.getUser().userID, User.getUser().userName,urunAdiText.getText().toString(), urunAciklamasiText.getText().toString(),
                            Double.parseDouble(urunFiyatiText.getText().toString()));

                    ProxySatici proxySatici =new ProxySatici(User.getUser());//Kullanıcının satış lisansı
                    //var ise satış yapabilir;
                    proxySatici.satisYap(newPost);

                    Fragment feedFragment=new FeedFragment();
                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_space, feedFragment).commit();
                }
                else
                {
                    Toast.makeText(MainActivity.context, "Lütfen gerekli alanları doldurunuz(Ürün adı-fiyatı)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    //Ürün adı ve fiyatı boş olamaz
    private boolean checkInputs()
    {
        if(TextUtils.isEmpty(urunAdiText.getText().toString()) || TextUtils.isEmpty(urunFiyatiText.getText().toString()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}