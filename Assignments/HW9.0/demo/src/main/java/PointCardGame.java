package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.Stack;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PointCardGame extends Application {

    String[] cards;
    int[] picked = new int[4];
    int pick = 0;


    public void start(Stage primaryStage) throws Exception {

        //HBoxes and VBoxes

        VBox vbox = new VBox();
        VBox BottomBox = new VBox(10);
        HBox CardBox = new HBox();
        HBox ToolBarBottom = new HBox(15);
        HBox ButtonBarTop = new HBox(10);
        vbox.setFillWidth(true);
        VBox.setMargin(CardBox, new Insets(10, 20, 20, 10));

        //SimpleStringProperty

        Text resultText = new Text("");
        SimpleStringProperty message = new SimpleStringProperty();
        message.bind(resultText.textProperty());


        Image a1 = new Image(cards[randomCard()]);
        Image b2 = new Image(cards[randomCard()]);
        Image c3 = new Image(cards[randomCard()]);
        Image d4 = new Image(cards[randomCard()]);

        ImageView card1 = new ImageView(a1);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card2 = new ImageView(b2);
        card2.setPreserveRatio(true);
        card2.setFitHeight(300);

        ImageView card3 = new ImageView(c3);
        card3.setPreserveRatio(true);
        card3.setFitHeight(300);

        ImageView card4 = new ImageView(d4);
        card4.setPreserveRatio(true);
        card4.setFitHeight(300);

        TextField inputField = new TextField();
        inputField.setMinWidth(200);

        TextField findSTF = new TextField();
        findSTF.setPrefWidth(150);

        Button shuffleButton = new Button("Shuffle");
        /**
         * shuffleButton.setOnAction(e -> {
         *             resultText.setText("");
         *             inputField.setText("");
         *             shuffleCards(CardBox);
         *             findSTF.setText("");
         *         });
         */


        Button verify = new Button("Verify");
        /**
         *   verify.setOnAction(event -> {
         *             String result = verifyExpression(inputField.getText());
         *             resultText.setText(result);
         *         });
         */



        Button FSB = new Button("Find Solution");
        /**
        FSB.setOnAction(event -> {
            String solution = calcuateSolution();
            findSTF.setText(solution);
        });
         **/

        ButtonBarTop.getChildren().add(FSB);
        ButtonBarTop.getChildren().add(findSTF);
        ButtonBarTop.getChildren().add(shuffleButton);
        ButtonBarTop.setAlignment(Pos.CENTER);
        HBox.setMargin(shuffleButton, new Insets(5, 10, 5, 5));
        HBox.setMargin(FSB, new Insets(5, 10, 5, 5));
        HBox.setMargin(findSTF, new Insets(5, 10, 5, 5));

        Label Label1 = new Label("Enter an expression: ");

        ToolBarBottom.getChildren().add(Label1);
        ToolBarBottom.getChildren().add(inputField);
        ToolBarBottom.getChildren().add(verify);
        ToolBarBottom.getChildren().add(resultText);

        ToolBarBottom.setAlignment(Pos.CENTER);
        ToolBarBottom.setPadding(new Insets(10, 10, 10, 15));
        ToolBarBottom.getChildren().addAll(card1, card2, card3, card4);
        vbox.getChildren().add(ButtonBarTop);
        vbox.getChildren().add(CardBox);
        vbox.getChildren().add(BottomBox);

        VBox.setMargin(resultText, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("24 Card Game");
        primaryStage.show();


    }

    int randomCard(){
        int ran = (int) (Math.random()*52);
        picked[pick++] = ran;
        return ran;
    }

    public static void main(String[] args) {Application.launch(args);}}
