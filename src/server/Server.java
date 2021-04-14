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

import client.SignUpClient;
import gui.RoomPanel;
import library.ChatMap;
import library.ObjectInOut;
import library.Room;

public class Server {
	private final static int PORT = 2222;
	private static Socket socket;
	public static List<RoomPanel> roomList;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static RoomListDAO roomlistDAO;
	private static UserDAO userDAO;
	private static ObjectInOut object;
	
	public static void main(String[] args) {
		roomList = new ArrayList<>();
		roomlistDAO = new RoomListDAO();
		userDAO = new UserDAO();
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			while (true) {
				socket = server.accept();
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				
				Thread reading = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							object = (ObjectInOut) ois.readObject();
							
							if (object.getProtocol() == ObjectInOut.CHAT) {
								Thread t1 = new Thread(new ChatServer(socket, object.getTitle()));
								t1.start();
								DataOutputStream out = new DataOutputStream(socket.getOutputStream());
								out.writeUTF("채팅");
								out.flush();
							} else if (object.getProtocol() == ObjectInOut.REGISTRATION) {
								
							} else if (object.getProtocol() == ObjectInOut.LOGIN) {
								System.out.println("로그인 시작!!");
//								userDAO.idCheck(object.get)
								
								
								ServerSignUp signUp = new ServerSignUp(socket);
//								SignUpClient signUpClient = new SignUpClient(socket);
								DataOutputStream out = new DataOutputStream(socket.getOutputStream());
								out.writeUTF("성공");
								out.flush();
							}
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
					}
				});
				reading.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}


