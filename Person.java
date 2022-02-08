package programmingassignment5;

public abstract class Person {
	
	String firstName;
	String lastName;
	String DOB;
	String phoneNum;
	String address;
	String email;
	//account3 account;
	
	//setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	/*
	public void setAccount(account3 account) {
		this.account = account;
	}
	*/
	
	//getters
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDOB() {
		return this.DOB;
	}
	
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getEmail() {
		return this.email;
	}
	/*
	public account3 getAccount() {
		return this.account;
	}
	*/
	//abstract methods
	public abstract void setID(int ID);
	public abstract int getID();
	public abstract void setCheckingAccount(Account account);
	public abstract void setSavingsAccount(Account account);
	public abstract void setCreditCard(CreditCard account);
	public abstract Account getChecking();
	public abstract Account getSavings();
	public abstract Account getCreditCard();
	public abstract void printCustomerInfo();
	public abstract void setPassword(String password);
	public abstract String getPassword();
}
