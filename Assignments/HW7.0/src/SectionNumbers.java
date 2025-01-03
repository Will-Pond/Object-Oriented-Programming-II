/**
 * Will Pond
 * In this program it asks the user to input a string name then an integer number for number of levels. Then it print out the String
 * with number of levels  1 through 3 by each level with the string.
 */

import java.util.Scanner;
public class SectionNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String a;

        System.out.println("Enter a String");
        a = input.next();

        System.out.println("Enter number of levels");
        int b = Integer.parseInt(input.next());

        sections(a, b);
    }

    static void sections(String prefix, int levels){
        if(levels == 0)
        {
            System.out.println(prefix);
        }
        else {
            String NewPrefix = null;

            // loops over the new variable and the recursion of the method
            for (int i = 1; i <= 3; i++)
            {
                NewPrefix = prefix + i + ".";
                sections(NewPrefix, levels - 1);
            }
          }
        }
    }

