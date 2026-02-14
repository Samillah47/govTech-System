public abstract class GovermrntService {
    protected String serviceName;
    protected double fee;

    public GovermrntService(String serviceName, double fee) {
        this.serviceName = serviceName;
        this.fee = fee;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getFee() {
        return fee;
    }

    public abstract void processSevice();
    
}
