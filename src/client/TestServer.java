package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import library.User;
import server.UserDAO;

// 테스트용 서버
public class TestServer {
	private static final int PORT = 2222;
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try (Socket client = server.accept()) {
					ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
					
					User user;
					user = (User) ois.readObject();
					
					dao.addUser(user.getId(), user.getPassword(), user.getNickname(), user.getAge());
					
					oos.writeObject(user);
					oos.flush();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}