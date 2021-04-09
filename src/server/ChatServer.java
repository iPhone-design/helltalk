package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import library.ChatMap;

public class ChatServer extends Thread {
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ChatServer(Socket client) {
		System.out.println("사용자 접속 성공");
		this.client = client;
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
			id = dis.readUTF();
			////////////////////////////////////////////////////////////////////로그인 닉네임 으로 바꿔야됨 id가 어케 하지??
			ChatMap.addUser(id , dos);
			ChatMap.messageToAll(id + " 님이 입장하셨습니다.");
			String read = null;
			
			while ((read = dis.readUTF())!= null) {
				if (read.equals("/종료")) {
//					ChatMap.messageToAll(" 님이 퇴장하셨습니다.");                     나중에 구현 합시다 시부레!
					break;
				} else if (read.startsWith("/w ")) {
					ChatMap.sendMessageToOne(read);
				} else {
					ChatMap.messageToAll(read);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ChatMap.removeSocket(id);
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
