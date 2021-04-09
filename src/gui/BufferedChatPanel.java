package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BufferedChatPanel extends JPanel{
	ChatPanel chatPanel = new ChatPanel();
	RoomListPanel roomlistPanel;

	public BufferedChatPanel() {
		roomlistPanel = new RoomListPanel(this);
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		chatPanel.setBounds(10, 10, 583, 583);
		chatPanel.setVisible(false);
		add(chatPanel);
		
		JButton button = new JButton("New button");
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

}
