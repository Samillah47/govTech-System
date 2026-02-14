public class PassportRequest extends GovermrntService {
    private Citizen citizen;

    public PassportRequest(Citizen citizen) {
        super("Passport Request", 30000.0);
        this.citizen = citizen;
    }

    @Override
    public void processSevice() {
        System.out.println("Processing " + getServiceName() + " for " + citizen.getName());
        System.out.println("Fee: " + getFee() + " Rwf");
    }

    @Override
    public String requirement() {
        return "Valid National ID, Birth certificate, Passport photos, Fee: 30,000 Rwf";
    }
}
