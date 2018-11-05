package com.blackjack.nickgraeff.blackjack;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import java.lang.invoke.ConstantCallSite;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Deck deck;

    private Vector<Integer> cardsOnTableForPlayer;
    private Vector<Integer> cardsOnTableForDealer;

    private Vector<ImageView> imagesOfCardsOnTableForPlayer;
    private Vector<ImageView> imagesOfCardsOnTableForDealer;

    private int playerScore;
    private int dealerScore;

    private int playerPoints;
    private int dealerPoints;

    private boolean playersTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deck = new Deck();
        imagesOfCardsOnTableForPlayer = new Vector<ImageView>();
        imagesOfCardsOnTableForDealer = new Vector<ImageView>();
        cardsOnTableForPlayer = new Vector<Integer>();
        cardsOnTableForDealer = new Vector<Integer>();
        playerScore = 0;
        dealerScore = 0;
        playerPoints = 0;
        dealerPoints = 0;
        playersTurn = true;

        setContentView(R.layout.activity_main);

        // Get the layout for the grid and set the rows and cols
        GridLayout playersGridLayout = (GridLayout) findViewById(R.id.gridLayoutForPlayer);
        GridLayout dealersGridLayout = (GridLayout) findViewById(R.id.gridLayoutForDealer);
        dealersGridLayout.setColumnCount(11);
        playersGridLayout.setColumnCount(11);
        dealersGridLayout.setRowCount(2);
        playersGridLayout.setRowCount(2);

        resetGame();
    }

    public void hitButtonSelected() {

        // Get the layout for the grid and set the rows and cols
        GridLayout dealersGridLayout = (GridLayout) findViewById(R.id.gridLayoutForDealer);
        GridLayout playersGridLayout = (GridLayout) findViewById(R.id.gridLayoutForDealer);

        int cardDealt = deck.dealACard();
        ImageView cardImage = new ImageView(getApplicationContext());
        cardImage.setImageResource(deck.getLastCardsImageResource());

        if (playersTurn) {
            playerPoints += cardDealt;
            TextView playersPointsView = (TextView) findViewById(R.id.textView5);
            playersPointsView.setText(Integer.toString(playerPoints));
            imagesOfCardsOnTableForPlayer.add(cardImage);
            playersGridLayout.addView(cardImage);
        } else {
            dealerPoints += cardDealt;
            TextView dealersPointsView = (TextView) findViewById(R.id.textView7);
            dealersPointsView.setText(Integer.toString(dealerPoints));
            imagesOfCardsOnTableForDealer.add(cardImage);
            dealersGridLayout.addView(cardImage);
        }
        handleCurrentStateOfGame();
    }

    public void standButtonSelected() {
        simulateDealerPlaying();
        handleCurrentStateOfGame();
    }

    public void doubleDownButtonSelected() {

        handleCurrentStateOfGame();
    }

    public void splitButtonSelected() {

        handleCurrentStateOfGame();
    }

    private void handleCurrentStateOfGame() {

    }

    private void resetGame() {

        // Get the layout for the grid
        GridLayout dealersGridLayout = (GridLayout) findViewById(R.id.gridLayoutForDealer);
        GridLayout playersGridLayout = (GridLayout) findViewById(R.id.gridLayoutForDealer);

        // Reset the deck
        deck.reset();

        // Remove all the views we added previously
        for (int i = 0; i < imagesOfCardsOnTableForPlayer.size(); ++i) {
            playersGridLayout.removeView(imagesOfCardsOnTableForPlayer.elementAt(i));
        }

        for (int i = 0; i < imagesOfCardsOnTableForDealer.size(); ++i) {
            dealersGridLayout.removeView(imagesOfCardsOnTableForDealer.elementAt(i));
        }

        // Reset points
        playerPoints = 0;
        dealerPoints = 0;

        // Player gets a card
        playersTurn = true;
        hitButtonSelected();

        // Dealer gets a card
        playersTurn = false;
        hitButtonSelected();

        // Player gets a card
        playersTurn = true;
        hitButtonSelected();

        // Dealer gets a card
        playersTurn = false;
        hitButtonSelected();

    }

    private void simulateDealerPlaying() {

    }

}
