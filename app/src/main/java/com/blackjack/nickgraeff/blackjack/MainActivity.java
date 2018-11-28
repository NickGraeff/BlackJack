package com.blackjack.nickgraeff.blackjack;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private ConstraintLayout layoutForThisActivity;

    // Logical elements
    private Deck deck;

    private Vector<Integer> cardsOnTableForPlayer;
    private Vector<Integer> cardsOnTableForDealer;

    private Vector<Integer> imageIdsOfCardsOnTableForPlayer;
    private Vector<Integer> imageIdsOfCardsOnTableForDealer;

    private int playerScore;
    private int dealerScore;

    private int pointsWonThisRound;
    private Vector<Integer> playerPoints;
    private int handBeingPlayedByPlayer;
    private int dealerPoints;

    private boolean playersTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display screen
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        ((TextView)findViewById(R.id.textView2)).setText(Integer.toString(0));
        ((TextView)findViewById(R.id.textView4)).setText(Integer.toString(0));
        imageIdsOfCardsOnTableForPlayer = new Vector<Integer>();
        imageIdsOfCardsOnTableForDealer = new Vector<Integer>();

        // Initialize logical elements
        deck = new Deck();
        cardsOnTableForPlayer = new Vector<Integer>();
        cardsOnTableForDealer = new Vector<Integer>();
        playerPoints = new Vector<Integer>();
        dealerPoints = 0;

        resetGame();
    }

    public void hitButtonSelected(View view) {

        int cardDealt = deck.dealAValue();
        ImageView cardImage = new ImageView(getApplicationContext());
        cardImage.setAdjustViewBounds(true);
        cardImage.setId(ViewCompat.generateViewId());
        cardImage.setTag(deck.getLastCardsImageResource());
        cardImage.setImageResource(deck.getLastCardsImageResource());

        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, 0, 0, 0);
        cardImage.setLayoutParams(llp);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);

        if (playersTurn) {
            playerPoints.set(handBeingPlayedByPlayer, playerPoints.elementAt(handBeingPlayedByPlayer) + cardDealt);
            cardsOnTableForPlayer.add(cardDealt);
            TextView playersPointsView = (TextView) findViewById(R.id.textView6);
            playersPointsView.setText(Integer.toString(playerPoints.elementAt(handBeingPlayedByPlayer)));
            imageIdsOfCardsOnTableForPlayer.add((Integer)cardImage.getTag());
            ((LinearLayout)findViewById(R.id.playerCardsLL)).addView(cardImage, layoutParams);
        } else {
            dealerPoints += cardDealt;
            cardsOnTableForDealer.add(cardDealt);
            TextView dealersPointsView = (TextView) findViewById(R.id.textView8);
            dealersPointsView.setText(Integer.toString(dealerPoints));
            imageIdsOfCardsOnTableForDealer.add((Integer)cardImage.getTag());
            ((LinearLayout)findViewById(R.id.dealerCardsLL)).addView(cardImage, layoutParams);
        }

        if (playerPoints.elementAt(handBeingPlayedByPlayer) > 21) {
            standButtonSelected(null);
        }

    }

    public void standButtonSelected(View view) {

        if (playerPoints.elementAt(handBeingPlayedByPlayer) > 21) {
            handBeingPlayedByPlayer += 1;
            Toast.makeText(getApplicationContext(), "Player has busted", Toast.LENGTH_SHORT).show();
            dealerScore += 1;
            ((TextView)findViewById(R.id.textView4)).setText(Integer.toString(dealerScore));
        } else {

            if (handBeingPlayedByPlayer == playerPoints.size() - 1) {
                simulateDealerPlaying();
                SystemClock.sleep(500);
            }

            if (dealerPoints > 21) {
                Toast.makeText(getApplicationContext(), "Dealer has busted", Toast.LENGTH_SHORT).show();
                playerScore += 1;
                ((TextView) findViewById(R.id.textView4)).setText(Integer.toString(playerScore));
                resetGame();
            } else if (playerPoints.elementAt(handBeingPlayedByPlayer) > dealerPoints) {
                Toast.makeText(getApplicationContext(), "Player has won", Toast.LENGTH_SHORT).show();
                playerScore += pointsWonThisRound;
                ((TextView) findViewById(R.id.textView2)).setText(Integer.toString(playerScore));
            } else if (playerPoints.elementAt(handBeingPlayedByPlayer) == dealerPoints) {
                Toast.makeText(getApplicationContext(), "There was a tie", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Dealer has won", Toast.LENGTH_SHORT).show();
                dealerScore += pointsWonThisRound;
                ((TextView) findViewById(R.id.textView4)).setText(Integer.toString(dealerScore));
            }
        }

        handBeingPlayedByPlayer += 1;

        if (handBeingPlayedByPlayer > playerPoints.size()-1) {
            resetGame();
        } else {
            TextView playersPointsView = (TextView) findViewById(R.id.textView6);
            playersPointsView.setText(Integer.toString(playerPoints.elementAt(handBeingPlayedByPlayer)));
        }
    }

    public void doubleDownButtonSelected(View view) {
        pointsWonThisRound = 2;
    }

    public void splitButtonSelected(View view) {
        playerPoints.setElementAt(handBeingPlayedByPlayer, cardsOnTableForPlayer.elementAt(0));
        playerPoints.add(cardsOnTableForPlayer.elementAt(1));
        cardsOnTableForPlayer.removeElementAt(1);
        TextView playersPointsView = (TextView) findViewById(R.id.textView6);
        playersPointsView.setText(Integer.toString(playerPoints.elementAt(handBeingPlayedByPlayer)));

    }

    private void resetGame() {

        // Reset the deck
        deck.reset();

        // Remove all the views we added previously
        for (int i = 0; i < imageIdsOfCardsOnTableForPlayer.size(); ++i) {
            ((LinearLayout)findViewById(R.id.playerCardsLL)).removeAllViews(); //(findViewById(imageIdsOfCardsOnTableForPlayer.elementAt(i)));
        }
        cardsOnTableForPlayer.clear();
        imageIdsOfCardsOnTableForPlayer.clear();

        for (int i = 0; i < imageIdsOfCardsOnTableForDealer.size(); ++i) {
            ((LinearLayout)findViewById(R.id.dealerCardsLL)).removeAllViews(); //(findViewById(imageIdsOfCardsOnTableForDealer.elementAt(i)));
        }
        cardsOnTableForDealer.clear();
        imageIdsOfCardsOnTableForDealer.clear();

        // Reset points
        pointsWonThisRound = 1;
        playerPoints.clear();
        playerPoints.add(0);
        handBeingPlayedByPlayer = 0;
        dealerPoints = 0;

        // Player gets a card
        playersTurn = true;
        hitButtonSelected(null);

        // Dealer gets a card
        playersTurn = false;
        hitButtonSelected(null);

        // Player gets a card
        playersTurn = true;
        hitButtonSelected(null);

        // Dealer gets a card
        playersTurn = false;
        hitButtonSelected(null);

        if (!splitIsPossible()) {
            ((android.widget.Button) findViewById(R.id.button3)).setEnabled(false);
        } else {
            ((android.widget.Button) findViewById(R.id.button3)).setEnabled(true);
        }

        playersTurn = true;

    }

    private boolean splitIsPossible() {
        if (cardsOnTableForPlayer.elementAt(0) == cardsOnTableForPlayer.elementAt(1)) {
            return true;
        }

        return false;
    }

    private void simulateDealerPlaying() {

        playersTurn = false;
        while (dealerPoints < 17) {
            hitButtonSelected(null);
            SystemClock.sleep(500);
        }

    }

}
