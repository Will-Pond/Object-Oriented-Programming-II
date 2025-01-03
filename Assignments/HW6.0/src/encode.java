/**
 * Will Pond
 * In this program it takes in two files which it asks user for it. An input  file and output file. Then it read first file
 * and writes the second file by adding bytes to it and saves it
 */
import java.util.Scanner;
import java.io.*;
public class encode {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        // asks user for file to be Encrypted
        System.out.println("Enter a file to Encrypted: ");

        // puts the file into the FileInputSteam
        FileInputStream in = new FileInputStream(input.next());

        //asks user for the file to be saved
        System.out.println("Enter the output file to saved: ");

        //puts the file into the FileOutputStream
        FileOutputStream output = new FileOutputStream(input.next());

        // This reads the first input file and then add 5 bytes to the output file you want to be saved Encrypting it
        int value;
        while ((value = in.read()) != -1) {
            output.write(value + 5);
        }
        input.close();
        output.close();
    }
}