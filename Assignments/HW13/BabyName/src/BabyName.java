/**
 * Will Pond
 * In this program is it read the files from the babynamesranking folder and ask the user for the year. Than it ask the user input
 * two names and find the highest rank possible for that name and print it off along with their name.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BabyName {
    private static final String FILE_PATH_PREFIX = "C:\\Users\\wpond\\NKU\\CSC360\\Assignments\\HW13\\BabyName\\src\\babynamesranking\\babynamesranking";
    private static final String FILE_EXTENSION = ".txt";

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        Map<String, Integer> map1 = new HashMap<String, Integer>();
        Map<String, Integer> map2 = new HashMap<String, Integer>();

        System.out.println("Enter the year");
        String year = input.next();

        File file = new File(FILE_PATH_PREFIX + year + FILE_EXTENSION);

        Scanner input2 = new Scanner(file);

        int result1 = 0;
        int result2 = 0;

        String name1 = null;
        String name2 = null;

        while (input2.hasNext()) {
            int ranking = input2.nextInt();
            name1 = input2.next();
            int n1 = input2.nextInt();
            name2 = input2.next();
            int n2 = input2.nextInt();
            map1.put(name1, ranking);
            map2.put(name2, ranking);
        }

        try {
            System.out.println("Enter first name");
            String first1 = input.next();
            System.out.println("Enter second name");
            String second2 = input.next();

            boolean test1 = map1.containsKey(first1);
            boolean test2 = map2.containsKey(first1);

            boolean test3 = map1.containsKey(second2);
            boolean test4 = map2.containsKey(second2);

            if (test1 == test2) {
                int value1 = map1.get(first1);
                int value2 = map2.get(first1);

                if (value1 < value2) {
                    result1 = value1;
                }
                if (value1 > value2) {
                    result1 = value2;
                }

            }
            if (test3 == test4) {
                int value3 = map1.get(second2);
                int value4 = map2.get(second2);

                if (value3 < value4) {
                    result2 = value3;
                }
                if (value3 > value4) {
                    result2 = value4;
                }

            }
            
            System.out.println(result1 + " " + first1);
            System.out.println(result2 + " " + second2);

        }catch(NullPointerException e) {
            System.out.println("No name in the list");
        }

      }
    }

