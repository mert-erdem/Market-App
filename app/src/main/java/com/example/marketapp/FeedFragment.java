package com.example.marketapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class FeedFragment extends Fragment
{
    RecyclerView feedrecyclerView;
    public static FeedRvAdapter feedRvAdapter;
    public static ArrayList<Post> posts;
    private static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.fragment_feed, container, false);

        feedrecyclerView=view.findViewById(R.id.feedrecyclerView);
        feedrecyclerView.setHasFixedSize(true);
        feedrecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        feedRvAdapter=new FeedRvAdapter(posts);
        feedrecyclerView.setAdapter(feedRvAdapter);

        return view;
    }


    //bir satıcının ürününün bir zaman sonra fiyatını değiştirdiğini varsayalım; bu ürünü halihazırda sepetine eklemiş bir müşteri
    //bildirim almalıdır;
    public static void fiyatDegisikligi()
    {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                posts.get(0).fiyatDegistir(5000);//observer
                FeedFragment.feedRvAdapter.notifyDataSetChanged();
                Sepet.getSepet().sepetTutariHesapla();
            }
        }, 10000);
    }


}