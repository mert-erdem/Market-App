package com.example.marketapp;

import android.widget.Toast;

//Proxy Design Pattern; sadece belirli koşulara uyan kullanıcılar satış yapabilir(gerçek yapıyla doğrudan iletişim kurmadan).
interface ISatici
{
    void satisYap(Post newPost);
}

class Satici implements ISatici
{
    @Override
    public void satisYap(Post newPost)
    {
        User.getUser().userPosts.add(newPost);//user ın kendi postlarına eklenir
        FeedFragment.posts.add(newPost);
        FeedFragment.feedRvAdapter.notifyDataSetChanged();


        Toast.makeText(MainActivity.context, "İşlem başarılı", Toast.LENGTH_LONG).show();
    }
}

class ProxySatici implements ISatici
{
    private ISatici satici =new Satici();
    private User user;

    public ProxySatici(User user)
    {
        this.user=user;
    }

    @Override
    public void satisYap(Post newPost)
    {
        if(user.saticiLisansi==true)//kullanıcı satıcı lisansına sahip ise
        {
            this.satici.satisYap(newPost);
        }
        else
        {
            Toast.makeText(MainActivity.context, "Lütfen profil sayfasından satıcı lisansı alınız", Toast.LENGTH_LONG).show();
        }
    }
}
