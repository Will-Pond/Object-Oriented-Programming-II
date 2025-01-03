public class SuperWildCardDemo {
  public static void main(String[] args) {
    GenericStack<String> stack1 = new GenericStack<>();
    GenericStack<Object> stack2 = new GenericStack<>();
    stack2.push("Java");
    stack2.push(2);
    stack1.push("Sun");
    addAll(stack1, stack2);
    //SuperWildCardDemo.<String>addAll(stack1, stack2);  // Equivalent to the preceding line:
                                                         // Just making the generic type parameter explicit.
    AnyWildCardDemo.print(stack2);
  }

  public static <T> void addAll(GenericStack<T> stack1,
      GenericStack<? super T> stack2) {
    while (!stack1.isEmpty())
      stack2.push(stack1.pop());
  }
  
  public static <T> void addAll2(GenericStack<? extends T> stack1,
  	      GenericStack<T> stack2) {
  	    while (!stack1.isEmpty())
  	      stack2.push(stack1.pop());
  } 
}
