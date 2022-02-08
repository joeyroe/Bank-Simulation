package programmingassignment5;


public interface Log {
	
	public void writeDepositChecking(double amount, Person p);
	public void writeDepositSavings(double amount, Person p);
	public void writeWithdrawChecking(double amount, Person p);
	public void writeWithdrawSavings(double amount, Person p);
	public void writeTransferCtoS(Person p, double amount);
	public void writeTransferCtoCC(Person p, double amount);
	public void writeTransferStoC(Person p, double amount);
	public void writeTransferStoCC(Person p, double amount);
	public void writePay(Person p1, Person p2, double amount);
}
