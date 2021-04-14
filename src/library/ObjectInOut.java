package library;

import java.io.Serializable;

public class ObjectInOut implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int CHAT = 100;
	public static final int LOGIN = 0;
	public static final int REGISTRATION = 1;
	
	
	private int protocol;
	private String id;
	private String pw;
	private String title;
	
	public ObjectInOut(int protocol) {
		super();
		this.protocol = protocol;
	}

	public ObjectInOut(int protocol, String id, String pw) {
		super();
		this.protocol = protocol;
		this.id = id;
		this.pw = pw;
	}

	public ObjectInOut(int protocol, String title) {
		super();
		this.protocol = protocol;
		this.title = title;
	}
	
	public int getProtocol() {
		return protocol;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "ObjectInOut [protocol=" + protocol + ", title=" + title + "]";
	}
}
