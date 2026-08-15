package hospitalManagementSystem;

public class Prescription {
	private String medicine;
	private String dosage;
	private String instructions;
	
	 public Prescription(String medicine, String dosage, String instructions) {
	        this.medicine = medicine;
	        this.dosage = dosage;
	        this.instructions = instructions;
	    }
	 public String getMedicine() {
		 return medicine;
	 }
	 public String getDosage() {
		 return dosage;
	 }
	 public String getInstructions() {
		 return instructions;
	 }
	 public void setMedicine(String medicine) {
		 this.medicine = medicine;
	 }
	 public void setDosage(String dosage) {
		 this.dosage = dosage;
	 }
	 public void setInstructions(String instructions) {
		 this.instructions = instructions;
	 }
	public void display() {
	    System.out.println("Medicine: " + medicine);
	    System.out.println("Dosage: " + dosage);
	    System.out.println("Instructions: " + instructions);
	}
}
