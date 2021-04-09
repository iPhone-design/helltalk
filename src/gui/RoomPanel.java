package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class RoomPanel extends JPanel {
	public RoomPanel() {
		setPreferredSize(new Dimension(330, 80));
		setMaximumSize(new Dimension(330, 80));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("민초");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(213, 5, 69, 24);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("방1");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 5, 69, 24);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("2/10");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(12, 33, 69, 24);
		add(lblNewLabel_2);
	}
}
