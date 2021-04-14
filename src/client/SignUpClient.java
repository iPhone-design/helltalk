package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.LoginPanel;
import library.LoginResult;
import library.User;
import library.UserRequest;

public class SignUpClient {
	static User user = new User();
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
	public SignUpClient(Socket socket) {
		this.socket = socket;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 로그인 요청
	public LoginResult login(User user) {
		LoginResult result = null;
		try {
			oos.writeObject(new UserRequest(user, 0));
			oos.flush();
			result = (LoginResult) ois.readObject();
			Thread.sleep(1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원가입 요청
	public LoginResult add(User user) {
		LoginResult result = null;
		try  {
			oos.writeObject(new UserRequest(user, 1));
			oos.flush();
			result = (LoginResult) ois.readObject();
			Thread.sleep(1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// User 정보 불러오기
	public User getUserData() {
		User user = new User();
		try {
			oos.writeObject(new UserRequest(user, 2));
			oos.flush();
			user = (User) ois.readObject();
			System.out.println("클라 : " + user.toString());
			Thread.sleep(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// User 정보 업데이트
	public void updateUserData(User user) {
		LoginResult result = null;
		try {
			oos.writeObject(new UserRequest(user, 3));
			oos.flush();
			result = (LoginResult) ois.readObject();
			Thread.sleep(1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}






















