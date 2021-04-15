package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import client.ChatClient;
import library.ChatMap;
import library.ObjectInOut;
import server.Controller;
import server.RoomListDAO;
import server.Server;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class RoomPanel extends JPanel {
	private JLabel titleLbl;
	private JLabel amountLbl;
	private JLabel nameLbl;
	private JButton enterRoomButton;
	
	public RoomPanel(String roomTitle, String name) {
		setPreferredSize(new Dimension(330, 80));
		setMaximumSize(new Dimension(330, 80));
		setBackground(Color.WHITE);
		setLayout(null);
		
		nameLbl = new JLabel();
		nameLbl.setText(name);
		nameLbl.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		nameLbl.setBounds(213, 5, 69, 24);
		add(nameLbl);
		
		titleLbl = new JLabel();
		titleLbl.setText(roomTitle);
		titleLbl.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		titleLbl.setBounds(20, 5, 69, 24);
		add(titleLbl);
		
		amountLbl = new JLabel("2/10");
		amountLbl.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		amountLbl.setBounds(12, 33, 69, 24);
		add(amountLbl);
		
		enterRoomButton = new JButton("입장");
		enterRoomButton.setBounds(249, 46, 69, 24);
		add(enterRoomButton);
	}

	public JButton getEnterRoomButton() {
		return enterRoomButton;
	}
}
