package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

 
public class RoomListPanel extends JPanel {
	private RoomPanel roomPanel = new RoomPanel();
	private JTextField textField;
	
	
	public RoomListPanel(BufferedChatPanel buffer) {
	 	setPreferredSize(new Dimension(370, 580));
	    setMaximumSize(new Dimension(370, 580));
	    setBackground(Color.white);
	    setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    setLayout(null);
	     
	    JPanel panel = new JPanel();
	    panel.setBounds(12, 51, 346, 437);
	    panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	    panel.setLayout(null);
	    roomPanel.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		super.mouseClicked(e);
	    		buffer.getChatPanel().setVisible(true);
	    	}
		}); 
	    int y = 5;
	    for (int i = 0; i < 3; i++) {
	    	 RoomPanel room = new RoomPanel();
	    	 room.setBounds(8, y, 330, 80);
	    	 panel.add(room);
	    	 y += 85;
	    	 
	     }
	     
	     
	     
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
	     		int y = 260;
	     		RoomPanel room = new RoomPanel();
	     		room.setBounds(8, y, 330, 80);
		    	panel.add(room);
		    	System.out.println("saoaweoao");
		    	revalidate();
		    	repaint();
		    	y += 80;
		    	
	     	}
	     });
	    btnNewButton_1.setBounds(54, 498, 97, 23);
	    add(btnNewButton_1);
	     
	    JButton btnNewButton_2 = new JButton("로그아웃");
	    btnNewButton_2.setBounds(206, 498, 97, 23);
	    add(btnNewButton_2);
	     
	    add(panel);
	
	 
	 } 
}
