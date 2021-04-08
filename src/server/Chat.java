package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat extends Thread {
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public Chat(Socket client) {
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
		try {
			String id = dis.readUTF();
			////////////////////////////////////////////////////////////////////로그인 닉네임 으로 바꿔야됨 id가 어케 하지??
			ChatMap.addUser(id , dos);
			String read = null;
			
			while ((read = dis.readUTF())!= null) {
				if (read.equals("/종료")) {
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
				System.out.println("사용자 접속 종료");
				ChatMap.removeSocket("b");
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
