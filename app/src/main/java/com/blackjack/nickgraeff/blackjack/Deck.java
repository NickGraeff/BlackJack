package com.blackjack.nickgraeff.blackjack;

import android.media.Image;

import java.util.Arrays;
import java.util.Random;

public class Deck {

    // Spades
    public static final int S1 = 0;
    public static final int S2 = 1;
    public static final int S3 = 2;
    public static final int S4 = 3;
    public static final int S5 = 4;
    public static final int S6 = 5;
    public static final int S7 = 6;
    public static final int S8 = 7;
    public static final int S9 = 8;
    public static final int S10 = 9;
    public static final int SJ = 10;
    public static final int SQ = 11;
    public static final int SK = 12;

    // Clubs
    public static final int C1 = 13;
    public static final int C2 = 14;
    public static final int C3 = 15;
    public static final int C4 = 16;
    public static final int C5 = 17;
    public static final int C6 = 18;
    public static final int C7 = 19;
    public static final int C8 = 20;
    public static final int C9 = 21;
    public static final int C10 = 22;
    public static final int CJ = 23;
    public static final int CQ = 24;
    public static final int CK = 25;

    // Hearts
    public static final int H1 = 26;
    public static final int H2 = 27;
    public static final int H3 = 28;
    public static final int H4 = 29;
    public static final int H5 = 30;
    public static final int H6 = 31;
    public static final int H7 = 32;
    public static final int H8 = 33;
    public static final int H9 = 34;
    public static final int H10 = 35;
    public static final int HJ = 36;
    public static final int HQ = 37;
    public static final int HK = 38;

    // Diamonds
    public static final int D1 = 39;
    public static final int D2 = 40;
    public static final int D3 = 41;
    public static final int D4 = 42;
    public static final int D5 = 43;
    public static final int D6 = 44;
    public static final int D7 = 45;
    public static final int D8 = 46;
    public static final int D9 = 47;
    public static final int D10 = 48;
    public static final int DJ = 49;
    public static final int DQ = 50;
    public static final int DK = 51;

    private boolean[] cardsDealt;
    private int cardPicked;
    private Random rand;

    Deck() {
        rand = new Random();
        cardsDealt = new boolean[52]; // Java initializes this to false by default
    }

    public int dealAValue() {
        do {
            cardPicked = rand.nextInt(52);
        } while (cardsDealt[cardPicked] == true);

        return (cardPicked % 13 + 1 > 10) ? 10 : cardPicked % 13 + 1;
    }

    public void reset() {
        Arrays.fill(cardsDealt, false);
    }

    public int getLastCardNumber() {
        return (cardPicked % 13 + 1);
    }

    public int getLastCardsImageResource() {
        int returnVal = -1;
        switch (cardPicked) {
            case S1:
                returnVal = R.drawable.ace_of_spades;
                break;
            case S2:
                returnVal = R.drawable.two_of_spades;
                break;
            case S3:
                returnVal = R.drawable.three_of_spades;
                break;
            case S4:
                returnVal = R.drawable.four_of_spades;
                break;
            case S5:
                returnVal = R.drawable.five_of_spades;
                break;
            case S6:
                returnVal = R.drawable.six_of_spades;
                break;
            case S7:
                returnVal = R.drawable.seven_of_spades;
                break;
            case S8:
                returnVal = R.drawable.eight_of_spades;
                break;
            case S9:
                returnVal = R.drawable.nine_of_spades;
                break;
            case S10:
                returnVal = R.drawable.ten_of_spades;
                break;
            case SJ:
                returnVal = R.drawable.jack_of_spades;
                break;
            case SQ:
                returnVal = R.drawable.queen_of_spades;
                break;
            case SK:
                returnVal = R.drawable.king_of_spades;
                break;

            case C1:
                returnVal = R.drawable.ace_of_clubs;
                break;
            case C2:
                returnVal = R.drawable.two_of_clubs;
                break;
            case C3:
                returnVal = R.drawable.three_of_clubs;
                break;
            case C4:
                returnVal = R.drawable.four_of_clubs;
                break;
            case C5:
                returnVal = R.drawable.five_of_clubs;
                break;
            case C6:
                returnVal = R.drawable.six_of_clubs;
                break;
            case C7:
                returnVal = R.drawable.seven_of_clubs;
                break;
            case C8:
                returnVal = R.drawable.eight_of_clubs;
                break;
            case C9:
                returnVal = R.drawable.nine_of_clubs;
                break;
            case C10:
                returnVal = R.drawable.ten_of_clubs;
                break;
            case CJ:
                returnVal = R.drawable.jack_of_clubs;
                break;
            case CQ:
                returnVal = R.drawable.queen_of_clubs;
                break;
            case CK:
                returnVal = R.drawable.king_of_clubs;
                break;

            case H1:
                returnVal = R.drawable.ace_of_hearts;
                break;
            case H2:
                returnVal = R.drawable.two_of_hearts;
                break;
            case H3:
                returnVal = R.drawable.three_of_hearts;
                break;
            case H4:
                returnVal = R.drawable.four_of_hearts;
                break;
            case H5:
                returnVal = R.drawable.five_of_hearts;
                break;
            case H6:
                returnVal = R.drawable.six_of_hearts;
                break;
            case H7:
                returnVal = R.drawable.seven_of_hearts;
                break;
            case H8:
                returnVal = R.drawable.eight_of_hearts;
                break;
            case H9:
                returnVal = R.drawable.nine_of_hearts;
                break;
            case H10:
                returnVal = R.drawable.ten_of_hearts;
                break;
            case HJ:
                returnVal = R.drawable.jack_of_hearts;
                break;
            case HQ:
                returnVal = R.drawable.queen_of_hearts;
                break;
            case HK:
                returnVal = R.drawable.king_of_hearts;
                break;

            case D1:
                returnVal = R.drawable.ace_of_diamonds;
                break;
            case D2:
                returnVal = R.drawable.two_of_diamonds;
                break;
            case D3:
                returnVal = R.drawable.three_of_diamonds;
                break;
            case D4:
                returnVal = R.drawable.four_of_diamonds;
                break;
            case D5:
                returnVal = R.drawable.five_of_diamonds;
                break;
            case D6:
                returnVal = R.drawable.six_of_diamonds;
                break;
            case D7:
                returnVal = R.drawable.seven_of_diamonds;
                break;
            case D8:
                returnVal = R.drawable.eight_of_diamonds;
                break;
            case D9:
                returnVal = R.drawable.nine_of_diamonds;
                break;
            case D10:
                returnVal = R.drawable.ten_of_diamonds;
                break;
            case DJ:
                returnVal = R.drawable.jack_of_diamonds;
                break;
            case DQ:
                returnVal = R.drawable.queen_of_diamonds;
                break;
            case DK:
                returnVal = R.drawable.king_of_diamonds;
                break;

            default:
                System.out.println("Invalid card picked.");
                System.exit(1);
                break;
        }

        return returnVal;
    }
}

