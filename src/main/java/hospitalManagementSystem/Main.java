package hospitalManagementSystem;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Hospital hospital = new Hospital();
		hospital.loadFromFile();
		boolean running = true;
		//V1.6.01
		
		while(running) {
			System.out.println("========================================");
			System.out.println("      HOSPITAL MANAGEMENT SYSTEM");
			System.out.println("========================================");
			System.out.println("1. Add Patient");
			System.out.println("2. Update Patient");
			System.out.println("3. Patient Records");
			System.out.println("4. Billing and Payments");
			System.out.println("5. File Management");
			System.out.println("0. Exit");
			System.out.print("Enter : ");
		int choice = InputHelper.readInt(sc);
		switch(choice) {
		case 1:
			System.out.println("Adding Patient.");
			System.out.println("Patient ID: ");
			int patientid = InputHelper.readInt(sc);
			System.out.println("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.println("Age: ");
			int age = InputHelper.readAge(sc);
			sc.nextLine();
			System.out.println("Gender: ");
			String gender = InputHelper.readGender(sc);
			
			
			Patient p = new Patient(patientid, name, age, gender);
			boolean addingDiagnosis = true;
			while (addingDiagnosis) {

			    System.out.println("Enter Diagnosis: ");
			    String diagnosis = sc.nextLine();

			    p.addDiagnosis(diagnosis);

			    System.out.println("Add another diagnosis? (Y/N): ");
			    addingDiagnosis = InputHelper.readYesNo(sc);
			}
			try {
			    hospital.addPatient(p);
			} catch (DuplicatePatientIdException e) {
			    System.out.println(e.getMessage());
			}
			break;
		case 2:
			boolean searching = true;
			int upd = -1;
			Patient New = null;
			while(searching) {
			System.out.println("You Choose To Update Patient");
			System.out.println("Enter the Patient ID: ");
			 upd = InputHelper.readInt(sc);
			sc.nextLine();
			if(upd == 0) {
				break;
			}
			New = hospital.searchPatient(upd);
			if(New != null) {
				
				System.out.println("Patient Has Found");
				New.displayMedicalRecord();
				searching = false;
			}
			else {
				System.out.println("Patient Not Found");
				continue;
			}
			}
			if(upd == 0) {
				break;
			}
				boolean updating = true;
				while(updating) {
					System.out.println("========================================");
					System.out.println("      UPDATE PATIENT");
					System.out.println("========================================");
					System.out.println("1. Name");
					System.out.println("2. Age");
					System.out.println("3. Gender");
					System.out.println("4. Billing Amount");
					System.out.println("5. Senior Citizen");
					System.out.println("6. Insurance Coverage");
					System.out.println("7. Diagnosis");
					System.out.println("8. Prescriptions");
					System.out.println("9. Display Updated Patient ");
					System.out.println("0. Back");
					int updatechoice = 	InputHelper.readInt(sc);
					sc.nextLine();
					
					switch(updatechoice) {
					case 1:
						System.out.print("New Name: ");
						String newname = sc.nextLine();
						New.setName(newname);
						System.out.println("Successfully Updated: " + newname);
						break;
					case 2:
						System.out.println("New Age: ");
						int newage = InputHelper.readAge(sc);
						sc.nextLine();
						New.setAge(newage);
						System.out.println("Successfully Updated: " + newage);
						break;
					case 3:
						System.out.println("New Gender: ");
						String newgender = InputHelper.readGender(sc);
						New.setGender(newgender);
						System.out.println("Successfully Updated: " + newgender);
						break;
					case 4:
						System.out.println("New Billing Amount: ");
						double newbilling = InputHelper.readNonNegativeDouble(sc);
						sc.nextLine();
						New.setBillingamount(newbilling);
						System.out.println("Successfully Updated: " + newbilling);
						break;
					case 5:
						Boolean yesseniorcitizen;
						System.out.println("Senior Citizen(Y/N): ");
						yesseniorcitizen = InputHelper.readYesNo(sc);
						double discountAmount2;
						if(yesseniorcitizen) {
							System.out.println("Discount Amount(%): ");
							discountAmount2 = InputHelper.readPercentage(sc);
							sc.nextLine();
							System.out.println("Successfully Updated: " + discountAmount2);
						}
						else {
							discountAmount2 = 0;
						}
						New.setDiscountamount(discountAmount2);
						New.setseniorCitizen(yesseniorcitizen);
						break;
					case 6:
						 System.out.println("New Insurance Coverage: ");
						    double newInsuranceCoverage = InputHelper.readNonNegativeDouble(sc);
						    sc.nextLine();

						    New.setInsurancecoverage(newInsuranceCoverage);

						    System.out.println("Successfully Updated: " + newInsuranceCoverage);
						    break;
					case 7:
						boolean diagnosisMenu = true;

					    while (diagnosisMenu) {
					        System.out.println("========================================");
					        System.out.println("         UPDATE DIAGNOSES");
					        System.out.println("========================================");
					        System.out.println("1. Add Diagnosis");
					        System.out.println("2. Remove Diagnosis");
					        System.out.println("3. Replace All Diagnoses");
					        System.out.println("4. Back");

					        int diagnosisChoice = InputHelper.readInt(sc);
					        sc.nextLine();

					        switch (diagnosisChoice) {
					        case 1:
					        	
					        	 boolean addingDiagnoses = true;

					        	    while (addingDiagnoses) {

					        	        System.out.println("Enter Diagnosis: ");
					        	        String newDiagnosis = sc.nextLine();

					        	        New.addDiagnosis(newDiagnosis);

					        	        System.out.println("Diagnosis Added.");

					        	        System.out.println("Do you Want to Add Another Diagnosis (Y/N): ");
					        	        addingDiagnoses = InputHelper.readYesNo(sc);
					        	    }
					            break;

					        case 2:
					        	  if (New.getDiagnoses().isEmpty()) {
					                  System.out.println("No diagnoses recorded.");
					                  break;
					              }
					              System.out.println("Current Diagnoses:");
					              for (int i = 0; i < New.getDiagnoses().size(); i++) {
					                  System.out.println((i + 1) + ". " + New.getDiagnoses().get(i));
					              }

					              System.out.println("Enter diagnosis number to remove: ");
					              int removeDiagnosis = InputHelper.readInt(sc);
					              sc.nextLine();

					              if (removeDiagnosis >= 1 &&
					                  removeDiagnosis <= New.getDiagnoses().size()) {

					                  String diagnosisToRemove = New.getDiagnoses().get(removeDiagnosis - 1);

					                  New.removeDiagnosis(diagnosisToRemove);

					                  System.out.println("Diagnosis Removed.");
					              } else {
					                  System.out.println("Invalid diagnosis number.");
					              }
					              break;

					        case 3:
					        	 ArrayList<String> replacementDiagnoses = new ArrayList<>();
					        	 
					        	 boolean addingdiagnosis = true;
					        	 while(addingdiagnosis) {
					        	 System.out.println("Enter Diagnosis: ");
					             String replacementDiagnosis = sc.nextLine();
					             replacementDiagnoses.add(replacementDiagnosis);
					             System.out.println("Add another diagnosis? (Y/N): ");
					             addingdiagnosis = InputHelper.readYesNo(sc);

					        	 }
					        	 New.setDiagnoses(replacementDiagnoses);

					        	    System.out.println("All Diagnoses Replaced.");
					        	    break;
					        case 4:
					            diagnosisMenu = false;
					            break;

					        default:
					            System.out.println("Invalid choice.");
					        }
					    }
					    break;
				
					case 8:
					    boolean prescriptionMenu = true;

					    while (prescriptionMenu) {
					        System.out.println("========================================");
					        System.out.println("       PRESCRIPTION MANAGEMENT");
					        System.out.println("========================================");
					        System.out.println("1. Add Prescription");
					        System.out.println("2. Remove Prescription");
					        System.out.println("3. Replace All Prescriptions");
					        System.out.println("4. Back");

					        int prescriptionChoice = InputHelper.readInt(sc);
					        sc.nextLine();

					        switch (prescriptionChoice) {

					        case 1:
					        	boolean addingPrescription = true;
					        	while(addingPrescription) {
					        		System.out.println("Medicine: ");
					        	    String medicine = sc.nextLine();

					        	    System.out.println("Dosage: ");
					        	    String dosage = sc.nextLine();

					        	    System.out.println("Instructions: ");
					        	    String instructions = sc.nextLine();

					        	    Prescription prescription = new Prescription(medicine, dosage, instructions);

					        	    New.addPrescription(prescription);

					        	    System.out.println("Prescription Added.");
					        	    System.out.println("Add another prescription? (Y/N)");
					                addingPrescription = InputHelper.readYesNo(sc);
					               
					        	}
					        	 	
					            break;

					        case 2:
					           if (New.getPrescriptions().isEmpty()) {
					        	   System.out.println("No Prescriptions Recorded");
					        	   break;
					           }
					           System.out.println("Current Prescriptions. ");
					           for (int i = 0; i < New.getPrescriptions().size(); i++) {
					               System.out.println("");
					               System.out.println("Prescription " + (i + 1));
					               New.getPrescriptions().get(i).display();
					           }
					           System.out.println();
					           System.out.println("Enter prescription number to remove:");

					           int removePrescription = InputHelper.readInt(sc);
					           sc.nextLine();
					           
					          if(removePrescription >= 1 && removePrescription <= New.getPrescriptions().size()) {
					        	  Prescription prescriptionToRemove = New.getPrescriptions().get(removePrescription - 1);

					              New.removePrescription(prescriptionToRemove);
					              System.out.println("Prescription Removed.");
					              } 
					            else { 
					              System.out.println("Invalid prescription number.");
					          }
					            break;
					        case 3:
					        	
					        	 if (New.getPrescriptions().isEmpty()) {
					        	        System.out.println("No prescriptions recorded.");
					        	        break;
					        	    }

					        	    ArrayList<Prescription> newPrescriptions = new ArrayList<>();

					        	    System.out.println("Enter new prescriptions.");
					        	    System.out.println("Enter 0 as the medicine name to finish.");

					        	    while (true) {

					        	        System.out.println("Medicine: ");
					        	        String newmedicine = sc.nextLine();

					        	        if (newmedicine.equals("0")) {
					        	            break;
					        	        }

					        	        System.out.println("Dosage: ");
					        	        String newdosage = sc.nextLine();

					        	        System.out.println("Instructions: ");
					        	        String newinstructions = sc.nextLine();

					        	        Prescription newprescription =
					        	                new Prescription(newmedicine, newdosage, newinstructions);

					        	        newPrescriptions.add(newprescription);

					        	        System.out.println("Prescription Added.");
					        	    }

					        	    New.setPrescriptions(newPrescriptions);

					        	    System.out.println("All prescriptions replaced.");
					            break;

					        case 4:
					            prescriptionMenu = false;
					            hospital.saveToFile();
					            break;
					        default:
					            System.out.println("Invalid choice.");
					        }
					    }
					    break;
					case 9:
						New.displayMedicalRecord();
						break;
					case 0:
						updating = false;
						hospital.saveToFile();
						break;
						default:
							System.out.println("❌ Invalid update option. Please select 1–9.");
						break;
					}
				}
			break;
		case 3:
			 boolean dataanalysis = true;
			    while (dataanalysis) {
			        System.out.println();
			        System.out.println("========================================");
			        System.out.println("          PATIENT RECORDS");
			        System.out.println("========================================");
			        System.out.println("1. Search by Name");
			        System.out.println("2. Search by Age");
			        System.out.println("3. Sort all Billing (Highest to lowest)");
			        System.out.println("4. Sort all Billing (Lowest to highest)");
			        System.out.println("5. Average Billing");
			        System.out.println("6. Highest Billing");
			        System.out.println("7. Lowest Billing");
			        System.out.println("8. Sort Patients by Name");
			        System.out.println("9. Count Patients");
			        System.out.println("10. Search Patient by ID");
			        System.out.println("11. Display All Patients");
			        System.out.println("12. High billing Patients");
			        System.out.println("13. Delete Patient");
			        System.out.println("0. Back");
			        System.out.print("Enter : ");
			        int choice2 = InputHelper.readInt(sc);
			        switch (choice2) {
			        case 1:
			        	sc.nextLine();
			        	System.out.println("Search Name: ");
			        	String searchname = sc.nextLine();
			        	hospital.searchPatientByName(searchname);
			        	break;
			        case 2:
			        	System.out.println("Note: This searches for patients within the specified age range.");
			        	System.out.println("Minimum Age: ");
			        	int minAge = InputHelper.readAge(sc);

			        	System.out.println("Maximum Age: ");
			        	int maxAge = InputHelper.readAge(sc);
			        	hospital.searchPatientByAgeRange(minAge, maxAge);
			        	break;
			        case 3:
			        	hospital.sortPatientBillingDescending();
			        	break;
			        case 4:
			        	hospital.sortPatientBillingAscending();
			        	break;
			        case 5:
			            hospital.averageBillPatient();
			            break;
			        case 6:
			            hospital.highBillPatients();
			            break;
			        case 7:
			            hospital.lowBillPatients();
			            break;
			        case 8:
			            hospital.sortPatientNames();
			            break;
			        case 9:
			        	int total = hospital.countPatients();
						System.out.println(total);
						break;
			        case 10:
			        	System.out.println("Searching For Patients");
						System.out.println("Enter An ID: ");
						int id = InputHelper.readInt(sc);
						Patient result = hospital.searchPatient(id);
						if(result == null) {
							System.out.println("❌ Patient ID not found.");
						}
						else {
							result.displayMedicalRecord();
						}
			        	break;
			        case 11:
			        	hospital.displayAllPatients();
			        	break;
			        case 12:
			        	hospital.displayHighBillingPatients();
			        	break;
			        case 13:
			        	System.out.println("Delete Patient.");
						System.out.println("Enter ID: ");
						int removeid = InputHelper.readInt(sc);
						hospital.deletePatient(removeid);
			        	break;
			        case 0:
			            dataanalysis = false;
			            break;
			        default:
			            System.out.println("Invalid choice.");
			        }
			    }
			break;
		case 4:
			boolean billingandPayments = true;
			while (billingandPayments) {
			System.out.println("========================================");
			System.out.println("        BILLING AND PAYMENTS");
			System.out.println("========================================");
			System.out.println("1. Create Billing Statement");
			System.out.println("2. Make Payment");
			System.out.println("3. View Billing Statement");
			System.out.println("4. View Payment History");
			System.out.println("5. View Payment Receipt");
			System.out.println("0. Back");
			int choice2 = InputHelper.readInt(sc);
			
			switch(choice2) {
			case 1:
				System.out.println("Enter Patient ID: ");
				int patientId3 = InputHelper.readInt(sc);

				Patient b = hospital.searchPatient(patientId3);

				if (b == null) {
				    System.out.println("Patient not found.");
				    break;
				}
				
				System.out.println("Billing Amount: ");
				double amount = InputHelper.readNonNegativeDouble(sc);
				sc.nextLine();
				boolean seniorDiscount;
				System.out.println("Senior Citizen?(Y/N): ");
				seniorDiscount = InputHelper.readYesNo(sc);
				double discountAmount;
				
				if(seniorDiscount) {
					System.out.println("Discount Amount(%): ");
					discountAmount = InputHelper.readPercentage(sc);
					sc.nextLine();
				}
				else {
					discountAmount = 0;
				}
				System.out.println("Insurance Coverage: ");
				double insuranceCoverage = InputHelper.readNonNegativeDouble(sc);
				sc.nextLine();
				
				b.setBillingamount(amount);
				b.setDiscountamount(discountAmount);
				b.setInsurancecoverage(insuranceCoverage);
				b.setseniorCitizen(seniorDiscount);
				System.out.println("Billing Statement Succesfully Created");
				break;
			case 2:
				System.out.println("Enter Patient ID: ");
			    int patientId = InputHelper.readInt(sc);
			    Patient patient = hospital.searchPatient(patientId);
			    if (patient == null) {
			        System.out.println("Patient not found.");
			        break;
			    }
			    double remainingBalance = patient.getRemainingBalance();
			    System.out.println("Remaining Balance: " + remainingBalance);
			    if(remainingBalance <= 0) {
			    	System.out.println("This bill has already been fully paid.");
			        break;
			    }
			    System.out.println("Enter Payment Amount: ");
			    double paymentAmount = InputHelper.readNonNegativeDouble(sc);
			    if (paymentAmount > remainingBalance) {
			        System.out.println("Payment exceeds the remaining balance.");
			        break;
			    }
			    
			    sc.nextLine();
			    System.out.println("Payment Method: ");
			    String paymentMethod = sc.nextLine();
			    Payment payment = new Payment(paymentAmount, paymentMethod);
			    patient.addPayment(payment);
			    System.out.println("Payment successfully recorded.");
				break;
			
			case 3:

			    System.out.println("Enter Patient ID: ");
			    int patientId4 = InputHelper.readInt(sc);

			    Patient patient4 = hospital.searchPatient(patientId4);

			    if (patient4 == null) {
			        System.out.println("Patient not found.");
			        break;
			    }

			    patient4.displayBillingStatement();

				break;
			
			case 4:
					System.out.println("Enter Patient ID: ");
				    int patientId2 = InputHelper.readInt(sc);

				    Patient patient2 = hospital.searchPatient(patientId2);

				    if (patient2 == null) {
				        System.out.println("Patient not found.");
				        break;
				    }

				    if (patient2.getPayments().isEmpty()) {
				        System.out.println("No payment history found.");
				        break;
				    }

				    System.out.println("========================================");
				    System.out.println("          PAYMENT HISTORY");
				    System.out.println("========================================");

				    for (Payment payment2 : patient2.getPayments()) {
				        payment2.displayPaymentHistory();
				    System.out.println("----------------------------------------");
				    }
				break;
			case 5:
				System.out.println("Enter Patient ID: ");
				int patientID = InputHelper.readInt(sc);
				
				Patient patients = hospital.searchPatient(patientID);
				
				if(patients == null) {
					System.out.println("Patient not found");
					break;
				}
				if(patients.getPayments().isEmpty()) {
					System.out.println("No payments recorded");
					break;
				}
				System.out.println("========================================");
			    System.out.println("          AVAILABLE PAYMENTS");
			    System.out.println("========================================");
				for(int i = 0; i < patients.getPayments().size(); i++) {
					System.out.println((i + 1) + ".");
					patients.getPayments().get(i).displayPaymentReceipt();
					System.out.println("----------------------------------------");
				}
				System.out.println("Select Payment: ");
				int paymentchoice = InputHelper.readInt(sc);
				if(paymentchoice < 1 || paymentchoice > patients.getPayments().size()) {
					System.out.println("Invalid payment choice.");
					break;
				}
				 Payment selectedPayment =
				            patients.getPayments().get(paymentchoice - 1);

				    patients.displayPaymentReceipt(selectedPayment);
				break;
				
			case 0:
				billingandPayments = false;
				hospital.saveToFile();
				break;
			default:
				System.out.println("Invalid choice.");
				
			}
			}
			break;
		case 5:
			 boolean data = true;
			 while(data) {
				 	System.out.println("========================================");
			        System.out.println("           	FILE MANAGEMENT");
			        System.out.println("========================================");
			        System.out.println("1. Save to File");
			        System.out.println("2. Load from File");
			        System.out.println("0. Back");
			        System.out.print("Enter : ");
			        int choice3 = InputHelper.readInt(sc);
			        switch(choice3) {
			        case 1:
			            hospital.saveToFile();
			            System.out.println("Program Memory -> hospital_data.txt");
			            break;
			        case 2:
			        	hospital.loadFromFile();
			        	System.out.println("hospital_data.txt -> Program Memory");
			            break;
			        case 0:
			            data = false;
			            break;
			        default:
			            System.out.println("Invalid choice.");
			        }
			 }
			break;
		case 0:
		    running = false;
		    System.out.println("Thank you for using the Hospital Management System!");
		    break;
		default:
			System.out.println("❌ Invalid menu choice.");
			break;
		}
			
		}
}
}

