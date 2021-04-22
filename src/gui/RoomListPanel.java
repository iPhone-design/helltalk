package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RoomListPanel extends JPanel {
	private JPanel panelBackground1;
	private JPanel panelBackground2;
	private JPanel bacePannel;
	private Socket socket;
	private int panelY = 5;
	private JButton exitRoomButton;
	private JButton logoutButton;
	private JLabel accountIdText;
	private JButton myPageButton;
	private JButton createRoomButton;
	private JLabel accountNicNameText;
	
	public RoomListPanel() {
		panelBackground1 = new JPanel();
		panelBackground2 = new JPanel();
		bacePannel = new JPanel();
	 	setPreferredSize(new Dimension(370, 580));
	    setMaximumSize(new Dimension(370, 580));
	    setBackground(Color.white);
	    setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    setLayout(null);
	    
	    panelBackground1.setBounds(12, 51, 346, 437);
	    panelBackground1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    panelBackground1.setLayout(null);
	    
	    panelBackground2.setBounds(12, 51, 346, 437);
	    panelBackground2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    panelBackground2.setLayout(null);
	    
	    bacePannel.setBounds(12, 51, 346, 437);
	    bacePannel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    bacePannel.setLayout(null);
	    
	    createRoomButton = new JButton("방 개설");
	    createRoomButton.setBounds(54, 498, 120, 23);
	    createRoomButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    add(createRoomButton);
	    
	    logoutButton = new JButton("로그아웃");
	    logoutButton.setBounds(206, 498, 120, 23);
	    logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    add(logoutButton);
	    
	    exitRoomButton = new JButton("나가기");
	    exitRoomButton.setBounds(206, 526, 120, 23);
	    exitRoomButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    exitRoomButton.setEnabled(false);
	    
	    add(exitRoomButton);
	    add(bacePannel);
	    
	    myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(206, 554, 120, 23);
		myPageButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(myPageButton);
	    
	    accountIdText = new JLabel();
	    accountIdText.setBounds(54, 536, 97, 40);
	    accountIdText.setForeground(Color.white);
	    add(accountIdText);
	    
	    accountNicNameText = new JLabel();
	    accountNicNameText.setBounds(54, 526, 97, 40);
	    accountNicNameText.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    accountNicNameText.setForeground(Color.black);
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

	public JButton getExitRoomButton() {
		return exitRoomButton;
	}
	
	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JPanel getPanelBackground1() {
		return panelBackground1;
	}

	public JPanel getPanelBackground2() {
		return panelBackground2;
	}

	public JPanel getBacePannel() {
		return bacePannel;
	}
}