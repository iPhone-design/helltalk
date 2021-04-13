package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import library.ChatMap;

public class ChatServer implements Runnable {
	private Socket client;
	private String roomName;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ChatServer(Socket client) {
		System.out.println("사용자 접속 성공");
		this.client = client;
//		this.roomName = roomName;
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
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	
	
}
