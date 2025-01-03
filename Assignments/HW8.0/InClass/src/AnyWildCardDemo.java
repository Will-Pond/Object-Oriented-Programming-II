public class AnyWildCardDemo {
  public static void main(String[] args ) {
     GenericStack<Integer> intStack = new GenericStack<>();
     intStack.push(1); // 1 is autoboxed into new Integer(1)
     intStack.push(2);
     intStack.push(-2);

     print(intStack);
     //print2(intStack); // Will this compile?
  }

  /** Print objects and empties the stack */
  public static void print(GenericStack<?> stack) {  // '?' by itself is equivalent to '? extends Object'
	//stack.push(new Double(3.2));
    while (!stack.isEmpty()) {
      System.out.print(stack.pop().toString() + " ");
    }
  }
  
  /** Print objects and empties the stack */
  public static void print2(GenericStack<Object> stack) {
	//stack.push(new Double(3.2));
    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
  }
}
