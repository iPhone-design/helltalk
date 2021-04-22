package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class BufferedChatPanel extends JPanel {
	private ChatPanel chatPanel;
	private RoomListPanel roomlistPanel;
	private String roomTitle;
	private Socket socket;
	private JList roomNameList;
	private JList userNameList;
	private JButton enterRoomButton;
	private CardLayout card;

	public BufferedChatPanel(String nickname) {
		card = new CardLayout();
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
		chatPanel.getTextField().setLocation(12, 508);
		chatPanel.getScroll().setLocation(12, 105);
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
		JScrollPane scrollRoom = new JScrollPane(roomNameList);
		scrollRoom.setBounds(27, 40, 292, 370);
		scrollRoom.setVerticalScrollBarPolicy(scrollRoom.VERTICAL_SCROLLBAR_AS_NEEDED);
		roomlistPanel.getPanelBackground1().add(scrollRoom);

		userNameList = new JList();
		userNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userNameList.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		JScrollPane scrollUser = new JScrollPane(userNameList);
		scrollUser.setBounds(27, 40, 292, 370);
		scrollUser.setVerticalScrollBarPolicy(scrollUser.VERTICAL_SCROLLBAR_AS_NEEDED);
		roomlistPanel.getPanelBackground2().add(scrollUser);

		JLabel roomListLabel = new JLabel("[ 방 리스트 ]");
		roomListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		roomListLabel.setBounds(27, 10, 101, 26);
		roomlistPanel.getPanelBackground1().add(roomListLabel);

		JLabel userListLabel = new JLabel("[ 유저 리스트 ]");
		userListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userListLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		userListLabel.setBounds(27, 10, 101, 26);
		roomlistPanel.getPanelBackground2().add(userListLabel);

		roomlistPanel.getBasePannel().setLayout(card);
		roomlistPanel.getBasePannel().add("roomNameList", roomlistPanel.getPanelBackground1());
		roomlistPanel.getBasePannel().add("userNameList", roomlistPanel.getPanelBackground2());

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

	public void changeRoomNameList() throws IOException {
		card.show(roomlistPanel.getBasePannel(), "roomNameList");
	}

	public void changeUserNameListl() {
		card.show(roomlistPanel.getBasePannel(), "userNameList");
	}

	public JButton getEnterRoomButton() {
		return enterRoomButton;
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

	public JList getRoomNameList() {
		return roomNameList;
	}

	public JList getUserNameList() {
		return userNameList;
	}

	public CardLayout getCard() {
		return card;
	}
}
