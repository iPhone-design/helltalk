package library;

import java.io.Serializable;
import java.util.List;

public class ObjectInOut implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int LOGIN = 0;
	public static final int REGISTRATION = 1;
	public static final int CHAT = 100;
	public static final int EXIT = 101;
	public static final int MYPAGE = 200;
	public static final int INFOCHANGE = 201;
	public static final int CREATEROOM = 300;
	public static final int REMOVEROOM = 301;
	public static final int REFRESHROOM = 400;
	
	private int protocol;
	private String id;
	private String pw;
	private String nickName;
	private int age;
	private String title;
	private int result;
	private int headCount;
	private List<Room> roomlist;
	
	public ObjectInOut() {
		super();
	}
	
	public ObjectInOut(int protocol, String id) {
		super();
		this.protocol = protocol;
		this.id = id;
	}
	
	public ObjectInOut(int protocol, List<Room> roomlist) {
		super();
		this.protocol = protocol;
		this.roomlist = roomlist;
	}

	public ObjectInOut(int protocol, String id, String pw, String nickName, int age) {
		super();
		this.protocol = protocol;
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.age = age;
	}
	
	public ObjectInOut(int protocol, int result) {
		super();
		this.protocol = protocol;
		this.result = result;
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
	
	public ObjectInOut(int protocol, String id, String pw, int result) {
		super();
		this.protocol = protocol;
		this.id = id;
		this.pw = pw;
		this.result = result;
	}
	
	public ObjectInOut(int protocol, String title, String nickName, int headCount, int result) {
		super();
		this.protocol = protocol;
		this.title = title;
		this.nickName = nickName;
		this.headCount = headCount;
		this.result = result;
	}
	
	public ObjectInOut(int protocol, String id, String pw, int result, String nicName) {
		super();
		this.protocol = protocol;
		this.id = id;
		this.pw = pw;
		this.result = result;
		this.nickName = nicName;
	}
	
	public int getHeadCount() {
		return headCount;
	}

	public int getResult() {
		return result;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Room> getRoomlist() {
		return roomlist;
	}
}
