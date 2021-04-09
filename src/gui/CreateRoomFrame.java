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

import library.ChatMap;

import server.ChatServer;
import server.ServerChat;

public class CreateRoomFrame extends JFrame {
	
	int j = 0;
	
	public CreateRoomFrame(BufferedChatPanel buffer, RoomListPanel roomPanel) {
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
				ChatMap.createRoom(textfield.getText());
				List<RoomPanel> roomList = roomPanel.getRoomList();
				RoomPanel room = new RoomPanel(textfield.getText(), "12");
				roomList.add(room);
	     		room.setBounds(8, roomPanel.getPanelY() + roomList.get(roomList.size() - 1).getY(), 330, 80);
	     		roomPanel.setPanelY(roomPanel.getPanelY() + 85);
	     		System.out.println(roomList.get(roomList.size() - 1).getY());
	     		roomPanel.getPanel().add(roomList.get(roomList.size() - 1));
		    	roomPanel.revalidate();
		    	roomPanel.repaint();
		    	
		    	setVisible(false);
		    	for (int i = 0; i < roomList.size(); i++) {
		    		j = i;
			    	roomList.get(i).getJbtn().addActionListener(new ActionListener() {
			    		int j = CreateRoomFrame.this.j;
			    		@Override
						public void actionPerformed(ActionEvent e) {
			    			buffer.getChatPanel().getTitleLbl().setText(roomList.get(j).getTitleLbl().getText());
			    			buffer.getChatPanel().setVisible(true);
			    			ChatServer userEnterace = new ChatServer(ServerChat.getSocket(), roomList.get(j).getTitleLbl().getText());
							userEnterace.start();
						}
					});
			    }
			}
		});
		add(btn);
		setVisible(true);
		
	}

}
