import java.util.Random;

public class SuperWildCardDemo0 {
  public static void main(String[] args) {
    GenericStack<Number> stack = new GenericStack<>();
    stack.push(2);
    stack.push(3.1);
    pushFiveIntegers(stack);
    pushFiveDoubles(stack);
    AnyWildCardDemo.print(stack);
  }
  
  public static void pushFiveIntegers(GenericStack<? super Integer> s) {
	//Integer j = s.pop();
	Random rand = new Random();
    for (int i = 0; i < 5; i++) {
      s.push(rand.nextInt());
    }
  }

  public static void pushFiveDoubles(GenericStack<? super Double> s) {
	Random rand = new Random();
    for (int i = 0; i < 5; i++) {
      s.push(rand.nextDouble());
    }
  }
}
