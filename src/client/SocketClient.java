package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import library.LoginResult;
import library.User;

// TODO 
// GUI 에서 입력한 로그인, 회원가입 객체 -> 서버로 전송
public class SocketClient {
//	private final String SERVER_ADDRESS = "192.168.100.33";
	private final String SERVER_ADDRESS = "localhost";
	private final int PORT = 2222;
	
	public LoginResult login(User user) {
		LoginResult result = null;
		try (Socket socket = new Socket(SERVER_ADDRESS,	PORT)) {
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			oos.writeObject(user);	// 서버에 객체 전달
			oos.flush();
			
			result = (LoginResult) ois.readObject();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void add(User user) {
		LoginResult result = null;
		try (Socket socket = new Socket(SERVER_ADDRESS,	PORT)) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(user);
			oos.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		LoginResult result = client.login(new User("보라돌이", "1111"));
		System.out.println(result);
	}
}