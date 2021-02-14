package com.example.marketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class FeedRvAdapter extends RecyclerView.Adapter<FeedRvAdapter.PostHolder>
{
    public ArrayList<Post> posts;

    public FeedRvAdapter(ArrayList<Post> posts)
    {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.post, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position)
    {
        holder.urunSaticiText.setText(posts.get(position).saticiIsmi);
        holder.urunIsimText.setText(posts.get(position).urunAdi);
        holder.urunAciklamaText.setText(posts.get(position).urunAciklamasi);
        holder.urunFiyatText.setText(String.valueOf(posts.get(position).urunFiyati)+" TL");

        //Eğer post user attığı bir post ise;
        if(posts.get(position).postID==User.getUser().userID)
        {
            holder.urunButonEkle.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount()
    {
        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder
    {
        TextView urunSaticiText, urunIsimText, urunAciklamaText, urunFiyatText;
        ImageView urunFoto;
        Button urunButonEkle;

        public PostHolder(@NonNull View itemView)
        {
            super(itemView);

            urunSaticiText=itemView.findViewById(R.id.saticiText);
            urunFoto=itemView.findViewById(R.id.urunFoto);
            urunFoto.setImageResource(R.drawable.basket_icon);
            urunIsimText=itemView.findViewById(R.id.urunIsim);
            urunAciklamaText=itemView.findViewById(R.id.urunAciklama);
            urunFiyatText=itemView.findViewById(R.id.urunFiyat);
            urunButonEkle=itemView.findViewById(R.id.sepeteEkle);

            urunButonEkle.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)//sepete ekle butonuna tıklandı
                {
                    int index=getLayoutPosition();//tıklanan butonun index i alınır
                    Sepet.getSepet().urunler.add(posts.get(index));//ürün sepete eklenir
                    posts.get(index).observers.add(Sepet.getSepet());//User'ın sepeti, sepete eklenen ürünün observer larından biri olur
                    v.setEnabled(false);
                    Toast.makeText(itemView.getContext(), "Ürün sepete eklendi", Toast.LENGTH_SHORT).show();
                    Sepet.getSepet().sepetTutariHesapla();//Sepet tutarı güncellenir
                }
            });
        }
    }
}
