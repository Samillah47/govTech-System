public class Citizen {
    private String name;
    private String nationalID;

    public Citizen(String name, String nationalID) {
        if (nationalID == null || nationalID.length() != 16 || !nationalID.matches("\\d{16}")) {
            throw new IllegalArgumentException("National ID must be exactly 16 digits");
        }
        this.name = name;
        this.nationalID = nationalID;
    }

    public String getName() {
        return name;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationalID(String nationalID) {
        if (nationalID == null || nationalID.length() != 16 || !nationalID.matches("\\d{16}")) {
            throw new IllegalArgumentException("National ID must be exactly 16 digits");
        }
        this.nationalID = nationalID;
    }

    @Override
    public String toString() {
        return "Citizen{name='" + name + "', nationalID='" + nationalID + "'}";
    }
}
