package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import library.LoginResult;
import library.User;
import library.UserRequest;

// TODO 
// GUI 에서 입력한 로그인, 회원가입 객체 -> 서버로 전송
public class SocketClient {
//	private final String SERVER_ADDRESS = "192.168.100.33";
	private final static String SERVER_ADDRESS = "localhost";
	private final static int PORT = 2222;
	
	static LoginResult result = null;
	static UserRequest request = null; 
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public SocketClient() throws IOException {
		socket = new Socket(SERVER_ADDRESS, PORT);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}

	public LoginResult login(User user) {
		try {
			oos.writeObject(new UserRequest(user, 1));	// 서버에 객체 전달
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
		try  {
			oos.writeObject(user);
			oos.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SocketClient client = new SocketClient();
//		LoginResult result = client.login(new User("moderator1", "moderator1"));
//		client.add(new User("8", "8", "8", 8));
		Thread.sleep(3000); //3초 뒤에 닫힘 
	}
}