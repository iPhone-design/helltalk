package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import library.ChatMap;

public class ChatServer implements Runnable {
	private Socket client;
	private String title;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ChatServer(Socket client, String title) {
		System.out.println("사용자 접속 성공");
		this.client = client;
		this.title = title;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String id = null;
		try {
			id = "테스트";
			if (ChatMap.selectRoom(title)) {
				ChatMap.createRoom(title);
			}
			ChatMap.enterUser(title, id, dos);
			ChatMap.messageToAll(title, id + " 님이 입장하셨습니다.");
			String read = null;
			
			while ((read = dis.readUTF())!= null) {
				if (read.equals("/종료")) {
					// 퇴장 시 메시지 출력이 안됨 ㅜㅜ
					ChatMap.messageToAll(title, id + " 님이 퇴장하셨습니다.");
					break;
				} else if (read.startsWith("/w ")) {
					ChatMap.sendMessageToOne(title, id, read);
				} else {
					ChatMap.messageToAll(title, id + " : " + read);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				client.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	
	
}
