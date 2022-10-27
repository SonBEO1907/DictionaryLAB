import java.util.Scanner;

public class Validator {
    
    public int getInt(){
        int value;
        Scanner sc = new Scanner(System.in);
        do {
            while (!sc.hasNextInt()) {
                System.out.print("That's not a number! Try again: ");
                sc.next();
            }
            /*Add value > 0 here */
            value = sc.nextInt();

        } while (false);
        return value;
    }

    public double getDouble(){
        double value;
        Scanner sc = new Scanner(System.in);
        do {
            while (!sc.hasNextDouble()) {
                System.out.print("That's not a number! Try again: ");
                sc.next();
            }
            value = sc.nextDouble();
        } while (false);
        System.out.println("Successfully get Double: " + value);
        return value;
    }

    public String getString(){
        String value;
        Scanner sc = new Scanner(System.in);
        value = sc.nextLine();
        return value;
    }     
}