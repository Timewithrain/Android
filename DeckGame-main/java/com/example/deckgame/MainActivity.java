package com.example.deckgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.net.URL;

import static com.example.deckgame.R.drawable.ic_launcher_background;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    MyAnimation myAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAnimation = new MyAnimation();
//                myAnimation.setRepeatCount(0); //重复旋转0次

        img1 = findViewById(R.id.activity_main_img_img1);
        img1.setOnClickListener(this);
        img2 = findViewById(R.id.activity_main_img_img2);
        img2.setOnClickListener(this);
        img3 = findViewById(R.id.activity_main_img_img3);
        img3.setOnClickListener(this);
        img4 = findViewById(R.id.activity_main_img_img4);
        img4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_img_img1:
                img1.startAnimation(myAnimation);
                img1.setImageResource(R.drawable.ic_launcher_background);
                break;
            case R.id.activity_main_img_img2:
                img1.startAnimation(myAnimation);
                img2.setImageResource(R.drawable.ic_launcher_background);
                break;
            case R.id.activity_main_img_img3:
                img3.startAnimation(myAnimation);
                img3.setImageResource(R.drawable.ic_launcher_background);
                break;
            case R.id.activity_main_img_img4:
                img4.startAnimation(myAnimation);
                img4.setImageResource(R.drawable.ic_launcher_background);
                break;
        }
    }


}
