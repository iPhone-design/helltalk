package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import gui.CreateRoomFrame;
import gui.RoomPanel;
import library.ChatMap;

public class ServerChat {
	private final static int PORT = 2222;
	private static Socket socket;
	public static List<RoomPanel> roomList;
	
	public static void main(String[] args) {
		roomList = new ArrayList<>();
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			ChatMap.createRoom("test");
			while (true) {
				socket = server.accept();
				ChatServer chat = new ChatServer(socket, "test");
				chat.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Socket getSocket() {
		return socket;
	}
}
