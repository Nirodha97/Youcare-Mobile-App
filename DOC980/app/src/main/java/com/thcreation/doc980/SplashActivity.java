package com.thcreation.doc980;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    ImageView botmImg;

    TextView name;

    LottieAnimationView lotti;

    DrawerLayout drawerLayout;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botmImg = findViewById(R.id.img_botm);
        name = findViewById(R.id.appname);
        lotti = findViewById(R.id.lottie);
        drawerLayout = findViewById(R.id.drawer_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                name.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.text_in));
                name.setVisibility(View.VISIBLE);
                botmImg.setVisibility(View.VISIBLE);
                lotti.setVisibility(View.VISIBLE);
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                name.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.txt_out));
                botmImg.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.image_out));
                botmImg.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);
            }
        },4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();

            }
        },5000);

    }
}
