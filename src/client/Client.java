package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.BufferedChatPanel;
import gui.CreateRoomFrame;
import gui.MainFrame;
import gui.RoomListPanel;
import gui.TestFrame;
import library.Room;
import server.RoomListDAO;

public class Client {
	private static final String ADDRESS = "192.168.100.33";
	private static final int PORT = 2222;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", PORT)){
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			MainFrame main = new MainFrame();
			
//			ChatClient chat = new ChatClient(socket);
			
//			CreateRoomFrame test = new CreateRoomFrame(socket);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
