import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();

        manager.loadFromFile();

        try {
            System.out.println("Enter Citizen's National ID: ");
            String citizenId = sc.nextLine();

            System.out.println("Enter Citize's name: ");
            String citizenName = sc.nextLine();

            Citizen citizen = new Citizen(citizenName, citizenId);

            System.out.println("Choose Service");
            System.out.println("1. Divorce Certificate");
            System.out.println("2. Passpport Request");

            int choice = sc.nextInt();

        
            GovermrntService service;

            if (choice == 1){
                service = new DivorceService(citizen);
            }else{
                service= new PassportRequest(citizen);
            }

            ServiseApplication app = new ServiseApplication(citizen, service);
            manager.addApplication(app);
            System.out.println("Application Submitted Successfully");
            System.out.println("Application ID: "+ app.getApplicaionId());

            manager.ApprovaApplication(app.getApplicaionId());
            manager.displayAll();

            manager.report();


        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
        sc.close();
    }
    
}
