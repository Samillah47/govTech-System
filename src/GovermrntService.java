public abstract class GovermrntService {
    protected String serviceName;
    protected double fee;
    protected String status;

    public GovermrntService(String serviceName, double fee) {
        this.serviceName = serviceName;
        this.fee = fee;
        this.status = "Pending";
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void processSevice();

    public abstract String requirement();
}
