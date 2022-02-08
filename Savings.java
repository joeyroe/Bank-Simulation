package programmingassignment5;


public class Savings extends Account{
	
public Savings() { //default constructor
		
	}
	
	public Savings(int accountNum, double accountBalance) {
		
		super(accountNum, accountBalance);
	}

	@Override
	public void deposit(double depositAmount) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            depositAmount = Math.round(depositAmount * roundingScale) / roundingScale;
        
		if(depositAmount < 0) {
			this.accountBalance += 0; //does nothing to the balance
		}
		
		else {
			this.accountBalance += depositAmount; //adds to current balance
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
		}
		
	}

	@Override
	public void withdraw(double withdrawAmount) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            withdrawAmount = Math.round(withdrawAmount * roundingScale) / roundingScale;
            
		if(withdrawAmount > this.accountBalance) {
			this.accountBalance += 0; //does nothing to the balance
		}
		
		else {
			this.accountBalance -= withdrawAmount;//subtracts from current balance
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
		}
		
	}

	@Override
	public void transfer(double transferAmount, Account a) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            transferAmount = Math.round(transferAmount * roundingScale) / roundingScale;

            
		if(transferAmount > this.accountBalance) {
			this.accountBalance += 0; //does nothing to balance
		}
		
		else {
			this.accountBalance -= transferAmount;//subtracts from account
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
			a.accountBalance += transferAmount;//adds to desired account
		}

		
	}

}
