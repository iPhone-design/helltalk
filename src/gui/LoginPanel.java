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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import client.SignUpClient;
import library.LoginResult;
import library.User;

public class LoginPanel extends JPanel implements KeyListener {
	private SignUpClient signUpClient;
	private MainFrame frame;
	private JTextField idText;
	private JPasswordField pwText;
	private JButton loginBtn;
	
	public LoginPanel(MainFrame frame, SignUpClient signUpClient) {
		this.frame = frame;
		this.signUpClient = signUpClient;
		
		setBackground(new Color(255, 228, 225));
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		idLbl.setBounds(351, 205, 24, 31);
		
		JLabel pwLbl = new JLabel("PASSWORD");
		pwLbl.setFont(new Font("함초롬바탕", Font.PLAIN, 23));
		pwLbl.setBounds(247, 282, 128, 31);
		
		idText = new JTextField();
		idText.setFont(new Font("굴림", Font.PLAIN, 16));
		idText.setBounds(405, 202, 309, 45);
		idText.addKeyListener(this);
		
		pwText = new JPasswordField();
		pwText.setFont(new Font("굴림", Font.PLAIN, 16));
		pwText.setBounds(405, 279, 309, 45);
		pwText.addKeyListener(this);
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
		
		loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("클라 로그인 시도중");
				User user = null;
				String message = "";

				LoginResult response = signUpClient.login(new User(idText.getText(), getPassword(pwText.getPassword())));
//				socket = signUp.getSocket();
				
//				user = new User(idText.getText(), getPassword(pwText.getPassword()));
//				LoginResult response = socket.login(user);
				
				int result = response.getResult();
				
				if (idText.getText().equals("") || pwText.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "빈 칸을 채워주세요.");
				} else {
					if (result == LoginResult.OK) {
						message = "로그인 완료";
						clearField();
						frame.changeChatPanel();
					} else if (result == LoginResult.NOT_EXIST) {
						message = "존재하지 않는 아이디 입니다.";
						clearField();
					} else if (result == LoginResult.WRONG_PASSWORD) {
						message = "비밀번호를 다시 확인해주세요.";
						pwText.setText("");
					}
					JOptionPane.showMessageDialog(LoginPanel.this, message);
					
//					socket.closeSocket();
//					signUp.setSocket(new SignUpClient(socket1));
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
				try {
					frame.changeFirstPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(23, 21, 97, 23);
		add(btnNewButton);
	}
	
	public SignUpClient getSocket() {
		return signUpClient;
	}
	
	public void setSocket(SignUpClient socket) {
		this.signUpClient = socket;
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
	
	///////////////////////////////////////////////////////////
	
	public String getPassword(char[] pw) {
		String password = "";
		for (char p : pw) {
			Character.toString(p);
			password += p;
		}
		System.out.println(password);
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
