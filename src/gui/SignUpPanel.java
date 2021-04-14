package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;

import com.mysql.cj.protocol.Warning;

import client.SignUpClient;
import library.LoginResult;
import library.User;

public class SignUpPanel extends JPanel implements KeyListener {
	private SignUpClient socket;
	
	List<JTextField> list = new ArrayList<>();
	private JTextField idText;
	private JPasswordField pwText;
	private JPasswordField conPwText;
	private JTextField nickNameText;
	private JFormattedTextField ageText;
	
	public SignUpPanel(MainFrame frame) {
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
		
		idText = new JTextField();
		idText.setFont(new Font("굴림", Font.PLAIN, 16));
		idText.setBounds(386, 112, 309, 45);
		idText.addKeyListener(this);
		
		pwText = new JPasswordField();
		pwText.setFont(new Font("굴림", Font.PLAIN, 16));
		pwText.setBounds(386, 193, 309, 45);
		pwText.addKeyListener(this);
		
		conPwText = new JPasswordField();
		conPwText.setFont(new Font("굴림", Font.PLAIN, 16));
		conPwText.setBounds(386, 273, 309, 45);
		conPwText.addKeyListener(this);
		
		nickNameText = new JTextField();
		nickNameText.setFont(new Font("굴림", Font.PLAIN, 16));
		nickNameText.setBounds(386, 354, 309, 45);
		nickNameText.addKeyListener(this);
		
		ageText = new JFormattedTextField(new NumberFormatter());
		ageText.setFont(new Font("굴림", Font.PLAIN, 16));
		ageText.setBounds(386, 436, 309, 45);
		setLayout(null);
		ageText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = ((JTextComponent) e.getSource()).getText().length();
				if (length > 2) {
					e.consume();
				}
				super.keyTyped(e);
			}
		});
		
		// 리스트에 텍스트 필드 추가
		list.add(idText);
		list.add(pwText);
		list.add(conPwText);
		list.add(nickNameText);
		list.add(ageText);
		
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if (checkData() == 1) {
					message = "빈칸을 채워주세요.";
					showMessage("Error", message);
				} else if (checkData() == 2) {
					message = "공백을 제거해주세요.";
					showMessage("Error", message);
				} else if (!checkPassword()) {
					message = "비밀번호가 틀립니다.";
					showMessage("Error", message);
				} else {
					User user = new User(idText.getText()
										, getPassword(pwText.getPassword())
										, nickNameText.getText()
										, Integer.parseInt(ageText.getText()));
					LoginResult response = socket.add(user);
					
					int result = response.getResult();
					if (result == LoginResult.ID_EXIST) {
						message = "아이디가 중복되었습니다.";
						idText.setText("");
					} else if (result == LoginResult.OK) {
						message = "가입이 완료되었습니다.";
						clearField();
					}
					showMessage("회원가입", message);
//					socket.closeSocket();
//					try {
//						socket = new SignUpClient();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
				}
			}
		});
		btnNewButton.setFont(new Font("함초롬바탕", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(457, 520, 150, 45);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("대충 집모양");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.changeFirstPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(27, 23, 97, 23);
		add(btnNewButton_1);
	}
	public SignUpClient getSocket() {
		return socket;
	}
	public void setSocket(SignUpClient socket) {
		this.socket = socket;
	}
	// 텍스트 필드 입력 수 제한
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
	
	// 텍스트 필드 초기화
	public void clearField() {
		for (int i = 0; i < 5; i++) {
			list.get(i).setText("");
		}
	}
	// 텍스트 필드에 입력된 데이터 검사
	public int checkData() {
		for (int i = 0; i < 5; i++) {
			if (list.get(i).getText().equals("")) { // 빈칸 체크
				return 1;
			} else if (list.get(i).getText().contains(" ")) {
				return 2;
			}
		}
		return 0;
	}
	// 비밀번호 String 변환
	private String getPassword(char[] pw) {
		String password = "";
		for (char p : pw) {
			Character.toString(p);
			password += p;
		}
		return password;
	}
	// 비밀번호 일치 여부 체크
	public boolean checkPassword() {
		String pw = getPassword(pwText.getPassword());
		String conPw = getPassword(conPwText.getPassword());
		if (pw.equals(conPw)) {
			return true;
		}
		pwText.setText("");
		conPwText.setText("");
		return false;
	}
	public void showMessage(String type, String message) {
		JOptionPane.showMessageDialog(SignUpPanel.this, message, type, JOptionPane.WARNING_MESSAGE);
	}
}
