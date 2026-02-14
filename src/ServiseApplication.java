public class ServiseApplication {

    private static int applicationCounter = 1;
    private int applicaionId;
    private Citizen citizen;
    private GovermrntService service;   
    private String status;

    public ServiseApplication(Citizen citizen, GovermrntService service) {
        this.applicaionId = applicationCounter++;
        this.citizen = citizen;
        this.service = service;
        this.status = "Pending";
    }
    
    public ServiseApplication(int id, Citizen citizen, GovermrntService service, String status) {
        this.applicaionId = id;
        this.citizen = citizen;
        this.service = service;
        this.status = status;
    }

    public int getApplicaionId() {
        return applicaionId;
    }
    
    public void setApplicaionId(int id) {
        this.applicaionId = id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public GovermrntService getService() {
        return service;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public static void setApplicationCounter(int counter) {
        applicationCounter = counter;
    }  

    public String toFileString() {
        return 
        "Application ID: " + applicaionId + 
        " \nCitizen: " + citizen.getName() + 
        " \nService: " + service.getServiceName() + 
        " \nStatus: " + status;
    }
    
}
