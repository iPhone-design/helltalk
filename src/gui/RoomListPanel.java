package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import client.ChatClient;
import library.ChatMap;
import library.ObjectInOut;
import library.Room;
import server.Controller;
import server.RoomListDAO;
import server.Server;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

 
public class RoomListPanel extends JPanel {
	private JTextField textField;
	private JPanel panelBackground;
	private RoomListDAO roomlistDAO;
	private Socket socket;
	private int panelY = 5;
	private RoomPanel panelRoom;
	private JButton exitRoomButton;
	private JButton logoutButton;
	private BufferedChatPanel bufferedChatPanel;
	private RoomPanel firstRoom;
	private RoomPanel secondRoom;
	private RoomPanel thirdRoom;
	private JLabel nickNameLbl;
	
	public RoomListPanel() {
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
	    JButton createRoomButton = new JButton("방 개설");
	    createRoomButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		CreateRoomFrame create = new CreateRoomFrame(socket);
	     	}
	    });
	    
	    
	    firstRoom = new RoomPanel("firstRoom", "방장1");
	    firstRoom.setBounds(8, 5, 330, 80);
	    panelBackground.add(firstRoom);
	    
	    secondRoom = new RoomPanel("SecondRoom", "방장2");
	    secondRoom.setBounds(8, 90, 330, 80);
	    panelBackground.add(secondRoom);
	    
	    thirdRoom = new RoomPanel("thirdRoom", "방장3");
	    thirdRoom.setBounds(8, 175, 330, 80);
	    panelBackground.add(thirdRoom);
	    
	    createRoomButton.setBounds(54, 498, 97, 23);
	    add(createRoomButton);
	    
	    logoutButton = new JButton("로그아웃");
	    logoutButton.setBounds(206, 498, 97, 23);
	    add(logoutButton);
	    add(panelBackground);
	    
	    exitRoomButton = new JButton("나가기");
	    exitRoomButton.setBounds(206, 526, 97, 23);
	    add(exitRoomButton);
	    add(panelBackground);
	    
	    nickNameLbl = new JLabel();
	    nickNameLbl.setBounds(173, 526, 97, 23);
	    add(nickNameLbl);
	}
	
	
	public JLabel getNickNameLbl() {
		return nickNameLbl;
	}

	public void setNickNameLbl(JLabel nickNameLbl) {
		this.nickNameLbl = nickNameLbl;
	}

	public RoomPanel getPanelRoom() {
		return panelRoom;
	}
	
	public JButton getExitRoomButton() {
		return exitRoomButton;
	}
	
	public JButton getLogoutButton() {
		return logoutButton;
	}

	public RoomPanel getFirstRoom() {
		return firstRoom;
	}

	public void setFirstRoom(RoomPanel firstRoom) {
		this.firstRoom = firstRoom;
	}

	public RoomPanel getSecondRoom() {
		return secondRoom;
	}

	public void setSecondRoom(RoomPanel secondRoom) {
		this.secondRoom = secondRoom;
	}

	public RoomPanel getThirdRoom() {
		return thirdRoom;
	}

	public void setThirdRoom(RoomPanel thirdRoom) {
		this.thirdRoom = thirdRoom;
	}
}