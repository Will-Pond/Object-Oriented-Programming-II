/**
 * Will Pond
 * In this program it takes in two files which it asks user for it. An input  file and output file. Very
 * similar  to the encode program but when  read first file and writes the second file it minus 5 bytes to it and
 *  then it saves it.
 */

import java.util.Scanner;
import java.io.*;
public class decode {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        // asks user for file to be Decrypted
        System.out.println("Enter a file to Decrypted: ");

        // puts the file into the FileInputSteam
        FileInputStream in = new FileInputStream(input.next());

        //asks user for the file to be saved
        System.out.println("Enter the output file to save: ");

        //puts the file into the FileOutputStream
        FileOutputStream output = new FileOutputStream(input.next());

        // This reads the first input file and then minus 5 bytes to the output file you want to be saved Decrypting it
        int value;
        while ((value = in.read()) != -1) {
            output.write(value - 5);
        }
        input.close();
        output.close();
    }
}

