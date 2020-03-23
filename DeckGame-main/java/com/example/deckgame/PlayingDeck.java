package com.example.deckgame;

import java.util.ArrayList;
import java.util.List;

public class PlayingDeck extends Deck {

    private List<PlayingCard> cards = new ArrayList<>();
    private String[] valid = {"♠ ","♣ ","♥ ","♦ "};
    private String[] rank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private String[] jocker = {"`@`","^@^"};

    @Override
    public void initDeck() {
        for (int i=0;i<4;i++){
            for (int j=0;j<13;j++){
                PlayingCard card = new PlayingCard();
                card.setForeground(rank[j]+valid[i]);
                cards.add(card);
            }
        }
        cards.add(new PlayingCard(jocker[0]));
        cards.add(new PlayingCard(jocker[1]));
    }

    @Override
    public Card randomSelect() {
        int random = (int)(Math.random()*(51-0+1));
        PlayingCard card = cards.get(random);
        card.setMyAnimation(new MyAnimation());
        return card;
    }
}
