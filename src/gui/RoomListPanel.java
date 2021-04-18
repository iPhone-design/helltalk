package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private JTextField jTextField;
	private JPanel panelBackground;
	private Socket socket;
	private int panelY = 5;
	private RoomPanel panelRoom;
	private JButton exitRoomButton;
	private JButton logoutButton;
	private RoomPanel firstRoom;
	private RoomPanel secondRoom;
	private RoomPanel thirdRoom;
	private JLabel accountIdText;
	private JButton myPageButton;
	private JButton createRoomButton;
	private JLabel accountNicNameText;
	
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
	     
	    jTextField = new JTextField();
	    jTextField.setBounds(12, 10, 265, 31);
	    add(jTextField);
	    jTextField.setColumns(10);
	    
	    JButton btnNewButton = new JButton("검색");
	    btnNewButton.setBounds(279, 10, 79, 31);
	    add(btnNewButton);
	    
	    createRoomButton = new JButton("방 개설");
	    createRoomButton.setBounds(54, 498, 120, 23);
	    add(createRoomButton);
	    
	    firstRoom = new RoomPanel("firstRoom", "방장1");
	    firstRoom.setBounds(8, 5, 330, 80);
	    panelBackground.add(firstRoom);
	    
	    secondRoom = new RoomPanel("SecondRoom", "방장2");
	    secondRoom.setBounds(8, 90, 330, 80);
	    panelBackground.add(secondRoom);
	    
	    thirdRoom = new RoomPanel("thirdRoom", "방장3");
	    thirdRoom.setBounds(8, 175, 330, 80);
	    panelBackground.add(thirdRoom);
	    
	    
	    logoutButton = new JButton("로그아웃");
	    logoutButton.setBounds(206, 498, 120, 23);
	    add(logoutButton);
	    
	    exitRoomButton = new JButton("나가기");
	    exitRoomButton.setBounds(206, 526, 120, 23);
	    exitRoomButton.setEnabled(false);
	    add(exitRoomButton);
	    
	    add(panelBackground);
	    
	    myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(206, 554, 120, 23);
		add(myPageButton);
	    
	    accountIdText = new JLabel();
	    accountIdText.setBounds(54, 526, 97, 40);
	    accountIdText.setForeground(Color.white);
	    add(accountIdText);
	    
	    accountNicNameText = new JLabel();
	    accountNicNameText.setBounds(54, 526, 97, 40);
	    accountNicNameText.setForeground(Color.white);
	    add(accountNicNameText);
	}
	
	public JLabel getAccountNicNameText() {
		return accountNicNameText;
	}

	public JButton getCreateRoomButton() {
		return createRoomButton;
	}

	public JButton getMyPageButton() {
		return myPageButton;
	}

	public JLabel getAccountIdText() {
		return accountIdText;
	}

	public void setAccountIdText(JLabel accountIdText) {
		this.accountIdText = accountIdText;
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