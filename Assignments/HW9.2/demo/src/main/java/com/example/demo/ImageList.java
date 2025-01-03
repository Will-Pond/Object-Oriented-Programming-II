package com.example.demo;

import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.nio.file.Paths.get;

public class ImageList {
    static int cardNum;
    static List cardImg = new ArrayList();

    static List valueImg = new ArrayList();

    public static List setCards() {

        for (cardNum=1; cardNum <= 52; cardNum++) {
            String path = "C:\\Users\\wpond\\NKU\\CSC360\\Assignments\\HW9.2\\demo\\src\\main\\java\\card\\" + cardNum + ".png";
            String path1 = path.substring(path.indexOf("\\"),path.indexOf("."));
            cardImg.add(path);
        }

        Collections.shuffle(cardImg);

        return valueImg;
    }


    public static int getNumberValue(int valueImg) {

        int cardValue = 0;


        // Spades
        if (cardNum <= 13) {
            cardValue = cardNum;

            // Hearts
        } else {
            if (cardNum <= 26) {
                cardValue = reduceToValue(cardNum);

                // Diamonds
            } else if (cardNum <= 39) {
                cardValue = reduceToValue(cardNum);

                // Clubs
            } else {
                cardValue = reduceToValue(cardNum);
            }
        }
        return cardValue;
    }

    public static int reduceToValue(int value) {
        /* get value regardless of suite */
        while(value>13){
            value-=13;
        }
        return value;
    }
    public static void main(String[] args) {
        getNumberValue(52);
      setCards();


        }






    }








