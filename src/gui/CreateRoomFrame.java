package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.ChatClient;
import library.ChatMap;
import library.Room;
import server.ChatServer;
import server.RoomListDAO;
import server.ServerChat;

public class CreateRoomFrame extends JFrame {
	private RoomListDAO roomlistDAO;
	private int j = 0;
	
	public CreateRoomFrame(BufferedChatPanel buffer, RoomListPanel roomlistPanel) {
		roomlistDAO = new RoomListDAO();
		setSize(300, 500);
		setLayout(null);
		JTextField textfield = new JTextField(10);
		textfield.setBounds(0, 0 , 100, 30);
		add(textfield);
		JButton btn = new JButton("결정");
		btn.setBounds(100, 100, 30, 30);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ChatMap.createRoom("test");
				roomlistDAO.addRoom(textfield.getText(),"master");
//				List<Room> roomList = roomlistDAO.RoomlistAll();
//				for (int i = 0; i <= roomList.size() -1; i++) {
//					RoomPanel panel = new RoomPanel(roomList.get(i).getTitle(), roomList.get(i).getRoomMasterName());
//					panel.setBounds(8, 5 + (i * 80) , 330, 80);
//					roomlistPanel.getPanel().add(panel);
//				}
//				roomlistPanel.revalidate();
//				roomlistPanel.repaint();
//		    	
//		    	Thread clientChatThread = new Thread(new Runnable() {
//					@Override
//					public void run() {
//						ChatServer chatserver = new ChatServer(ServerChat.getSocket(), textfield.getText());
//					}
//				});
		    	
		    	setVisible(false);
//		    	for (int i = 0; i < roomList.size(); i++) {
//		    		j = i;
//			    	roomList.get(i).getJbtn().addActionListener(new ActionListener() {
//			    		int j = CreateRoomFrame.this.j;
//			    		@Override
//						public void actionPerformed(ActionEvent e) {
//			    			buffer.getChatPanel().getTitleLbl().setText(roomList.get(j).getTitleLbl().getText());
//			    			buffer.getChatPanel().setVisible(true);
//			    			clientChatThread.start();
//			    			ChatClient client = new ChatClient();
//						}
//					});
//			    }
			}
		});
		add(btn);
		setVisible(true);
	}
}
