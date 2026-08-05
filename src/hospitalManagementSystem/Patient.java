package hospitalManagementSystem;
import java.util.ArrayList;
public class Patient {
		private int patientid;
		private String name;
		private int age;
		private boolean seniorcitizen;
		private double discountamount;
		private double billingamount;
		private double insurancecoverage;
		private String gender;
		private ArrayList<String> diagnoses;
		private ArrayList<Prescription> prescriptions;
		private ArrayList<Payment> payments;
		public Patient(int patientid, String name, int age, double billingamount, double insurancecoverage, String gender, double discountamount, boolean seniorcitizen) {
			this.patientid = patientid;
			this.name = name;
			this.age = age;
			this.billingamount = billingamount;
			this.insurancecoverage= insurancecoverage;
			this.gender = gender;
			this.discountamount = discountamount;
			this.seniorcitizen = seniorcitizen;
			this.diagnoses = new ArrayList<>();
			this.prescriptions = new ArrayList<>();
			this.payments = new ArrayList<>();
		}
		public Patient(
		        int patientid,
		        String name,
		        int age,
		        String gender
		        ) {
		    this.patientid = patientid;
		    this.name = name;
		    this.age = age;
		    this.gender = gender;

		    this.diagnoses = new ArrayList<>();
		    this.prescriptions = new ArrayList<>();
		    this.payments = new ArrayList<>();
		}
		public String toFileString() {
			
			String diagnosisData = String.join("|", diagnoses);
			
		    StringBuilder prescriptionData = new StringBuilder();

		    for (int i = 0; i < prescriptions.size(); i++) {

		        Prescription prescription = prescriptions.get(i);

		        prescriptionData.append(prescription.getMedicine())
		                        .append("~")
		                        .append(prescription.getDosage())
		                        .append("~")
		                        .append(prescription.getInstructions());

		        if (i < prescriptions.size() - 1) {
		            prescriptionData.append(";");
		        }
		    }
		    StringBuilder paymentData = new StringBuilder();
		    for(int i = 0; i < payments.size(); i++) {
		    	Payment payment = payments.get(i);
		    	
		    	paymentData.append(payment.getAmount())
		    					.append("~")
		    					.append(payment.getPaymentMethod());
		    	if(i < payments.size() - 1) {
		    		paymentData.append(";");
		    	}
		    }
		    return patientid + "," +
	           name + "," +
	           age + "," +
	           gender + "," +
	           billingamount + "," +
	           seniorcitizen + "," +
	           discountamount + "," +
	           insurancecoverage + "," +
	           diagnosisData + "," +
	           prescriptionData + "," +
		    paymentData;
		}
		public int getPatientid() {
			return patientid;
		}
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
		public double getBillingamount() {
			return billingamount;
		}
		public double getInsurancecoverage() {
			return insurancecoverage;
		}
		public String getGender() {
			return gender;
		}
		public double getDiscountamount() {
			return discountamount;
		}
		public boolean getseniorCitizen() {
			return seniorcitizen;
		}
		public double getFinalBill() {
			 return billingamount - insurancecoverage - getDiscountValue();
		}
		public double getDiscountValue() {
			return billingamount * (discountamount / 100);
		}
		public String getSeniorCitizenStatus() {
			if(seniorcitizen == true) {
				return "YES";
			}
			else {
				return "NO";
			}
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public void setBillingamount(double billing) {
			this.billingamount = billing;
		}
		public void setInsurancecoverage(double insurance) {
			this.insurancecoverage = insurance;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public void setDiscountamount(double discount) {
			this.discountamount = discount;
		}
		public void setseniorCitizen(boolean senior) {
			this.seniorcitizen = senior;
		}
		public void setDiagnoses(ArrayList<String> diagnoses) {
		    this.diagnoses = diagnoses;
		}
		public void addDiagnosis(String diagnosis) {
		    diagnoses.add(diagnosis);
		}
		public ArrayList<String> getDiagnoses() {
		    return diagnoses;
		}
		public void removeDiagnosis(String diagnosis) {
		    diagnoses.remove(diagnosis);
		}
		public void addPrescription(Prescription prescription) {
		    prescriptions.add(prescription);
		}

		public void removePrescription(Prescription prescription) {
		    prescriptions.remove(prescription);
		}

		public ArrayList<Prescription> getPrescriptions() {
		    return prescriptions;
		}
		public void setPrescriptions(ArrayList<Prescription> prescriptions) {
		    this.prescriptions = prescriptions;
		}
		public void addPayment(Payment payment) {
		    payments.add(payment);
		}
		public ArrayList<Payment> getPayments() {
		    return payments;
		}
		public double getTotalPaid() {
			double totalPaid = 0;
			for(Payment payment : payments) {
				totalPaid += payment.getAmount();
			}
			return totalPaid;
		}
		public double getRemainingBalance() {
			return getFinalBill() - getTotalPaid();
		}
		public String getPaymentStatus() {
			if (getTotalPaid() == 0) {
				return "UNPAID";
			}
			if (getTotalPaid() < getFinalBill()) {
				return "PARTIALLY PAID";
			}
			return "PAID";
		}
		public void setPayments(ArrayList<Payment> payments) {
		    this.payments = payments;
		}
		public void displayMedicalRecord() {
			 	System.out.println("===================================");
			    System.out.println("---------MEDICAL RECORD-------");
			    System.out.println("===================================");
			    System.out.println("Patient ID: " + patientid);
			    System.out.println("Name: " + name);
			    System.out.println("Age: " + age);
			    System.out.println("Gender: " + gender);
			    System.out.println("Senior Citizen: " + getSeniorCitizenStatus());
			    System.out.println("-----------------------------------");
			    System.out.println("Diagnoses:");
			    if (diagnoses.isEmpty()) {
			        System.out.println("No diagnoses recorded.");
			    } else {
			        for (String diagnosis : diagnoses) {
			            System.out.println("- " + diagnosis);
			        }
			    }
			    System.out.println("-----------------------------------");
			    System.out.println("Prescriptions:");

			    if (prescriptions.isEmpty()) {
			        System.out.println("No prescriptions recorded.");
			    } else {
			        for (Prescription prescription : prescriptions) {
			            prescription.display();
			        }
			    }
			    System.out.println("===================================");
		}
		public void displayBillingStatement() {
			 	System.out.println("===================================");
			    System.out.println("----------BILLING STATEMENT--------");
			    System.out.println("===================================");
			    System.out.println("Patient ID: " + patientid);
			    System.out.println("Name: " + name);
			    System.out.println("Age: " + age);
			    System.out.println("Gender: " + gender);
			    System.out.println("-----------------------------------");
			    System.out.println("Original Amount: " + billingamount);
			    System.out.println("Discount Value: " + getDiscountValue());
			    System.out.println("Insurance Coverage: " + insurancecoverage);
			    System.out.println("Amount Due: " + getFinalBill());
			    System.out.println("===================================");
			}    
		public void displayPaymentReceipt(Payment payment) {
			        System.out.println("========================================");
			        System.out.println("           PAYMENT RECEIPT");
			        System.out.println("========================================");
			        System.out.println("Patient ID: " + patientid);
			        System.out.println("Patient Name: " + name);
			        System.out.println("----------------------------------------");
			        System.out.println("Amount Paid: " + payment.getAmount());
			        System.out.println("Payment Method: " + payment.getPaymentMethod());
			        System.out.println("----------------------------------------");
			        System.out.println("Total Paid: " + getTotalPaid());
			        System.out.println("Remaining Balance: " + getRemainingBalance());
			        System.out.println("Payment Status: " + getPaymentStatus());
			        System.out.println("========================================");
			    }
		
	
}	