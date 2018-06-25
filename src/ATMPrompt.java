import java.util.Scanner;

public class ATMPrompt {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ATM atm = new ATM();
		User curUser;

		// Set test data
		atm.addUser(new User("0000", 0));
		atm.addUser(new User("1111", 10));
		atm.addUser(new User("2222", 200));
		atm.addUser(new User("3333", 3000));
		atm.addUser(new User("4444", 40000));

		// Get user
		System.out.println("Enter four-digit PIN");
		while (true) {
			if (scan.hasNext("[0-9]{4}")) {
				curUser = atm.getUser(scan.nextLine());
				if (curUser != null) {
					break;
				}
				System.out.println("No user found. Try again.");
			} else {
				scan.nextLine();
				System.out.println("Invalid format");
			}

		}
		System.out.println("Welcome!");

		// Main prompt
		while (true) {
			System.out.println("");
			System.out.println("Pick a number to choose an action:");
			System.out.println("  1. Check balance");
			System.out.println("  2. Withdraw");
			System.out.println("  3. Deposit");
			System.out.println("  4. Quit");

			// Get choice
			int input = 0;
			while (scan.hasNextLine()) {
				String inputStr = scan.nextLine();
				try {
					input = Integer.parseInt(inputStr);
					if (input < 1 || input > 4) {
						System.out.println("Invalid input");
						continue;
					}
					break;
				} catch (NumberFormatException ex) {
					System.out.println("Invalid input");
				} finally {
				}
			}

			// Check balance
			if (input == 1) {
				System.out.println(atm.checkBalance(curUser));
				continue;
			}

			// Withdraw
			if (input == 2) {
				System.out.println("How much do you want to withdraw?");
				int amount = 0;
				while (scan.hasNextLine()) {
					String inputStr = scan.nextLine();
					try {
						amount = Integer.parseInt(inputStr);
						break;
					} catch (NumberFormatException ex) {
						System.out.println("Invalid input");
					} finally {
					}
				}
				System.out.println(atm.withdrawFunds(curUser, amount));
			}

			// Deposit
			if (input == 3) {
				System.out.println("How much do you want to deposit?");
				int amount = 0;
				while (scan.hasNextLine()) {
					String inputStr = scan.nextLine();
					try {
						amount = Integer.parseInt(inputStr);
						break;
					} catch (NumberFormatException ex) {
						System.out.println("Invalid input");
					} finally {
					}
				}
				System.out.println(atm.depositFunds(curUser, amount));
			}

			// Quit
			if (input == 4) {
				break;
			}
		}

		// Tear down
		System.out.println("Thank you!");
		scan.close();
	}

}
