package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstPanel extends JPanel {
	private JButton registrationButton;

	public FirstPanel(MainFrame frame, RegistrationPanel signUp) {
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hell Talk");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 62));
		lblNewLabel.setBounds(365, 144, 319, 183);
		add(lblNewLabel);
		
		registrationButton = new JButton("회원가입");
		registrationButton.setBackground(new Color(255, 182, 193));
		registrationButton.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		registrationButton.setForeground(new Color(255, 255, 255));
		registrationButton.setBounds(354, 319, 125, 39);
		registrationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.changeSignUpPanel();
			}
		});
		add(registrationButton);
		
		JButton btnNewButton_1 = new JButton("로그인");
		btnNewButton_1.setBackground(new Color(255, 182, 193));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnNewButton_1.setBounds(504, 319, 125, 39);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.changeLoginPanel();
			}
		});
		add(btnNewButton_1);
	}

	public JButton getRegistrationButton() {
		return registrationButton;
	}
}
