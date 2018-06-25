import java.util.Calendar;
import java.util.Date;

public class User {
	public final static float dailyWithdrawalLimit = 1000;
	
	private String pin;
	private int balance;
	private Date limitDay;
	private int limitProgress;
	
	public User() {
		limitDay = this.getTodayMidnight();
		limitProgress = 0;
	}
	
	public User(String pin) {
		this.pin = pin;		
		limitDay = this.getTodayMidnight();
		limitProgress = 0;
	}
	
	public User(String pin, int startingAmt) {
		this.pin = pin;
		this.balance = startingAmt;
		limitDay = this.getTodayMidnight();
		limitProgress = 0;
	}
	
	protected void deposit(int amount) {
		this.balance += amount;
		return;
	}
	
	protected boolean withdraw(int amount) {
		Date today = this.getTodayMidnight();
		if(limitDay.before(today)) {
			limitDay = this.getTodayMidnight();
			limitProgress = 0;
		}
		
		if(limitProgress + amount > User.dailyWithdrawalLimit) {
			return false;
		}
		else {
			this.balance -= amount;
			this.limitProgress += amount;
		}
		
		return true;
	}
	
	protected int getBalance() {
		return this.balance;
	}
	
	protected String getPin() {
		return this.pin;
	}
	
	private Date getTodayMidnight() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today.getTime();
	}
	
}
