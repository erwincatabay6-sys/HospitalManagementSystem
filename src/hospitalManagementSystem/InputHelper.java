package hospitalManagementSystem;
import java.util.*;
public class InputHelper {
	 public static int readInt(Scanner sc) {
	        while (!sc.hasNextInt()) {
	            System.out.println("Invalid input. Please enter a number.");
	            sc.next();
	        }
	        return sc.nextInt();
}
	 public static double readDouble(Scanner sc) {
		    while (!sc.hasNextDouble()) {
		        System.out.println("Invalid input. Please enter a number.");
		        sc.next();
		    }
		    return sc.nextDouble();
		}
	 public static int readAge(Scanner sc) {
		    int age = readInt(sc);

		    while (age < 1 || age > 120) {
		        System.out.println("Invalid age! Age must be between 1 and 120.");
		        System.out.println("Age: ");
		        age = readInt(sc);
		    }
		    return age;
		}
	 public static double readPercentage(Scanner sc) {
		    double percentage = readDouble(sc);

		    while (percentage < 0 || percentage > 100) {
		        System.out.println("Invalid percentage! Please enter a value between 0 and 100.");
		        System.out.println("Percentage: ");
		        percentage = readDouble(sc);
		    }
		    return percentage;
		}
	 public static boolean readYesNo(Scanner sc) {
		    String input = sc.nextLine();

		    while (!input.equalsIgnoreCase("Y") &&
		           !input.equalsIgnoreCase("N")) {

		        System.out.println("Invalid input. Please enter Y or N.");
		        input = sc.nextLine();
		    }

		    return input.equalsIgnoreCase("Y");
		}
	 public static double readNonNegativeDouble(Scanner sc) {
		    double value = readDouble(sc);

		    while (value < 0) {
		        System.out.println("Value cannot be negative.");
		        System.out.println("Enter a non-negative value: ");
		        value = readDouble(sc);
		    }
		    return value;
		}
	 public static String readGender(Scanner sc) {
		    String gender = sc.nextLine();

		    while (!gender.equalsIgnoreCase("Male")
		            && !gender.equalsIgnoreCase("Female")
		            && !gender.equalsIgnoreCase("Other")) {

		        System.out.println("Invalid gender! Please enter Male, Female, or Other.");
		        System.out.println("Gender: ");
		        gender = sc.nextLine();
		    }
		    return gender;
		}
}