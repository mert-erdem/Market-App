package com.example.marketapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfilFragment extends Fragment
{
    Button bsaticiOl;
    TextView textViewIsim, textViewMail, textViewTel;
    RecyclerView siparislerRV;
    public static SiparisRvAdapter siparisRvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);



        textViewIsim=view.findViewById(R.id.textViewIsim);
        textViewTel=view.findViewById(R.id.textViewTel);
        textViewMail=view.findViewById(R.id.textViewMail);

        siparislerRV=view.findViewById(R.id.siparislerRv);
        siparislerRV.setHasFixedSize(true);
        siparislerRV.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        siparisRvAdapter=new SiparisRvAdapter();
        siparislerRV.setAdapter(siparisRvAdapter);
        siparisRvAdapter.notifyDataSetChanged();

        textViewIsim.setText(textViewIsim.getText()+User.getUser().userName);
        textViewMail.setText(textViewMail.getText()+User.getUser().userMail);
        textViewTel.setText(textViewTel.getText()+User.getUser().userTel);

        bsaticiOl=view.findViewById(R.id.bsaticiOl);

        if(User.getUser().saticiLisansi==true)
        {
            bsaticiOl.setEnabled(false);
        }

        bsaticiOl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //satici ol
                User.getUser().saticiLisansi=true;//Kullanıcı satıcı lisansını aldı
                bsaticiOl.setEnabled(false);//satıcı olduktan sonra buton kapanır
            }
        });


        return view;
    }
}