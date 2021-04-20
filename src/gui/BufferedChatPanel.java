package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BufferedChatPanel extends JPanel{
	private ChatPanel chatPanel;
	private RoomListPanel roomlistPanel;
	private String roomTitle;
	private Socket socket;
	private JList roomNameList;
	private JButton enterRoomButton;

	public BufferedChatPanel(String nickname) {
		roomlistPanel = new RoomListPanel();
		roomlistPanel.getAccountNicNameText().setSize(259, 30);
		roomlistPanel.getAccountNicNameText().setLocation(99, 10);
		roomlistPanel.getAccountIdText().setSize(39, 30);
		roomlistPanel.getAccountIdText().setLocation(12, 540);
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		 
		chatPanel = new ChatPanel();
		chatPanel.setBounds(10, 10, 583, 583);
		chatPanel.setVisible(false);
		add(chatPanel, null);
		JButton button = new JButton("무슨 버튼이지?");
		button.setBounds(50, 227, 282, 104);
		chatPanel.add(button);
		roomlistPanel.setBounds(610, 10, 370, 580);
		add(roomlistPanel);
		
		
		roomNameList = new JList();
		roomNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomNameList.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		JScrollPane scroll = new JScrollPane(roomNameList);
		scroll.setBounds(27, 21, 292, 389);
		scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_AS_NEEDED);
		roomlistPanel.getPanelBackground().add(scroll);
		
		enterRoomButton = new JButton("방 입장");
		enterRoomButton.setBounds(54, 526, 120, 23);
		enterRoomButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		roomlistPanel.add(enterRoomButton);
		
		JLabel lblNewLabel = new JLabel("닉네임 : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 10, 87, 31);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		roomlistPanel.add(lblNewLabel);
	}
	
	public JButton getEnterRoomButton() {
		return enterRoomButton;
	}

	public JList getRoomNameList() {
		return roomNameList;
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
