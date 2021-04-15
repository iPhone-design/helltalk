package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import gui.BufferedChatPanel;
import library.ChatMap;
import library.ObjectInOut;

public class Controller implements Runnable {
	private Socket socket;
	private String title;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInOut object;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public Controller(Socket socket) {
		System.out.println("사용자 접속 성공");
		this.socket = socket;
		this.title = title;
		ChatMap.createRoom("firstRoom");
		ChatMap.createRoom("secondRoom");
		ChatMap.createRoom("thirdRoom");
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					String id = null;
					try {
						object = (ObjectInOut) ois.readObject();
						System.out.print("처음부분 : ");
						if (object.getProtocol() == ObjectInOut.CHAT) {
							try {
								id = "마스터";
								ChatMap.enterUser(object.getTitle(), object.getNickName(), dos);
								ChatMap.messageToAll(object.getTitle(), object.getNickName() + " 님이 입장하셨습니다.");
								String read = null;
								while ((read = dis.readUTF())!= null) {
									System.out.println(read);
									if (read.equals("/종료")) {
										ChatMap.messageToAll(object.getTitle(), object.getNickName() + " 님이 퇴장하셨습니다.");
										ChatMap.removeUser(object.getTitle(), object.getNickName());
										System.out.println("방지움!!!");
										break;
									} else if (read.startsWith("/w ")) {
										ChatMap.sendMessageToOne(object.getTitle(), object.getNickName(), read);
									} else {
										ChatMap.messageToAll(object.getTitle(), object.getNickName() + " : " + read);
									}
								}
								System.out.print("끝 부분 : ");
							} catch (IOException e) {
								e.printStackTrace();
							} 
						} else if (object.getProtocol() == ObjectInOut.REGISTRATION) {
							
						} else if (object.getProtocol() == ObjectInOut.LOGIN) {
							ServerSignUp signUp = new ServerSignUp(socket);
						} else {
							System.out.println("아무것도 안댐 !!");
						}
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	
	
}
