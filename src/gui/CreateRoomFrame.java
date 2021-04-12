package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import library.ChatMap;
import server.ChatServer;
import server.RoomListDAO;
import server.ServerChat;

public class CreateRoomFrame extends JFrame {
	private RoomListDAO roomlistDAO;
	
	public CreateRoomFrame(BufferedChatPanel buffer, RoomListPanel roomlistPanel) {
		roomlistDAO = new RoomListDAO();
		setSize(300, 500);
		setLayout(null);
		JTextField textfield = new JTextField(10);
		textfield.setBounds(0, 0 , 100, 50);
		add(textfield);
		JButton btn = new JButton("결정");
		btn.setBounds(100, 100, 100, 50);
//		btn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try (Socket socket = new Socket("localhost", 2222);) {
//					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//					DataInputStream dis = new DataInputStream(socket.getInputStream());
//					dos.writeUTF("aa");
//					roomlistDAO.addRoom(textfield.getText(),"master");
//					setVisible(false);
//					dos.flush();
//				} catch (UnknownHostException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
		add(btn);
		setVisible(true);
	}
}
