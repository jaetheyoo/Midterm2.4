package customerInfo.model;

public class CustomerSave {
	private static Customer customer;
	
	public static void setCustomer(){
		
	}

	public Customer getCustomer() {
		return customer;
	}

	public static void setCustomer(Customer customer) {
		CustomerSave.customer = customer;
	}
}
