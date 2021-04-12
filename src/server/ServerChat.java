package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import gui.RoomPanel;
import library.ChatMap;

public class ServerChat {
	private final static int PORT = 2222;
	private static Socket socket;
	public static List<RoomPanel> roomList;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	public static void main(String[] args) {
		roomList = new ArrayList<>();
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			ChatMap.createRoom("aa");
			while (true) {
				socket = server.accept();
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				
//				if (dis.readUTF().equals("aa")) {
					Thread t1 = new Thread(new ServerRunnable());
					t1.start();
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DataOutputStream getDos() {
		return dos;
	}

	public static synchronized void setDos(DataOutputStream dos) {
		ServerChat.dos = dos;
	}

	public static synchronized DataInputStream getDis() {
		return dis;
	}

	public static synchronized void setDis(DataInputStream dis) {
		ServerChat.dis = dis;
	}

	public static Socket getSocket() {
		return socket;
	}
	
	private static class ServerRunnable implements Runnable {
		@Override
		public void run() {
			String id = null;
			try {
				////////////////////////////////////////////////////////////////////로그인 닉네임 으로 바꿔야됨
				id = dis.readUTF();
				////////////////////////////////////////////////////////////////////방 텍스트 긁어와서 넣어야함!!!
				if (ChatMap.selectRoom("aa")) {
					ChatMap.createRoom("aa");
				}
				ChatMap.enterUser("aa", id, dos);
				ChatMap.messageToAll("aa", id + " 님이 입장하셨습니다.");
				String read = null;
				
				while ((read = dis.readUTF())!= null) {
					if (read.equals("/종료")) {
						// 퇴장 시 메시지 출력이 안됨 ㅜㅜ
						ChatMap.messageToAll("aa", id + " 님이 퇴장하셨습니다.");
						break;
					} else if (read.startsWith("/w ")) {
						ChatMap.sendMessageToOne("aa", id, read);
					} else {
						ChatMap.messageToAll("aa", id + " : " + read);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


