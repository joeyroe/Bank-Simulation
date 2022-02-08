package programmingassignment5;


public class CreditCard extends Account{
	
	double creditMax;
	
	public CreditCard() { //default constructor
		
	}
	
	public CreditCard(int accountNum, double accountBalance, double creditMax) {
		this.accountNum = accountNum;
		this.accountBalance = accountBalance;
		this.creditMax = creditMax;
	}
	
	public void setCreditMax(double creditMax) {
		this.creditMax = creditMax;
	}
	
	public double getCreditMax() {
		return this.creditMax;
	}

	@Override
	public void deposit(double depositAmount) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            depositAmount = Math.round(depositAmount * roundingScale) / roundingScale;            
            
		if(depositAmount < 0) {
			this.accountBalance += 0;
		}
		else {
			this.accountBalance += depositAmount;
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
		}
		
	}

	@Override
	public void withdraw(double withdrawAmount) { //can't withdraw from credit account
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            withdrawAmount = Math.round(withdrawAmount * roundingScale) / roundingScale;            
            
		if(withdrawAmount > this.accountBalance) {
			this.accountBalance -= 0; //does nothing
		}
		else {
			this.accountBalance -= 0;
		}
	}

	@Override
	public void transfer(double transferAmount, Account a) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            transferAmount = Math.round(transferAmount * roundingScale) / roundingScale;            
            
		if(transferAmount > this.accountBalance) {
			this.accountBalance += 0;
		}
		else if((transferAmount + this.accountBalance) > creditMax){ //checks if it's under the max
			this.accountBalance += 0; //does nothing
		}
		
		else {
			this.accountBalance -= transferAmount;//subtracts from account
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
			a.accountBalance += transferAmount;//adds to desired account
		}
	}
	
	
}
