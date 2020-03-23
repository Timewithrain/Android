package com.example.deckgame;

import android.widget.TextView;

public class PlayingCard extends Card {

    private String foreground;
    private String background;
    private TextView textView;
    private MyAnimation myAnimation;

    public PlayingCard(){
        foreground = "";
        background = "Poker";
    }

    public PlayingCard(String str){
        foreground = str;
        background = "Poker";
    }

    @Override
    public void rotate() {
        if (textView!=null){
            textView.startAnimation(myAnimation);
            textView.setText(foreground);
        }

    }
    public TextView getTextView(){
        return this.textView;
    }

    public void setTextView(TextView textView){
        this.textView = textView;
        this.textView.setText(background);
    }

    public String getForeground(){
        return this.foreground;
    }

    public void setForeground(String foreground){
        this.foreground = foreground;
    }

    public String getBackground(){
        return this.background;
    }

    public void setBackground(String background){
        this.background = background;
    }

    public void setMyAnimation(MyAnimation animation){
        this.myAnimation = animation;
    }
}
