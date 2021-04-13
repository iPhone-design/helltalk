package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import library.ChatMap;
import library.Room;
import server.ChatServer;
import server.RoomListDAO;
import server.Server;

public class CreateRoomFrame extends JFrame {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public CreateRoomFrame(Socket socket) {
		this.socket = socket;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setSize(300, 500);
		setLayout(null);
		JTextField textfield = new JTextField(10);
		textfield.setBounds(0, 0 , 100, 50);
		add(textfield);
		JButton btn = new JButton("결정");
		btn.setBounds(100, 100, 100, 50);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					oos.writeObject(new Room(textfield.getText(), "master", 4));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		add(btn);
		setVisible(true);
	}
}
