package library;

import java.io.Serializable;

public class UserRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;
	private int choose;
	
	public UserRequest(User user, int choose) {
		super();
		this.user = user;
		this.choose = choose;
	}

	public int getChoose() {
		return choose;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}