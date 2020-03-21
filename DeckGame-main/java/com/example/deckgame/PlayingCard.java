package com.example.deckgame;

import android.widget.ImageView;

public class PlayingCard extends Card {

    private int foreground;
    private int background;
    private ImageView imageView;
    private MyAnimation myAnimation;

    public PlayingCard(){
        foreground = R.drawable.ic_launcher_foreground;
        background = R.drawable.ic_launcher_foreground;
    }

    @Override
    public void rotate() {

    }
}
