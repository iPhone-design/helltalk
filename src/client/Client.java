package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.BufferedChatPanel;
import gui.CreateRoomFrame;
import gui.RoomListPanel;
import library.Room;
import server.RoomListDAO;

public class Client {
	private static final String ADDRESS = "192.168.100.33";
	private static final int PORT = 2222;
	
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", PORT)){
			ChatClient chat = new ChatClient(socket);
			
//			CreateRoomFrame test = new CreateRoomFrame(socket);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
