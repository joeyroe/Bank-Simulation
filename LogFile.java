package programmingassignment5;

import java.io.PrintWriter;

public class LogFile implements Log{

	PrintWriter log;
	
	public LogFile() {
		
	}
	
	public LogFile(PrintWriter log) {//Lewis Roe 
		this.log = log;
	}
	
	public PrintWriter getPrintWriter() {//Lewis Roe 
		return this.log;
	}
	
	@Override
	public void writeDepositChecking(double amount, Person p) {//Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " deposited " + "$" + 
	amount + " into  Checking-" + p.getChecking().getAccountNum() +", new balance: $" + 
				p.getChecking().getAccountBalance());
		
	}

	@Override
	public void writeTransferCtoS(Person p, double amount) { //checking to savings //Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Checking-" + p.getChecking().getAccountNum() +" to Savings-" + 
							p.getSavings().getAccountNum());
					
		
	}


	@Override
	public void writeDepositSavings(double amount, Person p) {//Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " deposited " + "$" + 
	amount + " into Savings-" + p.getSavings().getAccountNum() +", new balance: $" + 
				p.getSavings().getAccountBalance());
		
	}

	@Override
	public void writeWithdrawChecking(double amount, Person p) {//Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " withdrew " + "$" + 
	amount + " from Checking-" + p.getChecking().getAccountNum() +", new balance: $" + 
				p.getChecking().getAccountBalance());
		
	}

	@Override
	public void writeWithdrawSavings(double amount, Person p) {//Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " withdrew " + "$" + 
	amount + " from Savings-" + p.getSavings().getAccountNum() +", new balance: $" + 
				p.getSavings().getAccountBalance());
		
	}

	@Override
	public void writePay(Person p1, Person p2, double amount) { //paying someone //Lewis Roe 
		log.println(p1.getFirstName() + " " + p1.getLastName() + " payed " + "$" + 
				amount + " from Checking-" + p1.getChecking().getAccountNum() +" by " + 
							p2.getFirstName() + " " + p2.getLastName());
		
	}

	@Override
	public void writeTransferCtoCC(Person p, double amount) { //checking to credit //Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Checking-" + p.getChecking().getAccountNum() +" to Credit-" + 
							p.getCreditCard().getAccountNum());
		
	}

	@Override
	public void writeTransferStoC(Person p, double amount) { //savings to checking //Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Savings-" + p.getSavings().getAccountNum() +" to Checking-" + 
							p.getChecking().getAccountNum());
		
	}

	@Override
	public void writeTransferStoCC(Person p, double amount) { //savings to credit //Lewis Roe 
		log.println(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Savings-" + p.getSavings().getAccountNum() +" to Credit-" + 
							p.getCreditCard().getAccountNum());
		
	}
	
	
}
