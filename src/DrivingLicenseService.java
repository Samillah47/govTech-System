public class DrivingLicenseService extends GovermrntService {
    private Citizen citizen;

    public DrivingLicenseService(Citizen citizen) {
        super("Driving Licence", 10000.0);
        this.citizen = citizen;
    }

    @Override
    public void processSevice() {
        System.out.println("Processing " + getServiceName() + " for " + citizen.getName());
        System.out.println("Fee: " + getFee() + " Rwf");
    }

    @Override
    public String requirement() {
        return "Valid National ID, Age 18+, Medical certificate, Fee: 10,000 Rwf";
    }
}
