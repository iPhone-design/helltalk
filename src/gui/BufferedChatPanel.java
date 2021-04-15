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
	private JLayeredPane chatText;

	public BufferedChatPanel(String nickname) {
		chatText = new JLayeredPane();
		roomlistPanel = new RoomListPanel();
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		 
		chatPanel = new ChatPanel("하이");
		chatPanel.setBounds(10, 10, 583, 583);
		chatText.setBounds(10, 10, 583, 583);
		chatText.add(chatPanel, new Integer(0), 0);
		chatText.setVisible(false);
		add(chatText, null);
		JButton button = new JButton("New button");
		button.setBounds(50, 227, 282, 104);
		chatPanel.add(button);
		roomlistPanel.setBounds(610, 10, 370, 580);
		add(roomlistPanel);
	}

	public JLayeredPane getChatText() {
		return chatText;
	}

	public void setChatText(JLayeredPane chatText) {
		this.chatText = chatText;
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
