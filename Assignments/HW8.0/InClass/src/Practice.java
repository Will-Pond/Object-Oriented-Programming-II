import java.util.ArrayList;
import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {

        String a = "Today is a very good day";
        String[] ab = a.split(" ");
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(ab));
        for(int i = 1; i<4; i++) {
            System.out.println(ngram(words, ab, i, 0, ab.length));
        }
    }

    public static ArrayList<String> ngram(ArrayList<String> results, String[] a, int f, int s, int e) {
        if (f + s > e) {
            return null;
        }
        for (int i = s; i < e - f + 1; i++) {
            String temp = "";
            for (int j = i; j < i + f; j++) {
                temp += a[j] + " ";
            }
            if (!results.contains(temp)) {
                results.add(temp);
            }
        }
        ngram(results, a, f, s + 1, e);
        return results;

    }
}
