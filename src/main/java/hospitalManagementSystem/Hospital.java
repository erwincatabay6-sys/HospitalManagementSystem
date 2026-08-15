package hospitalManagementSystem;
import java.util.*;
import java.io.*;
import java.util.logging.*;
public class Hospital {
	private static final Logger logger = Logger.getLogger(Hospital.class.getName());
	private static final String FILE_NAME = "hospital_data.txt";
	private HashMap<Integer, Patient> cases = new HashMap<>();
	private ArrayList<String> diagnoses = new ArrayList<>();
	public Hospital() {
	    logger.setUseParentHandlers(true);
	}
	public void addPatient(Patient patient) throws DuplicatePatientIdException{
				int patientid = patient.getPatientid();
			    if (idExists(patient.getPatientid())) {
			    	logger.warning("Duplicate Patient ID Detected." + patientid);
			    	 throw new DuplicatePatientIdException("Patient ID Already Exists: " + patientid);
			    }
			    cases.put(patientid, patient);
			    saveToFile();
			    logger.info("Patient Successfully Added: " + patientid);
		    }
	public Patient searchPatient(int patientid) {
		return cases.get(patientid);
	}
	public void displayAllPatients() {
		if(cases.isEmpty()) {
		System.out.println("Patient Lists are Empty");
		}
		else {
			for(Patient patient : cases.values()) {
			patient.displayMedicalRecord();
		}
		}
	}
	public void displayHighBillingPatients() {
		List <Patient> highBillingPatients = cases.values()
		.stream()
		.filter(patient -> patient.getBillingamount() > 5000)
		.toList();
		
		if(highBillingPatients.isEmpty()) {
			System.out.println("No Patient exceeded billing amount over 5000");
		}
		else {
			highBillingPatients.forEach(Patient::displayBillingStatement);
		}
		
	}
	public int countPatients() {
		return cases.size();
	}
	public boolean idExists(int patientid) {
		 return cases.containsKey(patientid);
	}
	public void deletePatient(int patientid) {
		Patient patient = cases.get(patientid);
		if(patient != null) {
			cases.remove(patientid);
			logger.info("Patient Has been Deleted");
			saveToFile();
		}
		else {
			logger.warning("Patient ID not found");
		}
	}
	public void averageBillPatient() {
	    if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }
	    double totalBilling = 0;

	    for (Patient patient : cases.values()) {
	        totalBilling += patient.getBillingamount();
	    }
	    double average = totalBilling / cases.size();

