package com.example.marketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class SiparisRvAdapter extends RecyclerView.Adapter<SiparisRvAdapter.PostHolder>
{
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.siparis, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position)
    {
        Siparis siparis=User.getUser().siparisler.get(position);
        holder.siparisTutarText.setText(String.valueOf(siparis.siparisTutari+" TL"));
        holder.siparisTarihText.setText(String.valueOf(siparis.siparisTarihi));
    }

    @Override
    public int getItemCount()
    {
        return User.getUser().siparisler.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder
    {
        TextView siparisTarihText, siparisTutarText;

        public PostHolder(@NonNull View itemView)
        {
            super(itemView);

            siparisTarihText=itemView.findViewById(R.id.siparisTarihi);
            siparisTutarText=itemView.findViewById(R.id.siparisTutari);
        }
    }
}
