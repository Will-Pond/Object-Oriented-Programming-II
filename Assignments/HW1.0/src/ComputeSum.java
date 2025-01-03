/**
Will Pond

This is my ComputeSum program where I prompt the user two integers using a method
called readIntGreaterOrEqualTo with parameters. Then I prompt the user float number
using the readDouble method. All using the Scanner class.
*/

import java.util.Scanner;                     //My imports
import java.util.InputMismatchException;

public class ComputeSum {
    public static void main(String[] arg) {
         Scanner input = new Scanner(System.in);
         int num=0;
         int lowBound = readIntGreaterOrEqualTo(input, num);                // storing the inputs of the methods into variables
         int highBound = readIntGreaterOrEqualTo(input, lowBound);
         double pointNum =readDouble(input);

        double total = 0;
        for(int i=lowBound;i<=highBound;i++){               // My calculation formula for kind combined input
            total += Math.pow(i, pointNum);
        }
            System.out.println("Sum = " +total);
        }

    public static int readIntGreaterOrEqualTo(Scanner input, int lowerBound) {
        boolean continueInput = true;
        int number = 0;

        do{
        try {
            System.out.println("Enter an integer greater than or equal to " + lowerBound + ":");
            int value = input.nextInt();
            if(value <= lowerBound){
            while(value <= lowerBound) {          // Keep going into the loop until the answer is greater than the variable
                System.out.println("Try again. The number must be greater than or equal to: " + lowerBound);
                value = input.nextInt();
                }
            }
            number = value;
            continueInput = false;
        }catch(InputMismatchException ex) {
            System.out.println("Try again an integer is required.");       // catches input that are not valid answers
            input.next();
            input.nextLine();
        }
        }while(continueInput);
        return number;
    }
    public static double readDouble(Scanner input) {

        boolean continueInput = true;
        double number = 0.0;

        do {
            try {
                System.out.println("Enter a floating point number: ");
                number = input.nextDouble();
                continueInput = false;
            } catch (InputMismatchException ex) {     // catches input that are not valid answers
                System.out.println("Try again A floating point number is required.");
                input.nextLine();

            }
        } while (continueInput);

        input.close();
        return number;
    }

}
