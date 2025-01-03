/**
 * Will Pond
 * This is the TestOctagon class where using the Octagon class
 * to create new octagon using the methods called in Octagon class
 * to get output
 */

public class TestOctagon {
    public static void main(String[] args) throws CloneNotSupportedException {
        Octagon oct1 = new Octagon(5);
        System.out.println("oct1: " + oct1);
        Octagon oct2 = (Octagon) oct1.clone();
        System.out.println("oct2:" + oct2);
        System.out.println("oct1.compareTo(oct2): " + oct1.compareTo(oct2));
        Octagon oct3 = new Octagon(5.2);
        System.out.println("oct1: " + oct3);
        System.out.println("oct2: " + oct2);
        System.out.println("oct1.compareTo(oct2): " + oct3.compareTo(oct2));
    }
}
