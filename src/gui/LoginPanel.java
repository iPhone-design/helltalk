package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	public LoginPanel(MainFrame frame) {
		setBackground(new Color(255, 228, 225));
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		idLbl.setBounds(351, 205, 24, 31);
		
		JLabel pwLbl = new JLabel("PASSWORD");
		pwLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		pwLbl.setBounds(247, 282, 128, 31);
		
		
		JTextField idText = new JTextField();
		idText.setFont(new Font("굴림", Font.PLAIN, 16));
		idText.setBounds(405, 202, 309, 45);
		
		JPasswordField pwText = new JPasswordField();
		pwText.setFont(new Font("굴림", Font.PLAIN, 16));
		pwText.setBounds(405, 279, 309, 45);
		setLayout(null);
		
		
		add(idLbl);		
		add(pwLbl);		
		add(idText);		
		add(pwText);		
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		
		JLabel lblNewLabel = new JLabel("로그인");
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 30));
		lblNewLabel.setBounds(495, 33, 116, 40);
		add(lblNewLabel);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idText.getText().equals("") || pwText.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "빈 칸을 채워주세요.");
				}
			}
		});
		loginBtn.setFont(new Font("함초롬바탕", Font.PLAIN, 20));
		loginBtn.setBackground(new Color(255, 182, 193));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBounds(488, 381, 128, 45);
		add(loginBtn);
		
		JButton btnNewButton = new JButton("대충 집");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.changeFirstPanel();
			}
		});
		btnNewButton.setBounds(23, 21, 97, 23);
		add(btnNewButton);
	}
}
