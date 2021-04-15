package library;

import java.io.Serializable;

public class ObjectInOut implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int CHAT = 100;
	public static final int EXIT = 101;
	public static final int LOGIN = 0;
	public static final int REGISTRATION = 1;
	
	
	private int protocol;
	private String id;
	private String pw;
	private String title;
	private String nickName;
	
	public ObjectInOut() {
		super();
	}
	
	public ObjectInOut(int protocol) {
		super();
		this.protocol = protocol;
	}

	public ObjectInOut(int protocol, String title, String nickName) {
		super();
		this.protocol = protocol;
		this.title = title;
		this.nickName = nickName;
	}

	public int getProtocol() {
		return protocol;
	}

	public String getTitle() {
		return title;
	}

	public String getNickName() {
		return nickName;
	}
}
