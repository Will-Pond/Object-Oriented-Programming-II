import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String FILE_PATH_PREFIX = "C:\\Users\\wpond\\NKU\\CSC360\\Assignments\\HW13\\BabyName\\src\\babynamesranking\\babynamesranking";
    private static final String FILE_EXTENSION = ".txt";

    public static void main(String[] args) {

            int year = promptYearValue("Enter the Year: ", 2001, 2010);
            char gender = promptGenderValue("Enter the gender: ");
            String name = promptStringValue("Enter the name: ");
            ArrayList<String[]> data = getDataByYear(year);
            int rank = getRank(name, gender, data);
            displayNameInfo(name, rank, year);
        }

        private static void displayNameInfo(String name, int rank, int year) {
            if (rank > 0) {
                System.out.println(name + " is ranked #" + rank + " in year " + year);
            } else {
                System.out.println("The name " + name + " is not ranked in year " + year);
            }
        }

        private static int getRank(String name, char gender, ArrayList<String[]> data) {
            int genderIndex = gender == 'M' ? 1 : 3;
            for (String[] line : data) {
                if (line[genderIndex].equals(name)) {
                    try {
                        return Integer.parseInt(line[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("There was a problem reading the data.\nThe string cannot be parsed as an integer.");
                    }
                }
            }
            return 0;
        }

        private static ArrayList<String[]> getDataByYear(int year) {
            String filePath = FILE_PATH_PREFIX + year + FILE_EXTENSION;
            File file = validateFile(filePath);
            return parseDataFromFile(file);
        }

        private static ArrayList<String[]> parseDataFromFile(File file) {
            ArrayList<String[]> data = new ArrayList<>();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    data.add(scanner.nextLine().split("\\s+"));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
            return data;
        }

        private static File validateFile(String fileName) {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("The file denoted by this pathname does not exist.");
                System.exit(0);
            }
            if (!file.isFile()) {
                System.out.println("The file denoted by this pathname is not a normal file.");
                System.exit(0);
            }
            if (!file.canRead()) {
                System.out.println("The application cannot read the file denoted by this pathname.");
                System.exit(0);
            }
            return file;
        }

        private static String promptStringValue(String prompt) {
            Scanner scanner = new Scanner(System.in);
            System.out.print(prompt);
            return scanner.nextLine();
        }

        private static char promptGenderValue(String s) {
            Scanner scanner = new Scanner(System.in);
            char gender;
            boolean valid;
            do {
                valid = true;
                System.out.print(s);
                gender = scanner.nextLine().toUpperCase().charAt(0);
                if (gender != 'M' && gender != 'F') {
                    System.out.println("Cannot recognize gender.\nPlease enter M for male or F for female.");
                    valid = false;
                }
            } while (!valid);
            return gender;
        }

        public static int promptYearValue(String s, int min, int max) {
            Scanner scanner = new Scanner(System.in);
            int year = 0;
            boolean valid;
            do {
                valid = true;
                System.out.print(s);
                try {
                    year = scanner.nextInt();
                    if (year < min || year > max) {
                        System.out.println("Value out of range.\nPlease enter a year between 2001 and 2010 inclusive.");
                        valid = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Value not recognized as a year.");
                    scanner.nextLine();
                    valid = false;
                }
            } while (!valid);
            return year;
        }
    }
