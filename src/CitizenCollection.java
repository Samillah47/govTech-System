import java.util.HashMap;

public class CitizenCollection {

    private HashMap<String, Citizen> citizens = new HashMap<>();

    public void addCitizen(Citizen citizen) {
        citizens.put(citizen.getNationalID(), citizen);
    }

    public Citizen findByNationalId(String nationalId) {
        return citizens.get(nationalId);
    }

    public boolean citizenExists(String nationalId) {
        return citizens.containsKey(nationalId);
    }

    public int size() {
        return citizens.size();
    }

    public void displayAllCitizens() {
        if (citizens.isEmpty()) {
            System.out.println("No registered citizens.");
            return;
        }
        System.out.println("\n--- Registered Citizens ---");
        for (Citizen citizen : citizens.values()) {
            System.out.println(citizen);
        }
        System.out.println("---------------------------");
    }
}
