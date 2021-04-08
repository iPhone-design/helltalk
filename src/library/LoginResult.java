package library;
// TODO
// 
public class LoginResult {
	public static final long serialVersionUID = 1L;
	public static final int OK = 0;
	public static final int NOT_EXIST = 1;
	public static final int WRONG_PASSWORD = 2;
	public static final int NOT_ACTIVATED = 3;
	private int result;
	public LoginResult(int result) {
		super();
		this.result = result;
	}
	public synchronized int getResult() {
		return result;
	}
	public synchronized void setResult(int result) {
		this.result = result;
	}
	public static synchronized int getOk() {
		return OK;
	}
	public static synchronized int getNotExist() {
		return NOT_EXIST;
	}
	public static synchronized int getWrongPassword() {
		return WRONG_PASSWORD;
	}
	public static synchronized int getNotActivated() {
		return NOT_ACTIVATED;
	}
	@Override
	public String toString() {
		return "LoginResult [result=" + result + "]";
	}
}