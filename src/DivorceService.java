public class DivorceService extends GovermrntService {
    private Citizen citizen;

    public DivorceService(Citizen citizen) {
        super("Divorce Service", 50000.0);
        this.citizen = citizen;
    }

    @Override
    public void processSevice() {
        System.out.println("Processing " + getServiceName() + " for " + citizen.getName());
        System.out.println("Fee: " + getFee() + " Rwf");
    }

    @Override
    public String requirement() {
        return "Valid National ID, Marriage certificate, Court order, Fee: 50,000 Rwf";
    }
}
