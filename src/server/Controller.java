package server;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	private File file;
	
	public Controller(Socket socket) {
		userDAO = new UserDAO();
		roomListDAO = new RoomListDAO();
		file = new File(".\\default_img\\img.png");
		this.socket = socket;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Room> roomlist = roomListDAO.RoomlistAll();
		for (int i = 0; i <= roomlist.size() - 1; i++) {
			ChatMap.createRoom(roomlist.get(i).getTitle());
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
								userDAO.insertImage(object.getId(), "img.png", file);
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
						} else if (object.getProtocol() == ObjectInOut.USERLEAVE) {
							int result = userDAO.userDelete(object.getId());
							object = new ObjectInOut(ObjectInOut.USERLEAVE, result);
							oos.writeObject(object);
							oos.flush();
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
							int result = roomListDAO.createRoom(object.getTitle(), object.getNickName(), object.getHeadCount());
							ChatMap.createRoom(object.getTitle());
							object = new ObjectInOut(ObjectInOut.CREATEROOM, result);
							oos.writeObject(object);
							oos.flush();
						} else if (object.getProtocol() == ObjectInOut.REFRESHROOM) {
							List<Room> roomlist = roomListDAO.RoomlistAll();
							object = new ObjectInOut(ObjectInOut.REFRESHROOM, roomlist);
							oos.writeObject(object);
							oos.flush();
						} else if (object.getProtocol() == ObjectInOut.IMAGELOAD) {
							String fileName = userDAO.extractImage(object.getId());
							object = new ObjectInOut(ObjectInOut.IMAGELOAD, fileName, 0);
							oos.writeObject(object);
							oos.flush();
						} else if (object.getProtocol() == ObjectInOut.IMAGECHANGE) {
							File tempFile = new File(".\\img\\" + object.getId() + "_" + object.getFileName());
							ByteArrayInputStream bais = new ByteArrayInputStream(object.getFileArray());
							String extension = object.getFileName().substring(object.getFileName().indexOf('.') + 1);
							BufferedImage bfImg = ImageIO.read(bais);
				          	ImageIO.write(bfImg , extension, tempFile);
				          	userDAO.updateImage(object.getId(), object.getFileName(), tempFile);
						}
					} catch (ClassNotFoundException e) {
						break;
					} catch (IOException e) {
						break;
					}
				}
			}
		});
		reading.start();
	}
}