	    System.out.printf("Average Billing: %.2f%n", average);
	}
	public void highBillPatients() {
	    if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }
	    Patient highest = cases.values().iterator().next();

	    for (Patient patient : cases.values()) {
	        if (patient.getBillingamount() > highest.getBillingamount()) {
	            highest = patient;
	        }
	    }
	    System.out.println("Highest Billing Patient:");
	    highest.displayBillingStatement();
	}
	public void lowBillPatients() {
	    if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }

	    Patient lowest = cases.values().iterator().next();
	    for (Patient patient : cases.values()) {
	        if (patient.getBillingamount() < lowest.getBillingamount()) {
	            lowest = patient;
	        }
	    }
	    System.out.println("Lowest Billing Patient:");
	    lowest.displayBillingStatement();
	}
	public void sortPatientBillingAscending() {
	    if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }

	    ArrayList<Patient> sortedPatients = new ArrayList<>(cases.values());

	    Collections.sort(sortedPatients,
	            (patient1, patient2) -> Double.compare(patient1.getBillingamount(),patient2.getBillingamount()));
	                                   
	    System.out.println("===== Patients Sorted by Billing (Lowest to Highest) =====");

	    for (Patient patient : sortedPatients) {
	        patient.displayBillingStatement();
	    }
	}
	public void sortPatientNames() {
	    if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }
	    ArrayList<Patient> sortedPatients = new ArrayList<>(cases.values());

	    Collections.sort(sortedPatients,
	            (patient1, patient2) ->
	                    patient1.getName().compareToIgnoreCase(patient2.getName()));

	    System.out.println("===== Patients Sorted by Name =====");

	    for (Patient patient : sortedPatients) {
	        patient.displayMedicalRecord();
	    }
	}
	public void saveToFile() {
	    try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
	        for (Patient patient : cases.values()) {
	            writer.println(patient.toFileString());
	        }
	        System.out.println("File written successfully!");
	    } catch (IOException e) {
	        System.out.println("Error saving file.");
	        e.printStackTrace();
	    }
	}
	public void loadFromFile() {
		File file = new File(FILE_NAME);

		if (!file.exists()) {
		    try {
		        file.createNewFile();
		        System.out.println("Created hospital_data.txt");
		        System.out.println("No saved patients found.");
		    } catch (IOException e) {
		        System.out.println("Failed to create file.");
		    }
		    return;
		}
		try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
			String line;
			boolean loaded = false;
			while((line = reader.readLine()) != null) {
				if(line.trim().isEmpty()) {
					continue;
				}
				loaded = true;
				String[] data = line.split(",", -1);
				if (data.length != 11) {
				    System.out.println("Invalid patient record: missing or extra data fields. Skipping record.");
				    continue;
				}
				int patientid = Integer.parseInt(data[0]);
				String name = (data[1]);
				int age = Integer.parseInt(data[2]);
				String gender = (data[3]);
				double billingamount = Double.parseDouble(data[4]);
				boolean seniorcitizen = Boolean.parseBoolean(data[5]);
				double discountamount = Double.parseDouble(data[6]);
				double insurancecoverage = Double.parseDouble(data[7]);
				
				ArrayList<String> diagnoses = new ArrayList<>();
				if (!data[8].isEmpty()) {
				    String[] diagnosisData = data[8].split("\\|");

				    for (String diagnosis : diagnosisData) {
				        diagnoses.add(diagnosis);
				    }
				}
				ArrayList<Prescription> prescriptions = new ArrayList<>();

				if (!data[9].isEmpty()) {

				    String[] prescriptionData = data[9].split(";");

				    for (String prescriptionEntry : prescriptionData) {

				        String[] prescriptionFields = prescriptionEntry.split("~");

				        String medicine = prescriptionFields[0];
				        String dosage = prescriptionFields[1];
				        String instructions = prescriptionFields[2];

				        Prescription prescription =
				                new Prescription(medicine, dosage, instructions);

				        prescriptions.add(prescription);
				    }
				}
				ArrayList<Payment> payments = new ArrayList<>();
				if(!data[10].isEmpty()) {
					String[] paymentData = data[10].split(";");
					for(String paymentEntry : paymentData) {
						String[] paymentFields = paymentEntry.split("~");
						
						double amount = Double.parseDouble(paymentFields[0]);
						String paymentMethod = paymentFields[1];
						
						Payment payment = new Payment(amount, paymentMethod);
						payments.add(payment);
					}
						
				}
				
				Patient patient = new Patient(patientid, name, age, billingamount, insurancecoverage, gender, discountamount, seniorcitizen);
				patient.setDiagnoses(diagnoses);
				patient.setPrescriptions(prescriptions);
				patient.setPayments(payments);
				try {
				    addPatient(patient);
				} catch (DuplicatePatientIdException e) {
				    System.out.println(e.getMessage());
				}
			}
			if(!loaded) {
				System.out.println("No Saved Patients Found.");
			}
			System.out.println("File loaded successfully!");
		}
		catch(IOException e) {
			System.out.println("Error Loading File.");
		}
		catch (NumberFormatException e) {
		    System.out.println("Invalid data format in the file.");
		}	
	}
	public void addDiagnosis(String diagnosis) {
	    diagnoses.add(diagnosis);
	}

	public ArrayList<String> getDiagnoses() {
	    return diagnoses;
	}

	public void setDiagnoses(ArrayList<String> diagnoses) {
	    this.diagnoses = diagnoses;
	}
	public void searchPatientByName(String name) {
		if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }
		 boolean found = false;
		for(Patient patient : cases.values()) {
			if(patient.getName().toLowerCase().contains(name.toLowerCase())) {
				patient.displayMedicalRecord();
				found = true;
			}
			}
			if (!found) {
			    System.out.println("No patients found.");
			}
	}
	public void searchPatientByAgeRange(int minAge, int maxAge) {
		if (cases.isEmpty()) {
	        System.out.println("There are no patients registered");
	        return;
	    }
		boolean found = false;
		for (Patient patient : cases.values()) {
				if(patient.getAge() >= minAge && maxAge <= patient.getAge()) {
				patient.displayMedicalRecord();
				found = true;
				}
		}
		if (!found) {
		    System.out.println("No patients found.");
		}
	}
	public void sortPatientBillingDescending() {
		 if (cases.isEmpty()) {
		        System.out.println("There are no patients registered");
		        return;
		    }
		List <Patient> sortedPatients = new ArrayList<>(cases.values());
		Collections.sort(sortedPatients,
	            (patient1, patient2) -> Double.compare(patient2.getBillingamount(),patient1.getBillingamount()));
		System.out.println("===== Patients Sorted by Billing (Highest to Lowest) =====");

	    for (Patient patient : sortedPatients) {
	        patient.displayBillingStatement();
	    }
	}
	
	public void updatePatient(int patientid, String newName, int newAge, double newBilling, double newInsuranceCoverage, String gender, double discountamount, boolean seniorcitizen) {
		Patient patient = cases.get(patientid);
		if(patient != null) {
			patient.setName(newName);
			patient.setAge(newAge);
			patient.setGender(gender);
			patient.setBillingamount(newBilling);
			patient.setInsurancecoverage(newInsuranceCoverage);
			patient.setseniorCitizen(seniorcitizen);
			patient.setDiscountamount(discountamount);
			saveToFile();
			
		}
		
	
	}
		
}
