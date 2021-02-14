package com.example.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;
    private ArrayList<Post> posts;
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FeedFragment.fiyatDegisikligi();

        context=getApplicationContext();

        posts=new ArrayList<>();
        addPosts();//Başka user ların hardcoded olarak sisteme ürün eklemesi

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_space,new FeedFragment()).commit();
        navigationItemOnClick();
    }


    private void navigationItemOnClick(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch(item.getItemId()){
                    case R.id.page_store:
                        selectedFragment = new FeedFragment();
                         break;
                    case R.id.page_basket:
                        selectedFragment = new SepetFragment();
                        break;
                    case R.id.page_addPost:
                        selectedFragment=new AddPostFragment();
                        break;
                    case R.id.page_profile:
                        selectedFragment = new ProfilFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_space,selectedFragment).commit();
                return true;
            }
        });
    }


    public void addPosts()
    {
        Post post=new Post("0001", "Seller Name","Samsung S10", "Ürün açıklaması, teknik özellikleri vb.", 3250);
        posts.add(post);
        Post post1=new Post("0002", "Seller Name","iPhone 8", "Ürün açıklaması, teknik özellikleri vb.", 3500);
        posts.add(post1);
        Post post2=new Post("0003", "Seller Name","iPhone X", "Ürün açıklaması, teknik özellikleri vb.", 4750);
        posts.add(post2);
        Post post3=new Post("0004", "Seller Name","Huawei p40", "Ürün açıklaması, teknik özellikleri vb.", 7000);
        posts.add(post3);
        Post post4=new Post("0005", "Seller Name","iPhone 12", "Ürün açıklaması, teknik özellikleri vb.", 11000);
        posts.add(post4);
        Post post5=new Post("0006", "Seller Name","Macbook Air", "Ürün açıklaması, teknik özellikleri vb.", 8000);
        posts.add(post5);

        FeedFragment.posts=this.posts;
    }

}