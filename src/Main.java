import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();
        CitizenCollection citizenCollection = new CitizenCollection();
        Citizen currentCitizen = null;

        manager.loadFromFile();

        System.out.println("Welcome to the Digital Government Service Management System");

        boolean running = true;
        while (running) {
            Menu.welcomeMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter your full name: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }
                    System.out.print("Enter your 16-digit National ID: ");
                    String nationalId = sc.nextLine().trim();
                    try {
                        currentCitizen = new Citizen(name, nationalId);
                        citizenCollection.addCitizen(currentCitizen);
                        System.out.println("Registration successful!");
                        System.out.println(currentCitizen);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (currentCitizen == null) {
                        System.out.print("Enter your 16-digit National ID: ");
                        String id = sc.nextLine().trim();
                        currentCitizen = citizenCollection.findByNationalId(id);
                        if (currentCitizen == null) {
                            System.out.println("Citizen not found. Please register first.");
                            break;
                        }
                    }

                    Menu.serviceMenu();
                    int serviceChoice;
                    try {
                        serviceChoice = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                        break;
                    }

                    GovermrntService service;
                    switch (serviceChoice) {
                        case 1:
                            service = new DrivingLicenseService(currentCitizen);
                            break;
                        case 2:
                            service = new CriminalRecordService(currentCitizen);
                            break;
                        case 3:
                            service = new DivorceService(currentCitizen);
                            break;
                        case 4:
                            service = new PassportRequest(currentCitizen);
                            break;
                        default:
                            System.out.println("Invalid service choice.");
                            continue;
                    }

                    System.out.println("\n--- Service Requirements ---");
                    System.out.println(service.requirement());
                    System.out.println("----------------------------");

                    System.out.print("Enter payment amount (" + service.getFee() + " Rwf): ");
                    double payment;
                    try {
                        payment = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid payment amount.");
                        break;
                    }

                    ServiseApplication app = new ServiseApplication(currentCitizen, service);

                    if (payment == service.getFee()) {
                        service.processSevice();
                        manager.addApplication(app);
                        try {
                            manager.ApprovaApplication(app.getApplicationId());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("Application submitted and approved!");
                    } else if (payment > 0 && payment < service.getFee()) {
                        service.processSevice();
                        manager.addApplication(app);
                        System.out.println("Partial payment received. Application is PENDING.");
                    } else {
                        System.out.println("Invalid payment. Application not submitted.");
                        break;
                    }
                    System.out.println("Your Application ID: " + app.getApplicationId());
                    break;

                case 3:
                    System.out.print("Enter your 16-digit National ID: ");
                    String profileId = sc.nextLine().trim();
                    Citizen found = citizenCollection.findByNationalId(profileId);
                    if (found != null) {
                        System.out.println("\n--- Citizen Profile ---");
                        System.out.println(found);
                    } else {
                        System.out.println("Citizen not found. Please register first.");
                    }
                    break;

                case 4:
                    manager.report();
                    break;

                case 5:
                    System.out.print("Enter Application ID: ");
                    String searchId = sc.nextLine().trim();
                    try {
                        ServiseApplication foundApp = manager.findById(searchId);
                        System.out.println("\n=====Application Details======");
                        System.out.println("Application ID : " + foundApp.getApplicationId());
                        System.out.println("Name : " + foundApp.getCitizen().getName());
                        System.out.println("National ID : " + foundApp.getCitizen().getNationalID());
                        System.out.println("Service : " + foundApp.getService().getServiceName());
                        System.out.println("Status : " + foundApp.getStatus());
                        System.out.println("==============================");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    manager.displayAll();
                    break;

                case 7:
                    System.out.println("Thank you for using the Digital Government Service Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}
