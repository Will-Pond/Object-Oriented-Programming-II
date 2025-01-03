package com.example.demo1;

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

import java.io.File;
import java.io.IOException;

import java.util.Stack;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CardGame extends Application {

    String[] cards;
    int[] picked = new int[4];
    int pick = 0;

    ArrayList<Image> cardImg = new ArrayList<Image>();


    // Outputs Strings for when the verify button is activated
    static final String CORRECT_RESULT = "Correct";
    static final String INCORRECT_RESULT = "Incorrect Result";
    static final String WRONG_NUMBERS = "The Numbers in the expression, don't match the number in the set.";

    private void setCards() {
        cards = new String[52];
        int cardNum = 1;
        for (int i = 0; i < cards.length; i++) {
            String path = "C:\\Users\\wpond\\NKU\\CSC360\\Assignments\\HW9.1\\demo1\\src\\main\\java\\card\\"+ cardNum + ".png";;
            cardNum++;
            cards[i] = path;

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Dividing the space using the VBoxes and HBoxes

        setCards();
        VBox vbox = new VBox();
        HBox cardBox = new HBox();
        HBox toolBarBottom = new HBox(15);
        VBox bottomBox = new VBox(10);
        HBox buttonBarTop = new HBox(10);
        vbox.setFillWidth(true);
        VBox.setMargin(cardBox, new Insets(10, 20, 20, 10));

        //IDK

        Text resultText = new Text("");
        SimpleStringProperty message = new SimpleStringProperty();
        message.bind(resultText.textProperty());


        //Accessing the images from the array of cards containing the files

        Image i1 = new Image(cards[randomCard()]);
        Image i2 = new Image(cards[randomCard()]);
        Image i3 = new Image(cards[randomCard()]);
        Image i4 = new Image(cards[randomCard()]);

        //Displaying the four cards

        ImageView card1 = new ImageView(i1);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);
        ImageView card2 = new ImageView(i2);
        card2.setPreserveRatio(true);
        card2.setFitHeight(300);
        ImageView card3 = new ImageView(i3);
        card3.setPreserveRatio(true);
        card3.setFitHeight(300);
        ImageView card4 = new ImageView(i4);
        card4.setPreserveRatio(true);
        card4.setFitHeight(300);

        // input TextFields

        TextField inputField = new TextField();
        inputField.setMinWidth(200);
        TextField findSolutionTxtFld = new TextField();
        findSolutionTxtFld.setPrefWidth(150);

        //Shuffle Button

        Button shuffleButton = new Button("Refresh");
        shuffleButton.setOnAction(e -> {
            resultText.setText("");
            inputField.setText("");
            shuffleCards(cardBox);
            findSolutionTxtFld.setText("");

        });

        //Verify Button

        Button verifyButton = new Button("Verify");
        verifyButton.setOnAction(event -> {
            String result = verifyExpression(inputField.getText());
            resultText.setText(result);
        });

        //Find Solution Button

        Button findSolutionBtn = new Button("Find Solution");
        findSolutionBtn.setOnAction(event -> {
            // Find Solution
            String solution = new FindSolution().toString();
            findSolutionTxtFld.setText(solution);
        });

        //buttonBarTop Hbox containing attributes

        buttonBarTop.getChildren().add(findSolutionBtn);
        buttonBarTop.getChildren().add(findSolutionTxtFld);
        buttonBarTop.getChildren().add(shuffleButton);
        buttonBarTop.setAlignment(Pos.CENTER);

        //positioning the Hbox buttonBarTop

        HBox.setMargin(shuffleButton, new Insets(5, 10, 5, 5));
        HBox.setMargin(findSolutionBtn, new Insets(5, 10, 5, 5));
        HBox.setMargin(findSolutionTxtFld, new Insets(5, 10, 5, 5));

        //The only label for enter an expression

        Label inputLabel = new Label("Enter an expression: ");

        //tootBarBottom Hbox containing attributes and positioning

        toolBarBottom.getChildren().add(inputLabel);
        toolBarBottom.getChildren().add(inputField);
        toolBarBottom.getChildren().add(verifyButton);
        bottomBox.getChildren().add(resultText);
        toolBarBottom.setAlignment(Pos.CENTER);
        toolBarBottom.setPadding(new Insets(10, 10, 10, 15));

        //Hbox cardBox containing all the cards to display

        cardBox.getChildren().addAll(card1, card2, card3, card4);


        //Vbox add all the Hboxes to the Vbox and position it

        vbox.getChildren().add(buttonBarTop);
        vbox.getChildren().add(cardBox);
        vbox.getChildren().add(toolBarBottom);
        vbox.getChildren().add(bottomBox);
        VBox.setMargin(resultText, new Insets(10, 10, 10, 10));

        //setting the Scene

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("24-Point Card Game");
        primaryStage.show();

    }

    static boolean equals24(double i) {
        return i >= 23.999 && i <= 24.001;
    }

    double operate(double a, double b, int op) {
        if (op == 0) return a + b;
        if (op == 1) return a - b;
        if (op == 2) return a * b;
        if (op == 3) {
            return a / b;
        }
        throw new IllegalArgumentException("operate( " + a + ", " + b + ", " + op + " )");

    }

    int a ;
    int b;
    int c;
    int d;


    String findSolution(int[] card) {
        String Solution = "";
        String noSolution = "No Solution";
        String[] operators = {"+", "-", "/"};

        int[][] allCombinations = {{a, b, c, d}, {d, a, b, c},
                {c, d, a, b}, {b, c, d, a}, {a, b, d, c}, {c, a, b, d},
                {d, c, a, b}, {b, d, c, a}, {a, d, c, b}, {b, a, d, c},
                {c, b, a, d}, {d, c, b, a}, {a, c, b, d}, {d, a, c, b},
                {b, d, a, c}, {c, b, d, a}, {b, a, c, d}, {d, b, a, c},
                {c, d, b, a}, {a, c, d, b}, {a, d, b, c}, {c, a, d, b},
                {b, c, a, d}, {d, b, c, a}};

        for (String firstOp : operators) {
            for (String secondOp : operators) {
                for (String thirdOp : operators) {
                    for (int[] cardNums : allCombinations) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (i == 0) {
                                    if (j == 0) {
                                        Solution = cardNums[0] + firstOp + cardNums[1] + secondOp + cardNums[2] + thirdOp + cardNums[3];
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 1) {
                                        Solution = "(" + cardNums[0] + firstOp
                                                + cardNums[1] + ")" + secondOp
                                                + cardNums[2] + thirdOp
                                                + cardNums[3];
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 2) {
                                        // Create an expression in the form a firstOp (b secondOp c) thirdOp d
                                        Solution = cardNums[0] + firstOp + "("
                                                + cardNums[1] + secondOp
                                                + cardNums[2] + ")" + thirdOp
                                                + cardNums[3];
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 3) {
                                        // Create an expression in the form a firstOp b secondOp (c thirdOp d)
                                        Solution = cardNums[0] + firstOp
                                                + cardNums[1] + secondOp + "("
                                                + cardNums[2] + thirdOp
                                                + cardNums[3] + ")";
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 4) {
                                        // Create an expression in the form (a firstOp b) secondOp (c thirdOp d)
                                        Solution = "(" + cardNums[0] + firstOp
                                                + cardNums[1] + ")" + secondOp
                                                + "(" + cardNums[2] + thirdOp
                                                + cardNums[3] + ")";
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    }
                                } else if (i == 1) {
                                    if (j == 0) {
                                        Solution = "(" + cardNums[0] + firstOp
                                                + cardNums[1] + secondOp
                                                + cardNums[2] + ")" + thirdOp
                                                + cardNums[3];
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 1) {
                                        Solution = "((" + cardNums[0] + firstOp
                                                + cardNums[1] + ")" + secondOp
                                                + cardNums[2] + ")" + thirdOp
                                                + cardNums[3];
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 2) {
                                        Solution = "(" + cardNums[0] + firstOp
                                                + "(" + cardNums[1] + secondOp
                                                + cardNums[2] + "))" + thirdOp
                                                + cardNums[3];
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    }
                                } else if (i == 2) {
                                    if (j == 0) {
                                        Solution = cardNums[0] + firstOp + "("
                                                + cardNums[1] + secondOp
                                                + cardNums[2] + thirdOp
                                                + cardNums[3] + ")";
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 1) {
                                        Solution = cardNums[0] + firstOp + "(("
                                                + cardNums[1] + secondOp
                                                + cardNums[2] + ")" + thirdOp
                                                + cardNums[3] + ")";
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    } else if (j == 2) {
                                        Solution = cardNums[0] + firstOp + "("
                                                + cardNums[1] + secondOp + "("
                                                + cardNums[2] + thirdOp
                                                + cardNums[3] + "))";
                                        if (evaluateExpression(Solution) == 24) {
                                            return Solution;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return noSolution;
    }



    private String verifyExpression(String text) {
        List<Integer> currentCards = getPickedCardsValues();

        List<Integer> inputNumbers = parseInputNumbers(text);

        for (int i = 0; i < inputNumbers.size(); i++) {
            boolean removed = currentCards.remove(inputNumbers.get(i));
            if (!removed) {
                return WRONG_NUMBERS;
            }
        }

        if (!(currentCards.size() == 0)) {
            return WRONG_NUMBERS;
        }

        if (evaluateExpression(text) == 24) {
            return CORRECT_RESULT;
        }

        return INCORRECT_RESULT;
    }

    private List<Integer> getPickedCardsValues() {
        String[] currentCards = new String[4];

        for (int i = 0; i < picked.length; i++) {
            currentCards[i] = cards[picked[i]];
        }

        String[] cardNums = new String[4];
        int i = 0;
        for (String card : currentCards) {
            String[] parts = card.split("/");
            String name = parts[parts.length - 1];
            String cardNum = name.split("\\.")[0];
            cardNums[i] = cardNum;
            i++;
        }
        List<Integer> cardValues = new ArrayList<>();

        for (i = 0; i < cardNums.length; i++) {
            cardValues.add(getNumberValue(cardNums[i]));
        }
        return cardValues;
    }

    private List<Integer> parseInputNumbers(String text) {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isDigit(ch)) {
                if (i + 1 < text.length()) {
                    i++;
                    char ch2 = text.charAt(i);
                    if (Character.isDigit(ch2)) {
                        values.add(Integer.parseInt(ch + "" + ch2));
                        continue;
                    }
                }
                values.add(Integer.parseInt(ch + ""));
            }
        }
        return values;
    }

    private void shuffleCards(HBox hBox) {
        pick = 0;
        Image i1 = new Image(cards[randomCard()]);
        Image i2 = new Image(cards[randomCard()]);
        Image i3 = new Image(cards[randomCard()]);
        Image i4 = new Image(cards[randomCard()]);

        ImageView card1 = new ImageView(i1);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card2 = new ImageView(i2);
        card2.setPreserveRatio(true);
        card2.setFitHeight(300);

        ImageView card3 = new ImageView(i3);
        card3.setPreserveRatio(true);
        card3.setFitHeight(300);

        ImageView card4 = new ImageView(i4);
        card4.setPreserveRatio(true);
        card4.setFitHeight(300);
        hBox.getChildren().setAll(card1, card2, card3, card4);
    }

    int randomCard() {
        int ran = (int) (Math.random() * 52);
        picked[pick++] = ran;
        return ran;
    }

    private int getNumberValue(String card) {
        StringBuilder value = null;
        value.append("\"C:\\\\Users\\\\wpond\\\\NKU\\\\CSC360\\\\Assignments\\\\HW9.1\\\\demo1\\\\src\\\\main\\\\java\\\\card\\\\\"");
        int cardValue = 0;
        Path p = Paths.get(card);
        String fileName = p.getFileName().toString();
        final int cardNum = Integer.parseInt(fileName);

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

    private int reduceToValue(int value) {
        /* get value regardless of suite */
        while (value > 13) {
            value -= 13;
        }
        return value;
    }

    public static int evaluateExpression(String expression) {
        // Create operandStack to store operands
        Stack<Integer> operandStack = new Stack<>();

        // Create operatorStack to store operators
        Stack<Character> operatorStack = new Stack<>();

        // Insert blanks around (, ), +, -, /, and *
        expression = insertBlanks(expression);

        // Extract operands and operators
        String[] tokens = expression.split(" ");

        // Phase 1: Scan tokens
        for (String token: tokens) {
            if (token.length() == 0) // Blank space
                continue; // Back to the while loop to extract the next token
            else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                // Process all +, -, *, / in the top of the operator stack
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                                operatorStack.peek() == '-' ||
                                operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the + or - operator into the operator stack
                operatorStack.push(token.charAt(0));
            }
            else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                // Process all *, / in the top of the operator stack
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '*' ||
                                operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the * or / operator into the operator stack
                operatorStack.push(token.charAt(0));
            }
            else if (token.trim().charAt(0) == '(') {
                operatorStack.push('('); // Push '(' to stack
            }
            else if (token.trim().charAt(0) == ')') {
                // Process all the operators in the stack until seeing '('
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }

                operatorStack.pop(); // Pop the '(' symbol from the stack
            }
            else { // An operand scanned
                // Push an operand to the stack
                operandStack.push(new Integer(token));
            }
        }

        // Phase 2: process all the remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        // Return the result
        return operandStack.pop();
    }

    /** Process one operator: Take an operator from operatorStack and
     *  apply it on the operands in the operandStack */

    public static void processAnOperator(
            Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        if (op == '+')
            operandStack.push(op2 + op1);
        else if (op == '-')
            operandStack.push(op2 - op1);
        else if (op == '*')
            operandStack.push(op2 * op1);
        else if (op == '/')
            operandStack.push(op2 / op1);
    }

    public static String insertBlanks(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    s.charAt(i) == '*' || s.charAt(i) == '/')
                result += " " + s.charAt(i) + " ";
            else
                result += s.charAt(i);
        }

        return result;
    }




    public static void main(String[] args) {
        Application.launch(args);}}
