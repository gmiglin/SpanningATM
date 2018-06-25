import java.util.Hashtable;

public class ATM {
	
	private Hashtable<String, User> UserList;
	
	public ATM() {
		UserList= new Hashtable<String, User>();
	}
	
	public String withdrawFunds(User user, int amount) {
		if (amount == 0) {
			return "Technically you can do this, but it's silly.";
		}
		if (amount <= 0) {
			return "Cannot withdraw a negative amount.";
		}

		int curBal = user.getBalance();
		if (curBal < amount) {
			return "Insufficient funds. " + this.formatAmount(curBal) + " remaining.";
		}
		if (user.withdraw(amount)) {
			return this.formatAmount(amount) + " withdrawn";
		}

		return "Error: Attempting to withdraw over daily withdrawal limit.";
	}

	public String depositFunds(User user, int amount) {
		if (amount == 0) {
			return "Technically you can do this, but it's silly.";
		}

		if (amount <= 0) {
			return "Cannot deposit a negative amount.";
		}

		user.deposit(amount);
		return this.formatAmount(amount) + " deposited";
	}

	public String checkBalance(User user) {
		return this.formatAmount(user.getBalance());
	}

	public boolean addUser(User user) {
		String pin = user.getPin();
		if (pin.length() == 0) {
			return false;
		}
		if (!UserList.containsKey(pin)) {
			UserList.put(pin, user);
			return true;
		}

		return false;
	}

	public User getUser(String pin) {
		if (UserList.get(pin) != null) {
			return UserList.get(pin);
		}
		return null;
	}

	private String formatAmount(int amount) {
		return "$" + amount + ".00";
	}

}
