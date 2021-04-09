package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.CreateRoomFrame;
import library.ChatMap;

public class ServerChat {
	private final static int PORT = 2222;
	private static Socket socket;
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			while (true) {
				socket = server.accept();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Socket getSocket() {
		return socket;
	}
}
