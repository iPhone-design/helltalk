package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final static int PORT = 2222;
	private static UserEntrance userEnterace;
	
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)){
			while (true) {
				System.out.println("서버 오픈");
				Socket socket = server.accept();
				
				System.out.println("사용자 입장");
				userEnterace = new UserEntrance(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
