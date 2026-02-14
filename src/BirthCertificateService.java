public class BirthCertificateService extends GovermrntService {
    private Citizen citizen;

    public BirthCertificateService(Citizen citizen) {
        super("Birth Certificate", 5000.0);
        this.citizen = citizen;
    }

    @Override
    public void processSevice() {
        System.out.println("Processing " + getServiceName() + " for " + citizen.getName());
        System.out.println("Fee: " + getFee() + " Rwf");
    }

    @Override
    public String requirement() {
        return "Valid National ID, Hospital records, Fee: 5,000 Rwf";
    }
}
