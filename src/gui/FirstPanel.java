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
	public FirstPanel(MainFrame frame, SignUpPanel signUp) {
		
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		setBackground(new Color(255, 228, 225));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hell Talk");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 62));
		lblNewLabel.setBounds(395, 144, 319, 183);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.changeSignUpPanel();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 17));
		btnNewButton.setBounds(384, 319, 125, 39);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("로그인");
		btnNewButton_1.setBackground(new Color(255, 182, 193));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.changeLoginPanel();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 17));
		btnNewButton_1.setBounds(534, 319, 125, 39);
		add(btnNewButton_1);
		
		// 임시 버튼 _ 마이페이지
		JButton btnNewButton_2 = new JButton("대충 마이페이지");
		btnNewButton_2.setBackground(new Color(255, 182, 193));
		btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_2.setBounds(680, 319, 125, 39);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfile temp = new UserProfile(signUp);
			}
		});
		add(btnNewButton_2);

	}
}
