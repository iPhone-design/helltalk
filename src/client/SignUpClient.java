package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import library.LoginResult;
import library.SignUpResult;
import library.User;
// TODO 
// GUI 에서 입력한 로그인, 회원가입 객체 -> 서버로 전송
public class SignUpClient {
//	private final String SERVER_ADDRESS = "192.168.100.33";
	private final static String SERVER_ADDRESS = "localhost";
	private final static int PORT = 2222;
	
	static SignUpResult result = null;
	static User user = new User();
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public SignUpClient() throws IOException {
		socket = new Socket(SERVER_ADDRESS, PORT);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}

	public SignUpResult login(User user) {
		try {
			oos.writeObject(user);	// 서버에 객체 전달
			oos.flush();
			
			result = (SignUpResult) ois.readObject();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public SignUpResult add(User user) {
		SignUpResult result = null;
		try  {
			oos.writeObject(user);
			oos.flush();
			result = (SignUpResult) ois.readObject();
			Thread.sleep(3000); //3초 뒤에 닫힘 
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
	
//	public static void main(String[] args) throws IOException, InterruptedException {
//		SocketClient client = new SocketClient();
//		client.add(new User("나나", "나나", "뽀", 50));
//		System.out.println(result);
//	}
}






















