package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import gui.RoomPanel;
import library.ChatMap;
import library.Room;

public class Server {
	private final static int PORT = 2222;
	private static Socket socket;
	public static List<RoomPanel> roomList;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static RoomListDAO dao;
	
	public static void main(String[] args) {
		roomList = new ArrayList<>();
		dao = new RoomListDAO();
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			ChatMap.createRoom("aa");
			while (true) {
				socket = server.accept();
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				
				if (dis.readUTF().equals("a")) {
					Thread t1 = new Thread(new ChatServer(socket));
					t1.start();
				} 
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


