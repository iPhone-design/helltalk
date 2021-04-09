package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoomPanel extends JPanel {
	private JLabel titleLbl;
	private JLabel amountLbl;
	private JLabel nameLbl;
	private JButton jbtn;

	public RoomPanel(String roomTitle, String name) {
		setPreferredSize(new Dimension(330, 80));
		setMaximumSize(new Dimension(330, 80));
		setBackground(Color.WHITE);
		setLayout(null);
		
		nameLbl = new JLabel();
		nameLbl.setText(name);
		nameLbl.setFont(new Font("굴림", Font.PLAIN, 20));
		nameLbl.setBounds(213, 5, 69, 24);
		add(nameLbl);
		
		titleLbl = new JLabel();
		titleLbl.setText(roomTitle);
		titleLbl.setFont(new Font("굴림", Font.PLAIN, 20));
		titleLbl.setBounds(12, 5, 69, 24);
		add(titleLbl);
		
		amountLbl = new JLabel("2/10");
		amountLbl.setFont(new Font("굴림", Font.PLAIN, 20));
		amountLbl.setBounds(12, 33, 69, 24);
		add(amountLbl);
		
		jbtn = new JButton("입장");
		jbtn.setBounds(249, 46, 69, 24);
		add(jbtn);
	}

	public JLabel getTitleLbl() {
		return titleLbl;
	}

	public void setTitleLbl(JLabel titleLbl) {
		this.titleLbl = titleLbl;
	}

	public JLabel getAmountLbl() {
		return amountLbl;
	}

	public void setAmountLbl(JLabel amountLbl) {
		this.amountLbl = amountLbl;
	}

	public JLabel getNameLbl() {
		return nameLbl;
	}

	public void setNameLbl(JLabel nameLbl) {
		this.nameLbl = nameLbl;
	}

	public JButton getJbtn() {
		return jbtn;
	}

	public void setJbtn(JButton jbtn) {
		this.jbtn = jbtn;
	}

	
	
	
}
