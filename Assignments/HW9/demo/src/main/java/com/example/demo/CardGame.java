package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static java.lang.Integer.parseInt;


public class CardGame extends Application {

    List<String> cardImg = new ArrayList<>();

    String solution;

    String result;

    String c1;
    String c2;
    String c3;
    String c4;

    public void start(Stage primaryStage)throws Exception{

        setCards();
        VBox vbox = new VBox();
        HBox cardBox = new HBox();
        HBox toolBarBottom = new HBox(15);
        VBox bottomBox = new VBox(10);
        HBox buttonBarTop = new HBox(10);
        vbox.setFillWidth(true);

        VBox.setMargin(cardBox, new Insets(10,20,20,10));

        Text resultText = new Text("");
        SimpleStringProperty message = new SimpleStringProperty();
        message.bind(resultText.textProperty());

        c1 = cardImg.get(0);
        c2 = cardImg.get(1);
        c3 = cardImg.get(2);
        c4 = cardImg.get(3);

        Image i1 = new Image(c1);
        Image i2 = new Image(c2);
        Image i3 = new Image(c3);
        Image i4 = new Image(c4);

        ImageView card1 = new ImageView(i1);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card2 = new ImageView(i2);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card3 = new ImageView(i3);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card4 = new ImageView(i4);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        TextField inputField = new TextField();
        inputField.setMinWidth(200);

        TextField findSolutionTxtFld = new TextField();
        findSolutionTxtFld.setPrefWidth(150);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e ->{
            resultText.setText("");
            inputField.setText("");
            shuffleCards(cardBox);
            findSolutionTxtFld.setText("");
        });

        Button verifyButton = new Button("Verify");
        verifyButton.setOnAction(e ->{
            result = (inputField.getText());
            inputField.setText(verifyExpression(result));
            final Stage popUp = new Stage();
            popUp.initModality(Modality.NONE);
            popUp.initOwner(primaryStage);
            VBox popUpBox = new VBox(20);
            popUpBox.getChildren().add(new Text(verifyExpression(result)));
            Scene popUpScene = new Scene(popUpBox,300,200);
            popUp.setScene(popUpScene);
            popUp.show();
        });

        Button findSolutionBtn = new Button("Find Solution");
        findSolutionBtn.setOnAction(e ->{
            solution = Solution(getNumberValue(c1),getNumberValue(c2),getNumberValue(c3),getNumberValue(c4));
            findSolutionTxtFld.setText(solution);
        });

        buttonBarTop.getChildren().add(findSolutionBtn);
        buttonBarTop.getChildren().add(findSolutionTxtFld);
        buttonBarTop.getChildren().add(refreshButton);
        buttonBarTop.setAlignment(Pos.CENTER);

        HBox.setMargin(refreshButton,new Insets(5,10,5,5));
        HBox.setMargin(findSolutionBtn,new Insets(5,10,5,5));
        HBox.setMargin(findSolutionTxtFld,new Insets(5,10,5,5));

        Label inputLabel = new Label("Enter an expression: ");

        toolBarBottom.getChildren().add(inputLabel);
        toolBarBottom.getChildren().add(inputField);
        toolBarBottom.getChildren().add(verifyButton);
        bottomBox.getChildren().add(resultText);

        toolBarBottom.setAlignment(Pos.CENTER);
        toolBarBottom.setPadding(new Insets(10,10,10,15));
        cardBox.getChildren().addAll(card1, card2, card3, card4);
        vbox.getChildren().add(buttonBarTop);
        vbox.getChildren().add(cardBox);
        vbox.getChildren().add(toolBarBottom);
        vbox.getChildren().add(bottomBox);
        VBox.setMargin(resultText, new Insets(10,10,10,10));

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("24-point-card-game");
        primaryStage.show();

    }
    public ArrayList<String> setCards() {

        int cardNum;

        for (cardNum=1; cardNum <= 52; cardNum++) {
            String path = "C:\\Users\\wpond\\NKU\\CSC360\\Assignments\\HW9\\demo\\src\\main\\java\\card\\" + cardNum + ".png";
            cardImg.add(((path)));
        }
        Collections.shuffle(cardImg);

        return (ArrayList<String>) cardImg;
    }

    int getNumberValue(String value) {

        String path1 = value.substring(value.indexOf("\\")+65,value.indexOf("."));
        int number = parseInt(path1);


        int cardValue;

        // Spades
        if (number <= 13) {
            cardValue = number;

            // Hearts
        } else {
            if (number <= 26) {
                cardValue = reduceToValue(number);

                // Diamonds
            } else if (number <= 39) {
                cardValue = reduceToValue(number);

                // Clubs
            } else {
                cardValue = reduceToValue(number);
            }
        }
        return cardValue;
    }

    int reduceToValue(int value){
        //get value no matter which suite //
        while(value > 13){
            value -= 13;
        }
        return value;
    }

    public String Solution(int a, int b, int c,int d) throws ArithmeticException  {
        String Solution = "";
        String noSolution = "No Solution";
        String[] operators = {"+", "-","*", "/"};

        int[][] allCombinations = {{a, b, c, d}, {d, a, b, c},
                {c, d, a, b}, {b, c, d, a}, {a, b, d, c}, {c, a, b, d},
                {d, c, a, b}, {b, d, c, a}, {a, d, c, b}, {b, a, d, c},
                {c, b, a, d}, {d, c, b, a}, {a, c, b, d}, {d, a, c, b},
                {b, d, a, c}, {c, b, d, a}, {b, a, c, d}, {d, b, a, c},
                {c, d, b, a}, {a, c, d, b}, {a, d, b, c}, {c, a, d, b},
                {b, c, a, d}, {d, b, c, a}};
        try {
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
        }catch(ArithmeticException e){
            return noSolution;
        }
        return noSolution;
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

    void shuffleCards(HBox hBox){

        ArrayList<String> stack = new ArrayList(cardImg);
        Collections.shuffle(stack);

        c1 = stack.get(0);
        c2 = stack.get(1);
        c3 = stack.get(2);
        c4 = stack.get(3);

        solution = Solution(getNumberValue(c1),getNumberValue(c2),getNumberValue(c3),getNumberValue(c4));


        Image i1 = new Image(c1);
        Image i2 = new Image(c2);
        Image i3 = new Image(c3);
        Image i4 = new Image(c4);

        ImageView card1 = new ImageView(i1);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card2 = new ImageView(i2);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card3 = new ImageView(i3);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        ImageView card4 = new ImageView(i4);
        card1.setPreserveRatio(true);
        card1.setFitHeight(300);

        hBox.getChildren().setAll(card1,card2,card3,card4);

    }

    public String verifyExpression(String text){
        String correct = "Correct";
        String incorrect = "Incorrect";


        if(result.equals(solution))
        {
            return correct;
        }
        else
        {
            return incorrect;
        }

    }
    public static void main(String[] args){
        Application.launch(args);
    }
}