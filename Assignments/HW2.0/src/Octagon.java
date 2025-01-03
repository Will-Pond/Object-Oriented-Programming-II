/**
 * Will Pond
 *
 * This is the Octagon class where it extends the GeometricObject class
 * and implements the Comparable and Cloneable interfaces. It contains
 * The getSide, getArea, getPerimeter, toString, CompareTo, and clone methods
 * of the Octagon
 */


import java.lang.Comparable;
import java.lang.Cloneable;
public  class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    private double side = 1.0;

    private  boolean isClone = false;

    public Octagon(){

    }

    public Octagon(double side){
        super();
        this.side = side;
    }

    public double getSide(double side){
        return side;
    }

    @Override
    public double getArea() {
        double area = (2 * (1 + Math.sqrt(2)) * side * side);
        return area;
    }

    @Override
    public double getPerimeter() {
        return (side * 8);
    }

    public String toString(){
        return "Octagon with sides = " +getSide(side) + "Perimeter = " + getPerimeter() + "Area = " + getArea() + "isClone = " + isClone;
    }

    public int compareTo(Octagon octagon1){
        if (getArea() > ((Octagon)octagon1).getArea())
            return 1;
        else if (getArea() < ((Octagon)octagon1).getArea())
            return -1;
        else
            return 0;
    }

    public Object clone() throws CloneNotSupportedException {
            isClone = true;
            return super.clone();

    }

}
