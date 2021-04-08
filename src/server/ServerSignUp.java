package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import library.LoginResult;
import library.User;

public class ServerSignUp {
	private static final int PORT = 2222;

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("클라이언트의 접속을 기다립니다.");
		while (true) {
		try (Socket client = server.accept()) {
			System.out.println("클라이언트 접속");
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			User user = (User) ois.readObject();
			LoginResult result = null;
//						int countLogin = dao.login(user.getId(), user.getPassword());
//						
//						if (countLogin == 0) {
//							result = new LoginResult(LoginResult.NOT_EXIST);
//						} else if (countLogin == 2) {
//							result = new LoginResult(LoginResult.WRONG_PASSWORD);
//						} else if (countLogin == 1) {
//							result = new LoginResult(LoginResult.OK);
//						}
//						oos.flush();

//						oos.reset();
			int idCheck = dao.login(user.getId(), user.getPassword());

			System.out.println(idCheck);
			if (idCheck == 0) { // 없는 아이디라 가입가능할때
				result = new LoginResult(LoginResult.JOIN);
				dao.addUser(user.getId(), user.getPassword(), user.getNickname(), user.getAge());
			} else if (idCheck == 1) { // 이미 존재하는 아이디라 가입 안될때
				result = new LoginResult(LoginResult.NOT_EXIST);
			}
			System.out.println(result);
			oos.writeObject(result);
			oos.flush();
		}
			catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}