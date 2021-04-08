package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class SignUpPanel extends JPanel {
	public SignUpPanel() {
		setBackground(new Color(255, 228, 225));
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		idLbl.setBounds(332, 112, 36, 52);
		
		JLabel pwLbl = new JLabel("PASSWORD");
		pwLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		pwLbl.setBounds(234, 193, 134, 52);
		
		JLabel conPwLbl = new JLabel("PASSWORD 확인");
		conPwLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		conPwLbl.setBounds(189, 260, 179, 61);
		
		JLabel nickNameLbl = new JLabel("닉네임");
		nickNameLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		nickNameLbl.setBounds(300, 345, 68, 52);
		
		JLabel ageLbl = new JLabel("나이");
		ageLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		ageLbl.setBounds(318, 427, 56, 52);
		
		JTextField idText = new JTextField();
		idText.setFont(new Font("굴림", Font.PLAIN, 16));
		idText.setBounds(386, 112, 309, 45);
		
		JPasswordField pwText = new JPasswordField();
		pwText.setFont(new Font("굴림", Font.PLAIN, 16));
		pwText.setBounds(386, 193, 309, 45);
		
		JPasswordField conPwText = new JPasswordField();
		conPwText.setFont(new Font("굴림", Font.PLAIN, 16));
		conPwText.setBounds(386, 273, 309, 45);
		
		JTextField nickNameText = new JTextField();
		nickNameText.setFont(new Font("굴림", Font.PLAIN, 16));
		nickNameText.setBounds(386, 354, 309, 45);
		
		JTextField ageText = new JTextField();
		ageText.setFont(new Font("굴림", Font.PLAIN, 16));
		ageText.setBounds(386, 436, 309, 45);
		setLayout(null);
		
		
		add(idLbl);		
		add(pwLbl);		
		add(conPwLbl);		
		add(nickNameLbl);		
		add(ageLbl);		
		add(idText);		
		add(pwText);		
		add(conPwText);		
		add(nickNameText);		
		add(ageText);		
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 30));
		lblNewLabel.setBounds(457, 23, 124, 45);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.setFont(new Font("함초롬바탕", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(451, 523, 150, 45);
		add(btnNewButton);
	}
}
