package hospitalManagementSystem;

public class Payment {
	 private double amount;
	 private String paymentMethod;

	    public Payment(double amount, String paymentMethod) {
	        this.amount = amount;
	        this.paymentMethod = paymentMethod;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public String getPaymentMethod() {
	        return paymentMethod;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public void setPaymentMethod(String paymentMethod) {
	        this.paymentMethod = paymentMethod;
	    }
	    public void displayPaymentReceipt() {
	    	System.out.println("Amount Paid: " + amount);
	        System.out.println("Payment Method: " + paymentMethod);
	    }
	    public void displayPaymentHistory() {
	        System.out.println("Amount Paid: " + amount);
	        System.out.println("Payment Method: " + paymentMethod);
	    }
}
