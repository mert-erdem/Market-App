package com.example.marketapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SepetFragment extends Fragment
{
    RecyclerView sepetRecyclerView;
    public static SepetRvAdapter sepetRvAdapter;
    public static TextView tutarView;
    public static Button satinAlButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_sepet, container, false);

        tutarView =view.findViewById(R.id.tutar);
        satinAlButton=view.findViewById(R.id.bsatinAl);

        if(Sepet.getSepet().tutar==0)
        {
            tutarView.setVisibility(View.INVISIBLE);
            satinAlButton.setVisibility(View.INVISIBLE);


        }

        if(Sepet.getSepet().urunler.size()>0)
        {
            tutarView.setText("Tutar= "+Sepet.getSepet().tutar+" TL");
            satinAlButton.setVisibility(View.VISIBLE);
        }
        else
        {
            satinAlButton.setVisibility(View.INVISIBLE);
        }


        sepetRecyclerView=view.findViewById(R.id.sepetRecyclerView);
        sepetRecyclerView.setHasFixedSize(true);
        sepetRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        sepetRvAdapter =new SepetRvAdapter();

        sepetRecyclerView.setAdapter(sepetRvAdapter);

        sepetRvAdapter.notifyDataSetChanged();


        satinAlButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Ödeme ekranı açılıyor;
                Fragment odemeFragment=new OdemeFragment();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_space, odemeFragment).commit();
            }
        });

        return view;
    }





}