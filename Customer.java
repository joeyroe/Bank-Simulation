package programmingassignment5;

public class Customer extends Person{

	int ID;
	String password;
	Account checkings;
	Account savings;
	CreditCard creditCard;
	
	//default constructor
	public Customer() {
		
	}
	
	//constructor
	public Customer(String firstName, String lastName, String address, String DOB, 
			String phoneNum, int ID, Account checkings, Account savings, CreditCard creditCard,
			String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.DOB = DOB;
		this.phoneNum = phoneNum;
		this.ID = ID;
		this.checkings = checkings;
		this.savings = savings;
		this.creditCard = creditCard;
		this.password = password;
	}
	
	@Override 
	public void printCustomerInfo() {
		
		System.out.println(this.firstName + " " + this.lastName);
		if(this.checkings.accountNum != 0) {
			System.out.println("Checking-" + this.checkings.accountNum + ": $" + 
			this.checkings.accountBalance);
		}
		
		if(this.savings.accountNum != 0) {
			System.out.println("Savings-" + this.savings.accountNum + ": $" + 
			this.savings.accountBalance);
		}
		
		if(this.creditCard.accountNum != 0) {
			System.out.println("Credit-" + this.creditCard.accountNum + ": $" + 
			this.creditCard.accountBalance);
			System.out.println("Credit Max: " + creditCard.creditMax);
		}
		System.out.println();
		if(this.email != "") {
			System.out.println("Email Address: " + this.email);
		}
	}

	//the abstract methods
	@Override public void setID(int ID) {
		this.ID = ID;
	}

	@Override public int getID() {
		return this.ID;
	}

	@Override public void setCheckingAccount(Account checkings) {
		this.checkings = checkings;
	}
	
	@Override public void setSavingsAccount(Account savings) {
		this.savings = savings;
	}
	
	@Override public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Override public Account getChecking() {
		return this.checkings;
	}
	
	@Override public Account getSavings() {
		return this.savings;
	}
	
	@Override public Account getCreditCard() {
		return this.creditCard;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
		
	}

	@Override
	public String getPassword() {
		return this.password;
	}
}

