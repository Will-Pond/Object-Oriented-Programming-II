/**
 * Will Pond
 * This is the TestBigRationalSeries where is uses
 * the BigRational class and the methods print out the
 * sum and the sum decimal. It is very similar to TestFastRationalSeries
 * but use BigInteger and you get a complete different sum
 */


import java.math.BigInteger;

public class TestBigRationalSeries {
    public static void main(String[] args) {
        BigRational total = new BigRational();
        for(int i = 1; i < 100; i++){
            BigRational sum = new BigRational(BigInteger.valueOf(i), BigInteger.valueOf(i+1));
            total = total.add(sum);   // modify with BigInteger
        }

        System.out.println("Series sum = " + total);
        System.out.println("Series sum = " + total.doubleValue());




    }
}
