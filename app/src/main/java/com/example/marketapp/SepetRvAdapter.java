package com.example.marketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SepetRvAdapter extends RecyclerView.Adapter<SepetRvAdapter.UrunHolder>
{
    @NonNull
    @Override
    public UrunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.sepet, parent, false);

        return new UrunHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UrunHolder holder, int position)
    {
        holder.urunSepetText.setText(Sepet.getSepet().urunler.get(position).urunAdi);
        holder.urunFiyatText.setText(String.valueOf(Sepet.getSepet().urunler.get(position).urunFiyati)+" TL");
    }

    @Override
    public int getItemCount()
    {
        return Sepet.getSepet().urunler.size();
    }

    class UrunHolder extends RecyclerView.ViewHolder
    {
        TextView urunSepetText;
        TextView urunFiyatText;
        Button urunKaldirButton;

        public UrunHolder(@NonNull View itemView)
        {
            super(itemView);

            urunSepetText=itemView.findViewById(R.id.urunSepet);
            urunFiyatText=itemView.findViewById(R.id.fiyaturunSepet);
            urunKaldirButton=itemView.findViewById(R.id.urunKaldir);

            urunKaldirButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int index=getLayoutPosition();
                    Sepet.getSepet().urunler.remove(index);
                    SepetFragment.sepetRvAdapter.notifyDataSetChanged();
                    Sepet.getSepet().sepetTutariHesapla();//ürün silindi tekrar tutar hesapla

                    if(Sepet.getSepet().tutar==0)
                    {
                        SepetFragment.satinAlButton.setVisibility(View.INVISIBLE);
                        SepetFragment.tutarView.setVisibility(View.INVISIBLE);
                    }

                    SepetFragment.tutarView.setText("Tutar= "+Sepet.getSepet().tutar+" TL");
                }
            });
        }
    }
}
