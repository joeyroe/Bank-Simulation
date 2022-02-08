package programmingassignment5;


import java.util.*;
import java.io.*;

public class MainBank4 {

	public static void main(String[] args) {//Lewis Roe & Solomon Davis

		try {
			
			PrintWriter aFile = new PrintWriter("log.txt");
			LogFile log = new LogFile(aFile);
			HashMap<String, Person> h = readFile("CS 3331 - Bank Users 5(2).csv");
			Scanner scan = new Scanner(System.in);
			String userChoice = " "; // used for the while loop
			String fileName;
                        List<String> bankList = new ArrayList();
                        BankStatement bs = new BankStatement(bankList);

			fileName = "Transaction Actions (1).csv";

			while (!userChoice.equals("n")) {
				System.out.println("Enter first name below \n*NOTE: if your a bank manager type bank");
				String firstName = scan.nextLine();
				System.out.println("Enter last name below\n*NOTE: if your a bank manager type manager");
				String lastName = scan.nextLine();
				String name = firstName + lastName;
				if (name.equals("bankmanager")) {
					GetBankManagersChoice(h, fileName, log, bs);

				} else {
					System.out.println("Enter password below");
					String password = scan.nextLine();
					boolean inHash = h.containsKey(name);
					if (inHash == true) {
						Person currentUser = h.get(name);
						if (currentUser.getPassword().equals(password)) {
							currentUser.printCustomerInfo();
							userDisplayOption(currentUser, h, log, bs);
						}else{
                                                    System.out.println("Password didn't match customer");
                                                }
					}else{
                                        System.out.println("Customer not found");
                                        }
				}
				userChoice = "J";
				while(!userChoice.equals("N") && !userChoice.equals("n") && !userChoice.equals("Y") && !userChoice.equals("y")){
					System.out.println("Do you want to continue?\nTYPE N - No\nTYPE Y - YES");
					userChoice = scan.nextLine();
				}
			}
                        updatedFile("CS 3331 - Bank Users 5(2).csv", h);
                        aFile.close();

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static HashMap<String, Person> readFile(String fileName) {//Lewis Roe

		try {
			File theFile = new File(fileName);
			Scanner scan = new Scanner(theFile);
			HashMap<String, Person> bankUsers = new HashMap<String, Person>();

			String[] temp = scan.nextLine().split(","); // first row
			temp[0] = temp[0].substring(3); // gets rid of three unwanted characters at the beginning

			// keeps track of the indices for the categories
			int firstNameIndex = -1;
			int lastNameIndex = -1;
			int addressIndex = -1;
			int phoneNumIndex = -1;
			int ID_index = -1;
			int DOB_index = -1;
			int savingsAccountNumIndex = -1;
			int checkingAccountNumIndex = -1;
			int creditAccountNumIndex = -1;
			int savingsBalanceIndex = -1;
			int checkingBalanceIndex = -1;
			int creditBalanceIndex = -1;
			int creditMaxIndex = -1;
			int emailIndex = -1;
			int passwordIndex = -1;

			// assigns the indices
			for (int i = 0; i < temp.length; i++) {

                            switch (temp[i]) {
                                case "First Name":
                                    firstNameIndex = i;
                                    break;
                                case "Last Name":
                                    lastNameIndex = i;
                                    break;
                                case "Date of Birth":
                                    DOB_index = i;
                                    break;
                                case "Identification Number":
                                    ID_index = i;
                                    break;
                                case "Address":
                                    addressIndex = i;
                                    break;
                                case "Phone Number":
                                    phoneNumIndex = i;
                                    break;
                                case "Checking Account Number":
                                    checkingAccountNumIndex = i;
                                    break;
                                case "Savings Account Number":
                                    savingsAccountNumIndex = i;
                                    break;
                                case "Credit Account Number":
                                    creditAccountNumIndex = i;
                                    break;
                                case "Checking Starting Balance":
                                    checkingBalanceIndex = i;
                                    break;
                                case "Savings Starting Balance":
                                    savingsBalanceIndex = i;
                                    break;
                                case "Credit Starting Balance":
                                    creditBalanceIndex = i;
                                    break;
                                case "Credit Max":
                                    creditMaxIndex = i;
                                    break;
                                case "Password":
                                    passwordIndex = i;
                                    break;
                                case "Email":
                                    emailIndex = i;
                                    break;
                                default:
                                    break;
                            }
			}

			scan.close();
			scan = new Scanner(theFile); // refresh scanner
			scan.nextLine(); // skips first line

			while (scan.hasNextLine()) { // each line is a new person

				String[] temp2 = scan.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // regex
				// create a person to store everything
				Person accountHolder = new Customer();
				Account checking = new Checking();
				Account savings = new Savings();
				CreditCard credit = new CreditCard();

				// set everything from file to person attributes
				if (firstNameIndex != -1) {
					accountHolder.setFirstName(temp2[firstNameIndex]);
				} else if (firstNameIndex == -1) {
					accountHolder.setFirstName("");
				}
				if (lastNameIndex != -1) {
					accountHolder.setLastName(temp2[lastNameIndex]);
				} else if (lastNameIndex == 1) {
					accountHolder.setLastName("");
				}
				if (addressIndex != -1) {
					accountHolder.setAddress(temp2[addressIndex]);
				} else if (addressIndex == -1) {
					accountHolder.setAddress("");
				}
				if (phoneNumIndex != -1) {
					accountHolder.setPhoneNum(temp2[phoneNumIndex]);
				} else if (phoneNumIndex == -1) {
					accountHolder.setPhoneNum("");
				}
				if (DOB_index != -1) {
					accountHolder.setDOB(temp2[DOB_index]);
				} else if (DOB_index == -1) {
					accountHolder.setDOB("");
				}
				if (ID_index != -1) {
					accountHolder.setID(Integer.parseInt(temp2[ID_index]));
				} else if (ID_index == -1) {
					accountHolder.setID(-1);
				}
				if (passwordIndex != -1) {
					accountHolder.setPassword(temp2[passwordIndex]);
				} else if (passwordIndex == -1) {
					accountHolder.setPassword("");
				}
				if (emailIndex != -1) {
					accountHolder.setEmail(temp2[emailIndex]);
				} else if (emailIndex == -1) {
					accountHolder.setEmail("");
				}

				// sets up checking account
				if (checkingBalanceIndex != -1) {
					checking.setAccountBalance(Double.parseDouble(temp2[checkingBalanceIndex]));
				} else if (checkingBalanceIndex == -1) {
					checking.setAccountBalance(0);
				}
				if (checkingAccountNumIndex != -1) {
					checking.setAccountNum(Integer.parseInt(temp2[checkingAccountNumIndex]));
				} else if (checkingAccountNumIndex == -1) {
					checking.setAccountNum(0);
				}

				// sets up savings account
				if (savingsBalanceIndex != -1) {
					savings.setAccountBalance(Double.parseDouble(temp2[savingsBalanceIndex]));
				} else if (savingsBalanceIndex == -1) {
					savings.setAccountBalance(0);
				}
				if (savingsAccountNumIndex != -1) {
					savings.setAccountNum(Integer.parseInt(temp2[savingsAccountNumIndex]));
				} else if (savingsAccountNumIndex == -1) {
					savings.setAccountNum(0);
				}

				// sets up credit account
				if (creditBalanceIndex != -1) {
					credit.setAccountBalance(Double.parseDouble(temp2[creditBalanceIndex]));
				} else if (creditBalanceIndex == -1) {
					credit.setAccountBalance(0);
				}
				if (creditAccountNumIndex != -1) {
					credit.setAccountNum(Integer.parseInt(temp2[creditAccountNumIndex]));
				} else if (creditAccountNumIndex == -1) {
					credit.setAccountNum(0);
				}
				if (creditMaxIndex != -1) {
					credit.setCreditMax(Double.parseDouble(temp2[creditMaxIndex]));
				} else if (creditMaxIndex == -1) {
					credit.setCreditMax(0);
				}

				accountHolder.setCheckingAccount(checking);
				accountHolder.setSavingsAccount(savings);
				accountHolder.setCreditCard(credit);

				String name = accountHolder.getFirstName() + accountHolder.getLastName(); // used to keep track of
																							// persons
				bankUsers.put(name, accountHolder); // put in the hashmap

			}

			scan.close();
			return bankUsers;
		}

		catch (IOException e) {
			return null;
		}
	}

	public static void readTransactions(String fileName, HashMap<String, Person> h, 
			LogFile log, BankStatement bs) {//Lewis Roe

		try {
			File theFile = new File(fileName);
			Scanner scan = new Scanner(theFile);

			// to keep track of the indices of the categories
			int fromFirst_index = -1;
			int fromLast_index = -1;
			int fromWhere_index = -1;
			int action_index = -1;
			int toFirst_index = -1;
			int toLast_index = -1;
			int toWhere_index = -1;
			int amount_index = -1;

			String[] row1 = scan.nextLine().split(","); // the categories
			row1[0] = row1[0].substring(3);// gets rid of unwanted characters at the beginning

			// set all of the indices
			for (int i = 0; i < row1.length; i++) {
				if (row1[i].equals("From First Name")) {
					fromFirst_index = i;
				} else if (row1[i].equals("From Last Name")) {
					fromLast_index = i;
				} else if (row1[i].equals("From Where")) {
					fromWhere_index = i;
				} else if (row1[i].equals("Action")) {
					action_index = i;
				} else if (row1[i].equals("To First Name")) {
					toFirst_index = i;
				} else if (row1[i].equals("To Last Name")) {
					toLast_index = i;
				} else if (row1[i].equals("To Where")) {
					toWhere_index = i;
				} else if (row1[i].equals("Action Amount")) {
					amount_index = i;
				}
			}
                        
			while (scan.hasNextLine()) { // scan the whole file
				String[] line = scan.nextLine().split(","); // gets current line

				if (line[action_index].equals("deposits")) { // deposit
					double amount = Double.parseDouble(line[amount_index]);// sets deposit amount
					String name = line[toFirst_index] + line[toLast_index]; // gets the name of who's depositing

					if (h.containsKey(name) == true) {
						if (line[toWhere_index].equals("Checking")) { // deposits to name's ^^ checking
							h.get(name).getChecking().deposit(amount);
							log.writeDepositChecking(amount, h.get(name));
                                                        bs.writeDepositChecking(amount, h.get(name));
						} else if (line[toWhere_index].equals("Savings")) { // deposits to name's savings
							h.get(name).getSavings().deposit(amount);
							log.writeDepositSavings(amount, h.get(name));
                                                        bs.writeDepositSavings(amount, h.get(name));
						} else if (line[toWhere_index].equals("Credit")) { // " " credit
							h.get(name).getCreditCard().deposit(amount);
						}
					}
				}

				else if (line[action_index].equals("withdraws")) { // withdraw action
					double amount = Double.parseDouble(line[amount_index]); // sets amount
					String name = line[fromFirst_index] + line[fromLast_index]; // finds out whose withdrawing

					if (h.containsKey(name) == true) {

						if (line[fromWhere_index].equals("Checking")) { // withdraw from names checking
							h.get(name).getChecking().withdraw(amount);
							log.writeWithdrawChecking(amount, h.get(name));
                                                        bs.writeWithdrawChecking(amount, h.get(name));
						} else if (line[fromWhere_index].equals("Savings")) {// savings
							h.get(name).getSavings().withdraw(amount);
							log.writeWithdrawSavings(amount, h.get(name));
                                                        bs.writeWithdrawSavings(amount, h.get(name));
						} else if (line[fromWhere_index].equals("Credit")) {// credit
							h.get(name).getCreditCard().withdraw(amount);
						}
					}
				}

				else if (line[action_index].equals("transfers") || line[action_index].equals("pays")) {// transfer / pay
					double amount = Double.parseDouble(line[amount_index]); // gets the amount
					String name1 = line[fromFirst_index] + line[fromLast_index];// who's transferring
					String name2 = line[toFirst_index] + line[toLast_index];// who's getting the funds

					if (h.containsKey(name1) == true && h.containsKey(name2) == true) {

						if (line[fromWhere_index].equals("Checking")) {// checking
							if (line[toWhere_index].equals("Savings")) {// transfer from checking to savings
								h.get(name1).getChecking().transfer(amount, h.get(name2).getSavings());
								log.writeTransferCtoS(h.get(name1), amount);
                                                                bs.writeTransferCtoS(h.get(name1), amount);
							} else if (line[toWhere_index].equals("Credit")) { // from checking to credit
								h.get(name1).getChecking().transfer(amount, h.get(name2).getCreditCard());
								log.writeTransferCtoCC(h.get(name1), amount);
                                                                bs.writeTransferCtoCC(h.get(name1), amount);
							} else if (line[toWhere_index].equals("Checking")) { // from checking to checking
								h.get(name1).getChecking().transfer(amount, h.get(name2).getChecking());
								log.writePay(h.get(name1), h.get(name2), amount);
                                                                bs.writePay(h.get(name1), h.get(name2), amount);
							}
						}

						else if (line[fromWhere_index].equals("Savings")) {// savings
							if (line[toWhere_index].equals("Savings")) {// transfer from savings to savings
								h.get(name1).getSavings().transfer(amount, h.get(name2).getSavings());
							} else if (line[toWhere_index].equals("Credit")) {// from savings to credit
								h.get(name1).getSavings().transfer(amount, h.get(name2).getCreditCard());
								log.writeTransferStoCC(h.get(name1), amount);
                                                                bs.writeTransferStoCC(h.get(name1), amount);
							} else if (line[toWhere_index].equals("Checking")) {// from savings to checking
								h.get(name1).getSavings().transfer(amount, h.get(name2).getChecking());
								log.writeTransferStoC(h.get(name1), amount);
                                                                bs.writeTransferStoC(h.get(name1), amount);
							}
						}

						if (line[fromWhere_index].equals("Credit")) {// credit
							if (line[toWhere_index].equals("Savings")) {// from credit to savings
								h.get(name1).getChecking().transfer(0, h.get(name2).getSavings());
							} else if (line[toWhere_index].equals("Credit")) {// from credit to credit
								h.get(name1).getChecking().transfer(0, h.get(name2).getCreditCard());
							} else if (line[toWhere_index].equals("Checking")) {// from credit to checking
								h.get(name1).getChecking().transfer(0, h.get(name2).getChecking());
							}
						}
					}
				}

				else if (line[action_index].equals("inquires")) {// does nothing
					String name = line[fromFirst_index] + line[fromLast_index];

					if (h.containsKey(name) == true) {
						if (line[fromWhere_index].equals("Checking")) {
							h.get(name).getChecking().deposit(0);
						} else if (line[fromWhere_index].equals("Savings")) {
							h.get(name).getSavings().deposit(0);
						} else if (line[fromWhere_index].equals("Credit")) {
							h.get(name).getCreditCard().deposit(0);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static void updatedFile(String fileName, HashMap<String, Person> h) {//Lewis Roe

		try {
                    
                        //System.out.println(fileName);
			File originalFile = new File(fileName);
			Scanner scan = new Scanner(originalFile);

			String[] header = scan.nextLine().split(",");
			header[0] = header[0].substring(3); // gets rid of the uwanted characters at the beginning
			PrintWriter newFile = new PrintWriter("updatedUsers.csv");

			int firstNameIndex = -1;
			int lastNameIndex = -1;
			int addressIndex = -1;
			int phoneNumIndex = -1;
			int ID_index = -1;
			int DOB_index = -1;
			int savingsAccountNumIndex = -1;
			int checkingAccountNumIndex = -1;
			int creditAccountNumIndex = -1;
			int savingsBalanceIndex = -1;
			int checkingBalanceIndex = -1;
			int creditBalanceIndex = -1;
			int creditMaxIndex = -1;
			int emailIndex = -1;
			int passwordIndex = -1;

			// assigns the indices
			for (int i = 0; i < header.length; i++) {

				if (header[i].equals("First Name")) {
					firstNameIndex = i;
				}

				else if (header[i].equals("Last Name")) {
					lastNameIndex = i;
				}

				else if (header[i].equals("Date of Birth")) {
					DOB_index = i;
				}

				else if (header[i].equals("Identification Number")) {
					ID_index = i;
				}

				else if (header[i].equals("Address")) {
					addressIndex = i;
				}

				else if (header[i].equals("Phone Number")) {
					phoneNumIndex = i;
				}

				else if (header[i].equals("Checking Account Number")) {
					checkingAccountNumIndex = i;
				}

				else if (header[i].equals("Savings Account Number")) {
					savingsAccountNumIndex = i;
				}

				else if (header[i].equals("Credit Account Number")) {
					creditAccountNumIndex = i;
				}

				else if (header[i].equals("Checking Starting Balance")) {
					checkingBalanceIndex = i;
				}

				else if (header[i].equals("Savings Starting Balance")) {
					savingsBalanceIndex = i;
				}

				else if (header[i].equals("Credit Starting Balance")) {
					creditBalanceIndex = i;
				}

				else if (header[i].equals("Credit Max")) {
					creditMaxIndex = i;
				}

				else if (header[i].equals("Password")) {
					passwordIndex = i;
				} else if (header[i].equals("Email")) {
					emailIndex = i;
				}
			}

			scan.close();
			scan = new Scanner(originalFile);// refresh scanner
			scan.nextLine();// skips the first line

			newFile.println(header[firstNameIndex] + "," + header[lastNameIndex] + "," + header[DOB_index] + ","
					+ header[emailIndex] + "," + header[ID_index] + "," + header[passwordIndex] + ","
					+ header[addressIndex] + "," + header[phoneNumIndex] + "," + header[checkingAccountNumIndex] + ","
					+ header[savingsAccountNumIndex] + "," + header[creditAccountNumIndex] + ","
					+ header[checkingBalanceIndex] + "," + header[savingsBalanceIndex] + ","
					+ header[creditBalanceIndex] + "," + header[creditMaxIndex]);

			while (scan.hasNextLine()) {
				String[] temp = scan.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				String key = temp[firstNameIndex] + temp[lastNameIndex];
				Person temp2 = (Customer) h.get(key);
				CreditCard cc = (CreditCard) temp2.getCreditCard();

				newFile.println(temp2.getFirstName() + "," + temp2.getLastName() + "," + temp2.getDOB() + ","
						+ temp2.getEmail() + "," + temp2.getID() + "," + temp2.getPassword() + "," + temp2.getAddress()
						+ "," + temp2.getPhoneNum() + "," + temp2.getChecking().getAccountNum() + ","
						+ temp2.getSavings().getAccountNum() + "," + temp2.getCreditCard().getAccountNum() + ","
						+ temp2.getChecking().getAccountBalance() + "," + temp2.getSavings().getAccountBalance() + ","
						+ temp2.getCreditCard().getAccountBalance() + "," + cc.getCreditMax());

			}

			newFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("error fkj");
		}
	}

	public static void AddNewUser(HashMap h) {//Solomon Davis

		String userFirstName, userLastName, userDOB, userAddress, userPhoneNumber, userCheckingAcc = null,
				userCheckingBal = null, userSavingsAcc, userSavingsBal, userCreditAcc = null, userCreditBal = null,
				userCreditMax = null, userPassword = null, userEmail = null;

		int userID;

		char userCheckingAccountChoice, userCreditAccountChoice;

		Scanner kbd = new Scanner(System.in);

		System.out.println("What is the new user's first name?");
		userFirstName = kbd.nextLine();

		System.out.println("What is the new user's last name?");
		userLastName = kbd.nextLine();

		System.out.println("What is the new user's date of birth?\nFormat: 29-Dec-72");
		userDOB = kbd.nextLine();

		System.out.println("What is the new user's address?\nFormat: \"538 W. University Ave, El Paso, TX 79968\"");
		userAddress = kbd.nextLine();

		System.out.println("What is the new user's phone number?\nFormat: (915) 747-5038");
		userPhoneNumber = kbd.nextLine();

		System.out.println("What is the new user's email address?");
		userEmail = kbd.nextLine();

		System.out.println("What is the new user's password?");
		userPassword = kbd.nextLine();

		System.out.println("Does the new user have a checking account?\n" + "TYPE Y - Yes\n" + "TYPE N - No\n");
		userCheckingAccountChoice = kbd.nextLine().charAt(0);

		System.out.println("Does the new user have a credit account?\n" + "TYPE Y - Yes\n" + "TYPE N - No\n");
		userCreditAccountChoice = kbd.nextLine().charAt(0);

		userSavingsBal = "0";
		userSavingsAcc = "2" + h.size();

		switch (userCheckingAccountChoice) {
		case 'Y':
		case 'y':
			userCheckingBal = "0";
			userCheckingAcc = "1" + h.size();
			break;

		case 'N':
		case 'n':
			userCheckingBal = "0";
			userCheckingAcc = "0";
			break;

		}

		switch (userCreditAccountChoice) {
		case 'Y':
		case 'y':
			userCreditBal = "0";
			userCreditAcc = "3" + h.size();
			userCreditMax = "1000";
			break;

		case 'N':
		case 'n':
			userCreditBal = "0";
			userCreditAcc = "0";
			userCreditMax = "0";
			break;

		}

		userID = h.size() + 1;

		Person accountHolder = new Customer();
		Account checking = new Checking();
		Account savings = new Savings();
		CreditCard credit = new CreditCard();

		accountHolder.setFirstName(userFirstName);
		accountHolder.setLastName(userLastName);
		accountHolder.setDOB(userDOB);
		accountHolder.setEmail(userEmail);
		accountHolder.setID(userID);
		accountHolder.setPassword(userPassword);
		accountHolder.setAddress(userAddress);
		accountHolder.setPhoneNum(userPhoneNumber);
		accountHolder.setCheckingAccount(checking);
		accountHolder.setSavingsAccount(savings);
		accountHolder.setCreditCard(credit);
		checking.setAccountBalance(Double.parseDouble(userCheckingBal));
		checking.setAccountNum(Integer.parseInt(userCheckingAcc));
		savings.setAccountBalance(Double.parseDouble(userSavingsBal));
		savings.setAccountNum(Integer.parseInt(userSavingsAcc));
		credit.setAccountBalance(Double.parseDouble(userCreditBal));
		credit.setAccountNum(Integer.parseInt(userCreditAcc));
		credit.setCreditMax(1500);

		String name = userFirstName + userLastName;
		h.put(name, accountHolder);

	}

	public static void userDisplayOption(Person user, HashMap<String, Person> h, 
			LogFile log, BankStatement bs) {//Lewis Roe

		Scanner scan = new Scanner(System.in);
		String trans = "";
		//LogFile log = new LogFile(p);

		while (!trans.equals("5")) {

			System.out.println("Enter 1: to make a deposit");
			System.out.println("Enter 2 to make a withdraw");
			System.out.println("Enter 3 to make a transfer");
			System.out.println("Enter 4 to make a payment");
			System.out.println("Enter 5 to logout");

			try {
				trans = scan.next();

				if (trans.equals("1")) { //deposit
					System.out.println("Enter the amount you'd like to deposit");
					double amount = scan.nextDouble();
					System.out.println("Enter 1 to deposit to Checking");
					System.out.println("Enter 2 to deposit to Savings");

					String acct = scan.next();

					if (acct.equals("1")) { //checking
						System.out.println("Enter the amount you'd like to deposit");
						user.getChecking().deposit(amount);
						log.writeDepositChecking(amount, user);
                                                bs.writeDepositChecking(amount, user);
                                                user.printCustomerInfo();
                                                
					} else if (acct.equals("2")) { //savings
						user.getSavings().deposit(amount);
						log.writeDepositSavings(amount, user);
                                                bs.writeDepositSavings(amount, user);
                                                user.printCustomerInfo();
					} else {
						System.out.println("Not a choice");

					}
				} else if (trans.equals("2")) { //withdraw
					System.out.println("Enter the amount that you'd like to withdraw");
					double amount = scan.nextDouble();
					System.out.println("Enter 1 to withdraw from Checkings");
					System.out.println("Enter 2 to withdraw from Savigs");

					String acct = scan.next();
					if (acct.equals("1")) { //checking
						user.getChecking().withdraw(amount);
						log.writeWithdrawChecking(amount, user);
                                                bs.writeWithdrawChecking(amount, user);
                                                user.printCustomerInfo();
					} else if (acct.equals("2")) { //savings
						user.getSavings().withdraw(amount);
						log.writeWithdrawSavings(amount, user);
                                                bs.writeWithdrawSavings(amount, user);
                                                user.printCustomerInfo();
					}
				}

				else if (trans.equals("3")) { //transfer
					System.out.println("Enter 1 to transfer from Checking");
					System.out.println("Enter 2 to transfer from Savings");
					String account1 = scan.next();
					if (account1.equals("1")) { // from checking
						System.out.println("Enter 1 to transfer to Savings");
						System.out.println("Enter 2 to transfer to Credit");
						String account2 = scan.next();
						if (account2.equals("1")) { // from checking to savings
							System.out.println("Enter the transfer amount");
							double amount = scan.nextDouble();
							user.getChecking().transfer(amount, user.getSavings());
							log.writeTransferCtoS(user, amount);
                                                        bs.writeTransferCtoS(user, amount);
                                                        user.printCustomerInfo();
                                                        
						} else if (account2.equals("2")) { // from checking to credit
							System.out.println("Enter the transfer amount");
							double amount = scan.nextDouble();
							user.getChecking().transfer(amount, user.getCreditCard());
							log.writeTransferCtoCC(user, amount);
                                                        bs.writeTransferCtoCC(user, amount);
                                                        user.printCustomerInfo();
                                                }
					} else if (account1.equals("2")) { // from savings
						System.out.println("Enter 1 to transfer to Checking");
						System.out.println("Enter 2 to tranfer to Credit");
						String account2 = scan.next();
						if (account2.equals("1")) { // from savings to checking
							System.out.println("Enter the trasfer amount");
							double amount = scan.nextDouble();
							user.getSavings().transfer(amount, user.getChecking());
							log.writeTransferStoC(user, amount);
                                                        bs.writeTransferStoC(user, amount);
                                                        user.printCustomerInfo();
						} else if (account2.equals("2")) { // from savings to credit
							System.out.println("Enter the transfer amount");
							double amount = scan.nextDouble();
							user.getSavings().transfer(amount, user.getCreditCard());
							log.writeTransferStoCC(user, amount);
                                                        bs.writeTransferStoCC(user, amount);
                                                        user.printCustomerInfo();
						}
					}
					
				} else if (trans.equals("4")) {//pays
					Scanner scan2 = new Scanner(System.in);
					System.out.println("Enter the first name of the person you'd like to pay");
					String firstNameTo = scan2.nextLine();
					System.out.println(firstNameTo);
					System.out.println("Enter the last name of the person you'd like to pay");
					String lastNameTo = scan2.nextLine();
					String nameTo = firstNameTo + lastNameTo; // name of person user is paying
					System.out.println(nameTo);
					if (h.containsKey(nameTo) == true) { // checks if name is valid
						System.out.println("Enter payment amount");
						String amount = scan2.nextLine();
						//scan2.nextLine();
						user.getChecking().transfer(Double.parseDouble(amount), h.get(nameTo).getChecking());// pay only with checking
						log.writePay(user, h.get(nameTo), Double.parseDouble(amount));
                                                bs.writePay(user, h.get(nameTo), Double.parseDouble(amount));
                                                user.printCustomerInfo();
					}
					//scan2.close();
				}

			} catch (Exception e) {
				System.out.println("error");
			}

		}
		log.getPrintWriter().close();

	}
	
	
	public static void InquireByName(HashMap<String, Person> h) {//Solomon Davis
		String name = "", inquireChoice;
		int userAccountNum;
		Scanner kbd = new Scanner(System.in);

		System.out.println("What is the name of the customer you would like to inquire NO SPACES?");
		name = kbd.nextLine();

		System.out.println("Do yo want a specific account?\nTYPE 1 - YES\nTYPE 2 - NO");
		inquireChoice = kbd.nextLine();

		boolean inHash = h.containsKey(name);

		if (inquireChoice.equals("2")) {
			if (inHash == true) {
				Person currentUser = h.get(name);
				currentUser.printCustomerInfo();
			} else {
				System.out.println("Name not found");
			}

		} else if(inquireChoice.equals("1")){
			System.out.println("Type the account number of the account you want to access?");
			userAccountNum = kbd.nextInt();
			Person p = h.get(name);

			if (userAccountNum == p.getChecking().accountNum) {
				System.out.println(name);
				System.out.println("Checking Balance " + p.getChecking().getAccountBalance());

			} else if (userAccountNum == p.getSavings().accountNum) {
				System.out.println(name);
				System.out.println("Savings Balance " + p.getChecking().getAccountBalance());

			} else if (userAccountNum == p.getCreditCard().accountNum) {
				System.out.println(name);
				System.out.println("Credit Balance " + p.getChecking().getAccountBalance());
                        } else {
                            System.out.println("Account Number Not Found");
                           }
                } else {
                    System.out.println("Not a choice");
        }

    }
	
	public static void DisplayAllCustomerInfo(HashMap<String, Person> h){//Solomon Davis
		
		Set setOfKeys = h.keySet();
		
		Iterator hIterator = setOfKeys.iterator();
		
		while(hIterator.hasNext()) {
			String name = (String)hIterator.next();
			Person currentUser = h.get(name);
			currentUser.printCustomerInfo();
		}
	}

	public static void GetBankManagersChoice(HashMap<String, Person> h, String fileName, 
			LogFile log, BankStatement bs) throws IOException {//Solomon Davis

		String managersChoice = "";
		Scanner kbd = new Scanner(System.in);
		while (!managersChoice.equals("7")) {
			System.out.println("What do you want to do?\n" + "TYPE 1 - Inquire account by name\n"
					 + "TYPE 2 - Write a Bank Statement File\n"
					+ "TYPE 3 - Add a new customer\n" + "TYPE 4 - Read transaction file\n"
					+ "TYPE 5 - Display All Customer Info\n" + "TYPE 7 - Logout");

			try {
				managersChoice = kbd.nextLine();

				switch (managersChoice) {
				case "1":
					InquireByName(h);
					break;

				case "2":
					WriteBankStatement(h, bs);
					break;

				case "3":
					AddNewUser(h);
					break;

				case "4":
					readTransactions("Transaction Actions (1).csv", h, log, bs);
					//updatedFile("CS 3331 - Bank Users 5(2).csv", h);
					break;

				case "5":
					DisplayAllCustomerInfo(h);
					break;
				

				}
			} catch (Exception E) {
				System.out.println("Not a choice of the manager's");

			}

		}
	}
        
	public static void WriteBankStatement(HashMap<String, Person> h, BankStatement bs) throws IOException {//Solomon Davis
		
        String customerName, fileName;
        Scanner kbd;
        PrintWriter pw;
        File bankStatementFile;
        FileWriter fw;


        kbd = new Scanner(System.in);
        
        System.out.println("Who do you want to create a bank statement for? NO SPACES");
        customerName = kbd.nextLine();
        
        
        if(customerName.equals(h.get(customerName).firstName +h.get(customerName).lastName)){
            System.out.println("Customer found!!!");
            fileName = customerName + ".txt";
            pw = new PrintWriter(fileName);
            bankStatementFile = new File(fileName);
            fw = new FileWriter(bankStatementFile,true);
            double cBal, sBal, crBal, roundingScale;
         
            roundingScale = Math.pow(10,2);
             
            cBal = h.get(customerName).getChecking().accountBalance ;
            sBal = h.get(customerName).getSavings().accountBalance ;
            crBal = h.get(customerName).getCreditCard().accountBalance ;
            
            
            for (int cnt = 0; cnt < bs.getList().size(); cnt++) {
                if (("BP".equals(bs.getList().get(cnt).substring(0, 2))) && (h.get(customerName).getChecking().accountNum + "").equals(bs.getList().get(cnt).substring(12, 16))) {
                    cBal -= Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;   
                }else if (("PF".equals(bs.getList().get(cnt).substring(0, 2))) && (h.get(customerName).getChecking().accountNum + "").equals(bs.getList().get(cnt).substring(12, 16))) {
                    cBal += Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;       
                }else if (("WC".equals(bs.getList().get(cnt).substring(0, 2))) && (h.get(customerName).getChecking().accountNum + "").equals(bs.getList().get(cnt).substring(12, 16))) {
                    cBal += Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;   
                }else if (("WS".equals(bs.getList().get(cnt).substring(0, 2))) && (h.get(customerName).getSavings().accountNum + "").equals(bs.getList().get(cnt).substring(11, 15))) {
                    sBal += Double.parseDouble(bs.getList().get(cnt).substring(16, bs.getList().get(cnt).length()));
                    sBal = Math.round(sBal * roundingScale) / roundingScale;   
                }else if (("DC".equals(bs.getList().get(cnt).substring(0, 2))) && (h.get(customerName).getChecking().accountNum + "").equals(bs.getList().get(cnt).substring(12, 16))) {
                    cBal -= Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;   
                }else if (("DS".equals(bs.getList().get(cnt).substring(0, 2))) && (h.get(customerName).getSavings().accountNum + "").equals(bs.getList().get(cnt).substring(11, 15))) {
                    sBal -= Double.parseDouble(bs.getList().get(cnt).substring(16, bs.getList().get(cnt).length()));
                    sBal = Math.round(sBal * roundingScale) / roundingScale;   
                }else if (("TCCR".equals(bs.getList().get(cnt).substring(0, 4))) && (h.get(customerName).getSavings().accountNum + "").equals(bs.getList().get(cnt).substring(17, 21))) {
                    cBal += Double.parseDouble(bs.getList().get(cnt).substring(22, bs.getList().get(cnt).length()));
                    crBal -= Double.parseDouble(bs.getList().get(cnt).substring(22, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;
                    crBal = Math.round(cBal * roundingScale) / roundingScale;
                }else if (("TCS".equals(bs.getList().get(cnt).substring(0, 3))) && (h.get(customerName).getSavings().accountNum + "").equals(bs.getList().get(cnt).substring(12, 16))) {
                    cBal += Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    sBal -= Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;
                    sBal = Math.round(sBal * roundingScale) / roundingScale;   
                }else if (("TSCR".equals(bs.getList().get(cnt).substring(0, 4))) && (h.get(customerName).getSavings().accountNum + "").equals(bs.getList().get(cnt).substring(17, 21))) {
                    sBal += Double.parseDouble(bs.getList().get(cnt).substring(22, bs.getList().get(cnt).length()));
                    crBal -= Double.parseDouble(bs.getList().get(cnt).substring(22, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;
                    crBal = Math.round(crBal * roundingScale) / roundingScale;   
                }else if (("TSC".equals(bs.getList().get(cnt).substring(0, 3))) && (h.get(customerName).getSavings().accountNum + "").equals(bs.getList().get(cnt).substring(12, 16))) {
                    sBal += Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal -= Double.parseDouble(bs.getList().get(cnt).substring(17, bs.getList().get(cnt).length()));
                    cBal = Math.round(cBal * roundingScale) / roundingScale;
                    sBal = Math.round(sBal * roundingScale) / roundingScale;   
                }
                
            }

            
            
            fw.write("THE BANK OF UTEP\n"
                     +"500 W. University Ave, El Paso, TX 79968\n\n"
                     +"Customer Name: "+ h.get(customerName).firstName  + " " 
                     +h.get(customerName).lastName  + "\n"
                     +"Customer's Address: "+ h.get(customerName).address + "\n"
                     +"Customer's Phone Number: " 
                     + h.get(customerName).phoneNum + "\n\n"
                     +"Checking Starting Balance: " 
                     + cBal+"\n"
                     +"Savings Starting Balance: " 
                     + sBal+"\n"
                     +"Credit Starting Balance: " 
                     + crBal+"\n\n"
                     +"Date"+ "         "+"Description"
                     +"                                  \n\n");
            
            
            int nameLength = h.get(customerName).firstName.length()+h.get(customerName).lastName.length()+1;
              
            for (int i = 0; i < bs.getList().size(); i++) {

                if (bs.getList().get(i).substring(0, nameLength).equals(h.get(customerName).firstName + " " + h.get(customerName).lastName)) {

                    fw.write(java.time.LocalDate.now() + "   " + bs.getList().get(i) + "\n\n");
                }
            }
                     
            fw.write("Checking Final Balance: " 
                     + h.get(customerName).getChecking().accountBalance+"\n"
                     +"Savings Final Balance: " 
                     + h.get(customerName).getSavings().accountBalance+"\n"
                     +"Credit Final Balance: " 
                     + h.get(customerName).getCreditCard().accountBalance+"\n\n");
            
            System.out.println("Bank Statement Complete");
            fw.close();

        } else {
        
            System.out.println("Customer not found");
        
        }		
		
	} 

}
