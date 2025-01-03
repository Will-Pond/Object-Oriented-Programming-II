/**
 * Will Pond
 * This is the TestFastRationalSeries were uses the FastRational
 * class and the methods to add up 1/2 + 2/3... 99/100 to print out
 * the sum and the sum of the decimal.
 */


public class TestFastRationalSeries {
    public static void main(String[] args) {
        FastRational total = new FastRational();
        for(int i = 1; i < 100; i++){
            total = total.add(new FastRational(i, i +1));
        }
        System.out.println("Series sum = " + total);
        System.out.println("Series sum = " + total.doubleValue());
    }
}
