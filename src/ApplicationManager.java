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
    private  final String FILE_NAME = "applications.txt";

    public void addApplication(ServiseApplication app){
        applications.add(app);
        saveToFile();
    }

    public void ApprovaApplication(int id) throws ApplicationNotFound, InvalidStatus {
        ServiseApplication app = findById(id);

        if(app.getStatus() .equals("APPROVED")){
            throw new InvalidStatus("Application is already approved.");
        }else if(app.getStatus() .equals("REJECTED")){
            throw new InvalidStatus("Application is already rejected.");
        }else{
            app.setStatus("APPROVED");
            saveToFile();
            System.out.println("Application approved successfully.");
        }

    }
    
    public void RejectApplication(int id) throws ApplicationNotFound, InvalidStatus {
        ServiseApplication app = findById(id);

        if(app.getStatus() .equals("APPROVED")){
            throw new InvalidStatus("Application is already approved.");
        }else if(app.getStatus() .equals("REJECTED")){
            throw new InvalidStatus("Application is already rejected.");
        }else{
            app.setStatus("REJECTED");
            saveToFile();
            System.out.println("Application rejected successfully.");
        }

    }

    public ServiseApplication findById(int id) throws ApplicationNotFound {
        for (ServiseApplication app : applications) {
            if (app.getApplicaionId() == id) {
                    return app;
                }
        }
        throw new ApplicationNotFound("Application with ID " + id + " not found.");
    }

    public void displayAll(){
        for (ServiseApplication app : applications) {
            System.out.println("\n=====Details======"); 
            System.out.println("NationalId : " + app.getCitizen().getNationalID() + 
            "\nName : " + app.getCitizen().getName() + 
            "\nService : " + app.getService().getServiceName() + 
            "\nStatus : " + app.getStatus() );
            System.out.println("=====================");
        }

    }

    public void saveToFile(){
        try (BufferedWriter  writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (ServiseApplication app : applications){
                writer.write(app.toFileString()); 
                writer.newLine();
            }

        }catch(IOException e){ 
            System.out.println("Error saving applications to file: " + e.getMessage());


        }
    }

    public void loadFromFile(){
       try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        int maxId = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Application ID:")) {
                String appIdLine = line;
                String citizenLine = reader.readLine();
                String serviceLine = reader.readLine();
                String statusLine = reader.readLine();
                
                if (citizenLine != null && serviceLine != null && statusLine != null) {
                    int appId = Integer.parseInt(appIdLine.split(": ")[1].trim());
                    String name = citizenLine.split(": ")[1].trim();
                    String serviceName = serviceLine.split(": ")[1].trim();
                    String status = statusLine.split(": ")[1].trim();

                    if (appId > maxId) {
                        maxId = appId;
                    }

                    Citizen citizen = new Citizen(name, "N/A");
                    GovermrntService service;
                    if (serviceName.equals("Passport Request")) {
                        service = new PassportRequest(citizen);
                    } else if (serviceName.equals("Divorce Service")) {
                        service = new DivorceService(citizen);
                    } else {
                        continue; 
                    }

                    ServiseApplication app = new ServiseApplication(appId, citizen, service, status);
                    applications.add(app);
                }
            }
        }
        ServiseApplication.setApplicationCounter(maxId + 1);
       } catch (Exception e) {

        System.out.println("No records found: " + e.getMessage());
        
       }
    }
    
    public void report(){
        double total = 0 ;
        double divorceRevenues = 0;
        double passportRequestRevenue = 0 ;

        for (ServiseApplication app : applications){
            if (app.getStatus().equals("APPROVED")){
                total += app.getService().getFee();
                if (app.getService().getServiceName().equals("Divorce Service")){
                    divorceRevenues += app.getService().getFee();
                }else{
                    passportRequestRevenue += app.getService().getFee();
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("revenue_report.txt"))){
            writer.write("Revenue Report");
            writer.write("\n--------------");
            writer.write("\nTotal Revenues: "+ total); 
            writer.write("\nDivorce Revenues: "+ divorceRevenues);
            writer.write("\nPassport Request Revenues: "+ passportRequestRevenue);

        } catch (IOException e) {
            System.out.println("Error generating report.");
        }
        System.out.println("Revenue report generated");
    }

    
}
