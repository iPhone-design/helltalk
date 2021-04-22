package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class LoginPanel extends JPanel implements KeyListener {
	private MainFrame frame;
	private JTextField idText;
	private JPasswordField pwText;
	private JButton loginBtn;
	private JButton registrationBtn;
	
	public LoginPanel(MainFrame frame) {
		this.frame = frame;
		
		setBackground(new Color(255, 228, 225));
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		idLbl.setBounds(351, 205, 24, 31);
		
		JLabel pwLbl = new JLabel("PASSWORD");
		pwLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		pwLbl.setBounds(247, 282, 128, 31);
		
		idText = new JTextField();
		idText.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		idText.setBounds(405, 202, 309, 45);
		idText.addKeyListener(this);
		
		pwText = new JPasswordField();
		pwText.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		pwText.setBounds(405, 279, 309, 45);
		pwText.addKeyListener(this);
		setLayout(null);
		
		add(idLbl);
		add(pwLbl);
		add(idText);
		add(pwText);
		setPreferredSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(495, 33, 400, 40);
		add(lblNewLabel);
		
		loginBtn = new JButton("로그인");
		loginBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		loginBtn.setBackground(new Color(255, 182, 193));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBounds(583, 381, 125, 45);
		add(loginBtn);
		
		JButton btnNewButton = new JButton("홈");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(23, 21, 70, 70);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.changeFirstPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnNewButton);
	}
	
	public JTextField getIdText() {
		return idText;
	}
	
	public JPasswordField getPwText() {
		return pwText;
	}
	
	public JButton getLoginBtn() {
		return loginBtn;
	}
	
	public String getPassword(char[] pw) {
		String password = "";
		for (char p : pw) {
			Character.toString(p);
			password += p;
		}
		return password;
	}
	
	public void clearField() {
		idText.setText("");
		pwText.setText("");
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		int num = 20;
		int length = ((JTextComponent) e.getSource()).getText().length();
		if (length > num) {
			e.consume();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
