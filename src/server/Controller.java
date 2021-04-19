package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import gui.BufferedChatPanel;
import library.ChatMap;
import library.ObjectInOut;
import library.Room;
import library.User;

public class Controller implements Runnable {
	private Socket socket;
	private String title;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInOut object;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private UserDAO userDAO;
	private RoomListDAO roomListDAO;
	
	public Controller(Socket socket) {
		userDAO = new UserDAO();
		roomListDAO = new RoomListDAO();
		this.socket = socket;
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
		Thread reading = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						object = (ObjectInOut) ois.readObject();
						if (object.getProtocol() == ObjectInOut.CHAT) {
							try {
								ChatMap.enterUser(object.getTitle(), object.getNickName(), dos);
								ChatMap.messageToAll(object.getTitle(), object.getNickName() + " 님이 입장하셨습니다.");
								String read = null;
								while ((read = dis.readUTF())!= null) {
									if (read.equals("/종료")) {
										ChatMap.messageToAll(object.getTitle(), object.getNickName() + " 님이 퇴장하셨습니다.");
										ChatMap.removeUser(object.getTitle(), object.getNickName());
										dos.writeUTF("/종료");
										dos.flush();
										break;
									} else if (read.startsWith("/w ")) {
										ChatMap.sendMessageToOne(object.getTitle(), object.getNickName(), read);
									} else {
										ChatMap.messageToAll(object.getTitle(), object.getNickName() + " : " + read);
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							} 
						} else if (object.getProtocol() == ObjectInOut.REGISTRATION) {
							if ((userDAO.idCheck(object.getId())) == 0) {
								userDAO.addUser(object.getId(), object.getPw(), object.getNickName(), object.getAge());
								object = new ObjectInOut(ObjectInOut.REGISTRATION, 0);
								oos.writeObject(object);
								oos.flush();
							} else if ((userDAO.idCheck(object.getId())) == 1) {
								object = new ObjectInOut(ObjectInOut.REGISTRATION, 1);
								oos.writeObject(object);
								oos.flush();
							}
						} else if (object.getProtocol() == ObjectInOut.LOGIN) {
							int result = userDAO.login(object.getId(), object.getPw());
							if (result == 0) {
								// 계정없음
								object = new ObjectInOut(ObjectInOut.LOGIN, 0);
								oos.writeObject(object);
								oos.flush();
							} else if (result == 1) {
								// 로그인 성공
								User user = userDAO.myProfile(object.getId());
								object = new ObjectInOut(ObjectInOut.LOGIN, user.getId(),"null", 1, user.getNickname());
								oos.writeObject(object);
								oos.flush();
							} else {
								// 비밀번호 틀림
								object = new ObjectInOut(ObjectInOut.LOGIN, 2);
								oos.writeObject(object);
								oos.flush();
							}
						} else if (object.getProtocol() == ObjectInOut.MYPAGE) {
							User user = userDAO.myProfile(object.getId());
							object = new ObjectInOut(ObjectInOut.MYPAGE, user.getId(), "null", user.getNickname(), 0);
							oos.writeObject(object);
							oos.flush();
						} else if (object.getProtocol() == ObjectInOut.INFOCHANGE) {
							int result = userDAO.updateUserData(object.getId(), object.getPw(), object.getNickName());
							object =  new ObjectInOut(ObjectInOut.INFOCHANGE, result);
							oos.writeObject(object);
							oos.flush();
						} else if (object.getProtocol() == ObjectInOut.CREATEROOM) {
							System.out.println(object.getTitle() + " " + object.getNickName() + " " + object.getHeadCount());
							int result = roomListDAO.createRoom(object.getTitle(), object.getNickName(), object.getHeadCount());
							object = new ObjectInOut(ObjectInOut.CREATEROOM, result);
							oos.writeObject(object);
							oos.flush();
						} else if (object.getProtocol() == ObjectInOut.REFRESHROOM) {
							List<Room> roomlist = roomListDAO.RoomlistAll();
							object = new ObjectInOut(ObjectInOut.REFRESHROOM, roomlist);
							oos.writeObject(object);
							oos.flush();
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		reading.start();
	}
}
