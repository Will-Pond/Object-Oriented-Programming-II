// Creates lists of employees in a company, grouping them by category.
// Then creates a list of all employees from the aforementioned lists.
// Also creates a list of rather arbitrary objects.
// Prints these lists out.
// Demonstrates the use of wildcards in Java.
/**
 * Will Pond
 * In this program it use Generics to help create ManageEmployees with many methods inside
 * The four method I create were printNames, addElements, printAll and printFavoriteLanguages.
 * Each are using ArrayList and for loop in these methods have wildcard parameters calling back to other methods
 * So this program takes in names, job titles, objects and prefer languages which are put in method using arraylist and print
 * out their follow input
 */

import java.util.ArrayList;

public class ManageEmployees {  	
  public static void main(String[] args) {
	ArrayList<WebDeveloper> webDevs = new ArrayList<>();
	webDevs.add(new WebDeveloper("Sir Timothy Berners-Lee"));
	webDevs.add(new WebDeveloper("Brendan Eich"));
	ArrayList<SystemsProgrammer> sysDevs = new ArrayList<>();
	sysDevs.add(new SystemsProgrammer("Grace Hopper"));
	sysDevs.add(new SystemsProgrammer("Donald Knuth"));
	ArrayList<Designer> designers = new ArrayList<>();
	designers.add(new Designer("Roberta Williams"));
	designers.add(new Designer("Sid Meier"));
	ArrayList<Theorist> theorists = new ArrayList<>();
	theorists.add(new Theorist("Shafrira Goldwasser"));
	theorists.add(new Theorist("Stephen Cook"));
	ArrayList<Programmer> devs = new ArrayList<>();
	devs.add(new Programmer("Richard Stallman"));
	devs.add(new Programmer("Bill Joy"));
	ArrayList<Object> objects = new ArrayList<>();
	objects.add(42);
	objects.add("Hello");
	objects.add(new Programmer("Linda Liukas"));
	
	System.out.println("Web devs: "); printNames(webDevs);
	System.out.println("Sys devs: "); printNames(sysDevs);
	System.out.println("Designers: "); printNames(designers);
	System.out.println("Theorists: "); printNames(theorists);
	System.out.println("General devs: "); printNames(devs);
	
	ArrayList<Employee> employees = new ArrayList<>();
	addElements(webDevs, employees);
	addElements(sysDevs, employees);
	addElements(designers, employees);
	addElements(theorists, employees);
	addElements(devs, employees);
	
	System.out.println("All employees: "); printNames(employees);
	
	System.out.println("Web devs (using toString):"); printAll(webDevs);
	System.out.println("Objects (using toString):"); printAll(objects);

	System.out.println("Among web developers: ");
	printFavoriteLanguages(webDevs);
	
	addElements(webDevs, devs);
	addElements(sysDevs, devs);
	
	System.out.println("Among all developers: ");
	printFavoriteLanguages(devs);
  }
  
  // INSERT THE MISSING METHODS HERE ...
	public static void printNames (ArrayList<? extends Employee> arrayList)

	{
		for(int i = 0; i < arrayList.size(); i++)
		{
			System.out.println(arrayList.get(i).name());    //get names method of the arraylist
		}
	}

	public static <T> void addElements( ArrayList<T> test1 ,ArrayList<? super T> test2)
	{
		for(int i = 0; i < test1.size(); i++)
		{
			test2.add(test1.get(i));   //add elements
		}

	}

	public static void printAll(ArrayList<?> arrayList)
	{
		for(int i = 0; i < arrayList.size(); i++)
		{
			System.out.println(arrayList.get(i).toString());  // use toString method
		}
	}

	public static void printFavoriteLanguages(ArrayList<? extends Programmer> arrayList)
	{
		for(int i = 0; i < arrayList.size(); i++)
		{
			System.out.println(arrayList.get(i).name()+ " "+"prefers"+" "+arrayList.get(i).favoriteLanguage());  //names and favoriteLanguage methods
		}
	}
}

class Employee {
  private String name;
  public Employee (String name) {
	this.name = name;
  }
  public String name() {
	  return name;
  }
}

class Programmer extends Employee {
  public Programmer(String name) {
	super(name);
  }
  public String favoriteLanguage() {
	return "Java";
  }
}

class WebDeveloper extends Programmer {
  public WebDeveloper(String name) {
	super(name);
  }
  public String favoriteLanguage() {
	return "JavaScript";
  }
}

class SystemsProgrammer extends Programmer {
  public SystemsProgrammer(String name) {
    super(name);
  }
  public String favoriteLanguage() {
	return "C";
  }  
}

class Theorist extends Employee {
  public Theorist(String name) {
    super(name);
  }
}

class Designer extends Employee {
  public Designer(String name) {
	super(name);
  }
}

