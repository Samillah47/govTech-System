public class Menu {

    public static void welcomeMenu() {
        System.out.println("\n========================================");
        System.out.println("  Digital Government Service Management");
        System.out.println("========================================");
        System.out.println("1. Register as a Citizen");
        System.out.println("2. Apply for a Service");
        System.out.println("3. View Profile");
        System.out.println("4. View Reports");
        System.out.println("5. Search Application");
        System.out.println("6. View All Applications");
        System.out.println("7. Change Application Status");
        System.out.println("8. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    public static void serviceMenu() {
        System.out.println("\n--- Available Services ---");
        System.out.println("1. Birth Certificate");
        System.out.println("2. Driving Licence");
        System.out.println("3. Criminal Record");
        System.out.println("4. Divorce Certificate");
        System.out.println("5. Passport Request");
        System.out.print("Choose a service: ");
    }

    public static void statusMenu() {
        System.out.println("\n--- Change Status ---");
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        System.out.print("Choose action: ");
    }
}
