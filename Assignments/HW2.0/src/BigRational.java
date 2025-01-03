/**
 * Will Pond
 * This is the BigRational class that extends Number
 * and implements Comparable interface. It is similar to FastRational
 * class but uses BigInteger creating new modification to all the methods to
 * fit with BigInteger
 */


import java.math.BigInteger;
public class BigRational extends Number implements Comparable<BigRational> {
    private static final long serialVersionUID = 747594659068733876L;
    // Data fields for numerator and denominator
    private BigInteger numerator;
    private BigInteger denominator;

    /** Construct a rational with default properties */
    public BigRational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    /** Construct a rational with specified numerator and denominator */
    public BigRational(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO))
            throw new ArithmeticException("Rational number with denominator zero");
        BigInteger gcd = new BigInteger( String.valueOf(gcd(numerator, denominator)));  //modify with BigInteger
        this.numerator = ((denominator.compareTo(BigInteger.ZERO) > 0) ? BigInteger.ONE : new BigInteger("-1")).multiply(numerator.divide(gcd));
        this.denominator = denominator.abs().divide(gcd);


    }

    /** Find GCD of two numbers */
    private static BigInteger gcd(BigInteger n, BigInteger d) {
       BigInteger gcd = n.gcd(d);       // use gcd of BigInteger


        return gcd;
    }

    /** Use Euclid's algorithm to compute a GCD. */
    private static long fastGcd(long m, long n)   // Do not use
    // Assumes m and n are non-negative
    {
        if (m == 0)
            return n;
        if (n == 0)
            return m;
        m = Math.abs(m);
        n = Math.abs(n);
        while (true)
        {
            long r = m % n;
            if (r == 0)
                return n;
            m = n;
            n = r;
        }
    }

    /** Return numerator */
    public BigInteger getNumerator() {return numerator;
    }

    /** Return denominator */
    public BigInteger getDenominator() {
        return denominator;
    }

    /** Add a rational number to this rational */
    public BigRational add(BigRational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new BigRational(n, d); // modify with BigInteger
    }

    /** Subtract a rational number from this rational */
    public BigRational subtract(BigRational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator).multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new BigRational(n, d);    // modify with BigInteger
    }

    /** Multiply a rational number to this rational */
    public BigRational multiply(BigRational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new BigRational(n, d);  // modify with BigInteger
    }

    /** Divide a rational number from this rational */
    public BigRational divide(BigRational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator());
        BigInteger d = denominator.multiply(secondRational.numerator);
        return new BigRational(n, d);  // modify with BigInteger
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }

    @Override // Override the equals method in the Object class
    public boolean equals(Object other) {
        if ((this.subtract((BigRational)(other))).getNumerator().equals(BigInteger.ZERO))
            return true;
        else
            return false;
    }

    @Override // Implement the abstract intValue method in Number
    public int intValue() {
        return (int)doubleValue();
    }

    @Override // Implement the abstract floatValue method in Number
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override // Implement the doubleValue method in Number
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
        return (long)doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(BigRational o) {
        if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) > 0)
            return 1;
        else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0)
            return -1;
        else
            return 0;
    }
    }


