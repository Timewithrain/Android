package com.example.deckgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /******version 1****/
//    ImageView img1;
//    ImageView img2;
//    ImageView img3;
//    ImageView img4;
//    MyAnimation myAnimation;

    /******version 2****/
    PlayingDeck deck;
    PlayingCard card;
    MyAnimation myAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        /***************************version 1******************************/
//        myAnimation = new MyAnimation();
//        myAnimation.setRepeatCount(0); //重复旋转0次
//        img1 = findViewById(R.id.activity_main_img_img1);
//        img1.setOnClickListener(this);
//        img2 = findViewById(R.id.activity_main_img_img2);
//        img2.setOnClickListener(this);
//        img3 = findViewById(R.id.activity_main_img_img3);
//        img3.setOnClickListener(this);
//        img4 = findViewById(R.id.activity_main_img_img4);
//        img4.setOnClickListener(this);

        /***************************version 2******************************/
        deck = new PlayingDeck();
        deck.initDeck();
        card = (PlayingCard) deck.randomSelect();
        card.setTextView((TextView) findViewById(R.id.activity_second_tv_card));
        card.getTextView().setOnClickListener(this);
    }

    /***************************version 1******************************/
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.activity_main_img_img1:
//                img1.startAnimation(myAnimation);
//                img1.setImageResource(R.drawable.ic_launcher_background);
//                break;
//            case R.id.activity_main_img_img2:
//                img1.startAnimation(myAnimation);
//                img2.setImageResource(R.drawable.ic_launcher_background);
//                break;
//            case R.id.activity_main_img_img3:
//                img3.startAnimation(myAnimation);
//                img3.setImageResource(R.drawable.ic_launcher_background);
//                break;
//            case R.id.activity_main_img_img4:
//                img4.startAnimation(myAnimation);
//                img4.setImageResource(R.drawable.ic_launcher_background);
//                break;
//        }
//    }

    /***************************version 2******************************/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_second_tv_card:
                card.rotate();
                break;
        }
    }

}
