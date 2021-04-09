package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class BufferedChatPanel extends JPanel{
	ChatPanel chatPanel = new ChatPanel();

	public BufferedChatPanel() {
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		chatPanel.setBounds(12, 10, 583, 583);
		add(chatPanel);

	}
}
