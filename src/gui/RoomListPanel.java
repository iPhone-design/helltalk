package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

 
public class RoomListPanel extends JPanel {
	private JTextField textField;
	private JPanel panel;
	private List<RoomPanel> roomList;
	int panelY = 5;
	public RoomListPanel(BufferedChatPanel buffer) {
		roomList = new ArrayList<>();
		panel = new JPanel();
	 	setPreferredSize(new Dimension(370, 580));
	    setMaximumSize(new Dimension(370, 580));
	    setBackground(Color.white);
	    setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    setLayout(null);
	     
	    panel.setBounds(12, 51, 346, 437);
	    panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    panel.setLayout(null);	 	
	     
	    textField = new JTextField();
	    textField.setBounds(12, 10, 265, 31);
	    add(textField);
	    textField.setColumns(10);
	    JButton btnNewButton = new JButton("검색");
	    btnNewButton.setBounds(279, 10, 79, 31);
	    add(btnNewButton);
	    JButton btnNewButton_1 = new JButton("방 개설");
	    btnNewButton_1.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		CreateRoomFrame frame = new CreateRoomFrame(buffer, RoomListPanel.this);
	     	}
	     });
	    
	    btnNewButton_1.setBounds(54, 498, 97, 23);
	    add(btnNewButton_1);
	     
	    JButton btnNewButton_2 = new JButton("로그아웃");
	    btnNewButton_2.setBounds(206, 498, 97, 23);
	    add(btnNewButton_2);
	     
	    add(panel);
	
	 
	 }

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public List<RoomPanel> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<RoomPanel> roomList) {
		this.roomList = roomList;
	}

	public int getPanelY() {
		return panelY;
	}

	public void setPanelY(int panelY) {
		this.panelY = panelY;
	}

	
	
}
