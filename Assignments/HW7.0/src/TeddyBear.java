/**
 * Will Pond
 * In this program it asks the user to input the number of bears you have. Dependent on what your input you have is decided by
 * the if statements in the method check Bears. The goal of this is to get exactly 42 bears by doing that you must meet the if statements
 * If it doesn't meet the condition than there no possible way to get 42 so the final output will be either a true statement or false statement.
 */

import java.util.Scanner;
public class TeddyBear {
    public static void main(String[] args) {

        System.out.println("Enter number of Bears");

        Scanner input = new Scanner(System.in);

        System.out.println(checkBears(input.nextInt()));

    }

    public static boolean checkBears(int n) {

        if (n < 42)  // less than 42
        {
            return false;
        }
        if (n == 42)  // equal to 42
        {
            return true;
        }
        else
        {
            if (n % 5 == 0) // first condition for getting 42 by divisible by 5
            {
                n = n - 42;
                checkBears(n);
                return true;
            }
            if (n % 2 == 0) // second condition for getting 42 by n is even
            {
                n = n / 2;
                checkBears(n);
                return true;
            }
            if ((n % 3 == 0) || (n % 4 == 0)) //third condition for getting 42 by divisible by 3 or 4
            {
                n = (n % 10) * ((n % 100) / 10);
                checkBears(n);
                return true;
            }
        }
        return false; // all condition fail return false
    }
}