/**
 Will Pond

 This is my AverageNumbers program where I ask the user for a text file
 and I convert the text file into parsable integers and unparsable integers by each line.
 Then I add up the total number of parsable integers and unparsable integers. Then add up the sum.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AverageNumbers {
      public static void main(String[] args) {

          System.out.println("Enter name of input file");
          String filename = "";
          Scanner input = new Scanner(System.in);
          filename = input.nextLine();
          File file = new File(filename);

        try {
            Scanner input1 = new Scanner(file);
            double sum = 0;
            int totalNum = 0;
            String line ="";
            int numCatch = 0;
            while(input1.hasNextLine()){
                try{
                     line = input1.nextLine();
                     int data = Integer.parseInt(line);
                     sum += data;
                     totalNum++;
             } catch (NumberFormatException ex) {            // catches invalid integers
                    System.out.println("Cannot parse as " + line + " an integer.");
                    numCatch++;
        }
    }
            System.out.println("Number of parsable lines: " + totalNum);
            double avg = sum/totalNum;
            System.out.println("Average value:" + avg);
            System.out.println("Number of unparsable lines " + numCatch);

                    }catch(FileNotFoundException ex){
                        System.out.println("Could not find file: " + filename);
                        System.exit(1);
        }
      }
   }


