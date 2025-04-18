import java.util.Scanner;

// Parent class Vehicle
class Vehicle {
    protected String brand;
    protected int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    // Default implementation of displayInfo
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " km/h");
    }
}

public class Act5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose a car brand by number:");
        System.out.println("1 - Toyota");
        System.out.println("2 - Hyundai");
        System.out.println("3 - Tesla");
        System.out.print("Enter your choice (1-3): ");
        
        int choice;
        // Check if input is an integer
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            scanner.close();
            return;
        }
        
        // Declare and instantiate based on user choice
        switch (choice) {
            case 1:
                Toyota toyota = new Toyota();
                toyota.displayInfo();
                break;
            case 2:
                Hyundai hyundai = new Hyundai();
                hyundai.displayInfo();
                break;
            case 3:
                Tesla tesla = new Tesla();
                tesla.displayInfo();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                scanner.close();
                return;
        }
        
        scanner.close();
    }
}

class Tesla extends Vehicle {
    public Tesla() {
        super("Tesla", 1000); // Speed is 1000
    }

    @Override
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " km/h");
        
        // Additional Tesla-specific choice
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like a Hybrid or Electric Tesla?");
        System.out.print("Enter 1 for Hybrid, 2 for Electric: ");
        
        int type;
        if (scanner.hasNextInt()) {
            type = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Defaulting to Electric Tesla.");
            scanner.close();
            return;
        }
        
        if (type == 1) {
            System.out.println("Type: Hybrid-Powered");
        } else if (type == 2) {
            System.out.println("Type: Electric-Power");
        } else {
            System.out.println("Invalid choice. Defaulting to Electric Tesla.");
        }

        scanner.close();
    }
}

class Toyota extends Vehicle {
    public Toyota() {
        super("Toyota", 100); // Speed is 100
    }
}

class Hyundai extends Vehicle {
    public Hyundai() {
        super("Hyundai", 500); // Speed is 500
    }
}