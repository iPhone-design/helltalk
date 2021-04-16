package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.JButton;

public class BufferedChatPanel extends JPanel{
	private ChatPanel chatPanel;
	private RoomListPanel roomlistPanel;
	private String roomTitle;
	private Socket socket;

	public BufferedChatPanel(String nickname) {
		roomlistPanel = new RoomListPanel();
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		 
		chatPanel = new ChatPanel("방제목");
		chatPanel.setBounds(10, 10, 583, 583);
		chatPanel.setVisible(false);
		add(chatPanel, null);
		JButton button = new JButton("무슨 버튼이지?");
		button.setBounds(50, 227, 282, 104);
		chatPanel.add(button);
		roomlistPanel.setBounds(610, 10, 370, 580);
		add(roomlistPanel);
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

	public RoomListPanel getRoomlistPanel() {
		return roomlistPanel;
	}

	public void setRoomlistPanel(RoomListPanel roomlistPanel) {
		this.roomlistPanel = roomlistPanel;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}
}
