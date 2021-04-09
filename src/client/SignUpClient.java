package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import library.LoginResult;
import library.User;
import library.UserRequest;

public class SignUpClient {
//	private final String SERVER_ADDRESS = "192.168.100.33";
	private final static String SERVER_ADDRESS = "localhost";
	private final static int PORT = 2222;
	
	static User user = new User();
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public SignUpClient() throws IOException {
		socket = new Socket(SERVER_ADDRESS, PORT);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
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
	
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}






















