package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final static int PORT = 2222;
	private static Socket socket;
	
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			while (true) {
				socket = server.accept();
				Thread controller = new Thread(new Controller(socket));
				controller.start();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}


