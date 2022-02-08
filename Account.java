package programmingassignment5;


public abstract class Account {
	
	int accountNum;
	double accountBalance;
	
	public Account() { //default constructor
		
	}
	
	public Account(int accountNum, double accountBalance) {
		this.accountNum = accountNum;
		this.accountBalance = accountBalance;
	}
	
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	
	//the getters
	public int getAccountNum() {
		return this.accountNum;
	}
	
	public double getAccountBalance() {
		return this.accountBalance;
	}
	
	//abstract methods
	public abstract void deposit(double depositAmount);
	public abstract void withdraw(double withdrawAmount);
	public abstract void transfer(double transferAmount, Account a);
}
