package library;

import java.io.Serializable;

public class SignUpResult implements Serializable {
	private static final long serialVersionUID = 2L;
	public static final int NOT_EXIST = 0;
	public static final int ID_EXIST = 1;
	private int result;
	
	public SignUpResult(int result) {
		super();
		this.result = result;
	}
	public synchronized int getResult() {
		return result;
	}
	public synchronized void setResult(int result) {
		this.result = result;
	}
	public static synchronized int getNotExist() {
		return NOT_EXIST;
	}
	public static synchronized int getIdExist() {
		return ID_EXIST;
	}
	@Override
	public String toString() {
		return "LoginResult [result=" + result + "]";
	}
}