package com.example.marketapp;

import android.widget.Toast;


//Belirli durumlarda kulanıcıya farklı kanallardan bilgi vermek için kullanılan Decorator Design Pattern
interface Decorator
{
    void gonder();
}

class Bildirim implements Decorator
{
    @Override
    public void gonder()
    {
        Toast.makeText(MainActivity.context, "Sepetinizdeki bir ürünün fiyatı değişti", Toast.LENGTH_SHORT).show();
    }
}

abstract class BildirimDecorator implements Decorator
{
    protected Decorator decorator;

    public BildirimDecorator(Decorator decorator)
    {
        this.decorator = decorator;
    }
    public void gonder()
    {
        decorator.gonder();
    }
}

//SMS bildirimi;
class Sms extends BildirimDecorator
{
    public Sms(Decorator decorator)
    {
        super(decorator);
    }

    @Override
    public void gonder()
    {
        super.gonder();
        viaSms();
    }
    private void viaSms()
    {
        System.out.println("Sms bildirimi");
    }
}

//Mail bildirimi;
class Mail extends BildirimDecorator
{
    public Mail(Decorator decorator)
    {
        super(decorator);
    }

    @Override
    public void gonder()
    {
        decorator.gonder();
        viaMail();
    }
    private void viaMail()
    {
        System.out.println("Mail bildirimi");
    }
}
