package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import library.ChatMap;
import library.Room;
import server.ChatServer;
import server.RoomListDAO;
import server.Server;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

 
public class RoomListPanel extends JPanel {
	private JTextField textField;
	private JPanel panelBackground;
	private RoomListDAO roomlistDAO;
	private Socket socket;
	int panelY = 5;
	public RoomListPanel() {
		this.socket = socket;
//		BufferedChatPanel buffer = new BufferedChatPanel();
		panelBackground = new JPanel();
	 	setPreferredSize(new Dimension(370, 580));
	    setMaximumSize(new Dimension(370, 580));
	    setBackground(Color.white);
	    setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    setLayout(null);
	     
	    panelBackground.setBounds(12, 51, 346, 437);
	    panelBackground.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    panelBackground.setLayout(null);	 	
	     
	    textField = new JTextField();
	    textField.setBounds(12, 10, 265, 31);
	    add(textField);
	    textField.setColumns(10);
	    JButton btnNewButton = new JButton("검색");
	    btnNewButton.setBounds(279, 10, 79, 31);
	    add(btnNewButton);
	    JButton btnNewButton_1 = new JButton("방 개설");
	    btnNewButton_1.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		CreateRoomFrame create = new CreateRoomFrame(socket);
	     	}
	    });
	    
	    Thread refresh = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					panelBackground.removeAll();
					RoomListDAO roomlist = new RoomListDAO();
					List<Room> roomList = roomlist.RoomlistAll();
					for (int i = 0; i <= roomList.size() -1; i++) {
						RoomPanel panelRoom = new RoomPanel(roomList.get(i).getTitle(), roomList.get(i).getRoomMasterName());
						panelRoom.setBounds(8, 5 + (i * 85), 330, 80);
						panelBackground.add(panelRoom);
					}
					revalidate();
					repaint();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
    	refresh.start();
	    
	    btnNewButton_1.setBounds(54, 498, 97, 23);
	    add(btnNewButton_1);
	    
	    JButton btnNewButton_2 = new JButton("로그아웃");
	    btnNewButton_2.setBounds(206, 498, 97, 23);
	    add(btnNewButton_2);
	    add(panelBackground);
	 }
}