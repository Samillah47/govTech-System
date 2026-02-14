import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.ApplicationNotFound;
import Exceptions.InvalidStatus;

public class ApplicationManager {

    private ArrayList<ServiseApplication> applications = new ArrayList<>();
    private final String FILE_NAME = "applications.txt";

    public void addApplication(ServiseApplication app) {
        applications.add(app);
        saveToFile();
    }

    public void ApprovaApplication(String id) throws ApplicationNotFound, InvalidStatus {
        ServiseApplication app = findById(id);

        if (app.getStatus().equals("APPROVED")) {
            throw new InvalidStatus("Application is already approved.");
        } else if (app.getStatus().equals("REJECTED")) {
            throw new InvalidStatus("Application is already rejected.");
        } else {
            app.setStatus("APPROVED");
            saveToFile();
            System.out.println("Application approved successfully.");
        }
    }

    public void RejectApplication(String id) throws ApplicationNotFound, InvalidStatus {
        ServiseApplication app = findById(id);

        if (app.getStatus().equals("APPROVED")) {
            throw new InvalidStatus("Application is already approved.");
        } else if (app.getStatus().equals("REJECTED")) {
            throw new InvalidStatus("Application is already rejected.");
        } else {
            app.setStatus("REJECTED");
            saveToFile();
            System.out.println("Application rejected successfully.");
        }
    }

    public void changeApplicationStatus(String id, String newStatus) throws ApplicationNotFound, InvalidStatus {
        ServiseApplication app = findById(id);

        if (!newStatus.equals("APPROVED") && !newStatus.equals("REJECTED") && !newStatus.equals("Pending")) {
            throw new InvalidStatus("Invalid status. Use APPROVED, REJECTED, or Pending.");
        }

        app.setStatus(newStatus);
        saveToFile();
        System.out.println("Application status changed to " + newStatus);
    }

    public ServiseApplication findById(String id) throws ApplicationNotFound {
        for (ServiseApplication app : applications) {
            if (app.getApplicationId().equals(id)) {
                return app;
            }
        }
        throw new ApplicationNotFound("Application with ID " + id + " not found.");
    }

    public void displayAll() {
        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }
        for (ServiseApplication app : applications) {
            System.out.println("\n=====Details======");
            System.out.println("Application ID : " + app.getApplicationId() +
                    "\nNationalId : " + app.getCitizen().getNationalID() +
                    "\nName : " + app.getCitizen().getName() +
                    "\nService : " + app.getService().getServiceName() +
                    "\nStatus : " + app.getStatus());
            System.out.println("=====================");
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ServiseApplication app : applications) {
                writer.write("Application ID: " + app.getApplicationId());
                writer.newLine();
                writer.write("Citizen: " + app.getCitizen().getName());
                writer.newLine();
                writer.write("NationalID: " + app.getCitizen().getNationalID());
                writer.newLine();
                writer.write("Service: " + app.getService().getServiceName());
                writer.newLine();
                writer.write("Status: " + app.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving applications to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Application ID:")) {
                    String appIdLine = line;
                    String citizenLine = reader.readLine();
                    String nationalIdLine = reader.readLine();
                    String serviceLine = reader.readLine();
                    String statusLine = reader.readLine();

                    if (citizenLine != null && nationalIdLine != null && serviceLine != null && statusLine != null) {
                        String appId = appIdLine.split(": ")[1].trim();
                        String name = citizenLine.split(": ")[1].trim();
                        String nationalId = nationalIdLine.split(": ")[1].trim();
                        String serviceName = serviceLine.split(": ")[1].trim();
                        String status = statusLine.split(": ")[1].trim();

                        Citizen citizen;
                        try {
                            citizen = new Citizen(name, nationalId);
                        } catch (IllegalArgumentException e) {
                            citizen = new Citizen(name, "0000000000000000");
                        }

                        GovermrntService service;
                        switch (serviceName) {
                            case "Passport Request":
                                service = new PassportRequest(citizen);
                                break;
                            case "Divorce Service":
                                service = new DivorceService(citizen);
                                break;
                            case "Driving Licence":
                                service = new DrivingLicenseService(citizen);
                                break;
                            case "Criminal Record":
                                service = new CriminalRecordService(citizen);
                                break;
                            default:
                                continue;
                        }

                        ServiseApplication app = new ServiseApplication(appId, citizen, service, status);
                        applications.add(app);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No previous records found.");
        }
    }

    public void report() {
        double total = 0;
        double divorceRevenues = 0;
        double passportRequestRevenue = 0;
        double drivingLicenceRevenue = 0;
        double criminalRecordRevenue = 0;

        for (ServiseApplication app : applications) {
            if (app.getStatus().equals("APPROVED")) {
                total += app.getService().getFee();
                switch (app.getService().getServiceName()) {
                    case "Divorce Service":
                        divorceRevenues += app.getService().getFee();
                        break;
                    case "Passport Request":
                        passportRequestRevenue += app.getService().getFee();
                        break;
                    case "Driving Licence":
                        drivingLicenceRevenue += app.getService().getFee();
                        break;
                    case "Criminal Record":
                        criminalRecordRevenue += app.getService().getFee();
                        break;
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("revenue_report.txt"))) {
            writer.write("Revenue Report");
            writer.write("\n--------------");
            writer.write("\nTotal Revenues: " + total + " Rwf");
            writer.write("\nDivorce Revenues: " + divorceRevenues + " Rwf");
            writer.write("\nPassport Request Revenues: " + passportRequestRevenue + " Rwf");
            writer.write("\nDriving Licence Revenues: " + drivingLicenceRevenue + " Rwf");
            writer.write("\nCriminal Record Revenues: " + criminalRecordRevenue + " Rwf");
        } catch (IOException e) {
            System.out.println("Error generating report.");
        }
        System.out.println("\n--- Revenue Report ---");
        System.out.println("Total Applications: " + applications.size());
        System.out.println("Total Revenues: " + total + " Rwf");
        System.out.println("Divorce Revenues: " + divorceRevenues + " Rwf");
        System.out.println("Passport Request Revenues: " + passportRequestRevenue + " Rwf");
        System.out.println("Driving Licence Revenues: " + drivingLicenceRevenue + " Rwf");
        System.out.println("Criminal Record Revenues: " + criminalRecordRevenue + " Rwf");
        System.out.println("----------------------");
        System.out.println("Revenue report saved to revenue_report.txt");
    }

    public int getApplicationCount() {
        return applications.size();
    }
}
