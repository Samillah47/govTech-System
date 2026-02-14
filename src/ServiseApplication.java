import java.util.UUID;

public class ServiseApplication {

    private String applicationId;
    private Citizen citizen;
    private GovermrntService service;
    private String status;

    public ServiseApplication(Citizen citizen, GovermrntService service) {
        this.applicationId = UUID.randomUUID().toString();
        this.citizen = citizen;
        this.service = service;
        this.status = "Pending";
    }

    public ServiseApplication(String id, Citizen citizen, GovermrntService service, String status) {
        this.applicationId = id;
        this.citizen = citizen;
        this.service = service;
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String id) {
        this.applicationId = id;
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

    public void showProgress() {
        System.out.println("\n--- Application Progress ---");
        System.out.println("Application ID: " + applicationId);
        System.out.println("Citizen: " + citizen.getName());
        System.out.println("Service: " + service.getServiceName());
        System.out.println("Fee: " + service.getFee() + " Rwf");
        System.out.println("Status: " + status);
        System.out.println("Requirements: " + service.requirement());
        System.out.println("----------------------------");
    }

    @Override
    public String toString() {
        return "Application{id='" + applicationId + "', citizen='" + citizen.getName() +
                "', service='" + service.getServiceName() + "', status='" + status + "'}";
    }
}
