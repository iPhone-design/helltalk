package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import library.ChatMap;

public class ServerChat {
	private final static int PORT = 2222;
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			while (true) {
				Socket socket = server.accept();
				
				ChatServer userEnterace = new ChatServer(socket, "test");
				userEnterace.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
