 package com.example.sepiapetsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

 public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProgressBar progressBar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        Intent intent = getIntent();
        String image = intent.getStringExtra("Image");
        String name = intent.getStringExtra("Name");
        String detail = intent.getStringExtra("Details");

        ImageView petImage = findViewById(R.id.petImage);
        TextView petName = findViewById(R.id.petName);
        WebView details = findViewById(R.id.details);
        progressBar = findViewById(R.id.progressBar);

        Glide.with(this)
                .load(image)
                .fitCenter()
                .into(petImage);




        petName.setText(name);
        details.loadUrl(detail);

        details.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });


    }
}