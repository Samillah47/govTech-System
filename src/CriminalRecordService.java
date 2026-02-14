public class CriminalRecordService extends GovermrntService {
    private Citizen citizen;

    public CriminalRecordService(Citizen citizen) {
        super("Criminal Record", 1500.0);
        this.citizen = citizen;
    }

    @Override
    public void processSevice() {
        System.out.println("Processing " + getServiceName() + " for " + citizen.getName());
        System.out.println("Fee: " + getFee() + " Rwf");
    }

    @Override
    public String requirement() {
        return "Valid National ID, Fee: 1,500 Rwf";
    }
}
