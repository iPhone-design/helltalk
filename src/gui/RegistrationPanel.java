package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import javax.swing.SwingConstants;

public class RegistrationPanel extends JPanel implements KeyListener {
	private List<JTextField> list = new ArrayList<>();
	private Socket socket;
	private JTextField idText;
	private JPasswordField pwText;
	private JPasswordField conPwText;
	private JTextField nickNameText;
	private JFormattedTextField ageText;
	private JButton addUserButton;
	private ObjectOutputStream oos;
	
	public RegistrationPanel(MainFrame frame, Socket socket) {
		this.socket = socket;
		
		setBackground(new Color(255, 228, 225));
		JLabel idLbl = new JLabel("ID");
		idLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		idLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		idLbl.setBounds(332, 112, 36, 52);
		
		JLabel pwLbl = new JLabel("PASSWORD");
		pwLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pwLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		pwLbl.setBounds(234, 193, 134, 52);
		
		JLabel conPwLbl = new JLabel("PASSWORD 확인");
		conPwLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		conPwLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		conPwLbl.setBounds(176, 273, 192, 61);
		
		JLabel nickNameLbl = new JLabel("닉네임");
		nickNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nickNameLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		nickNameLbl.setBounds(281, 354, 87, 52);
		
		JLabel ageLbl = new JLabel("나이");
		ageLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ageLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 23));
		ageLbl.setBounds(312, 436, 56, 52);
		
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
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(457, 23, 124, 45);
		add(lblNewLabel);
		
		addUserButton = new JButton("회원가입");
		addUserButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		addUserButton.setBackground(new Color(255, 182, 193));
		addUserButton.setForeground(new Color(255, 255, 255));
		addUserButton.setBounds(457, 520, 150, 45);
		add(addUserButton);
		
		JButton btnNewButton_1 = new JButton("홈");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(255, 182, 193));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(27, 23, 70, 70);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.changeFirstPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnNewButton_1);
	}
	
	public JButton getAddUserButton() {
		return addUserButton;
	}

	public JTextField getIdText() {
		return idText;
	}

	public JPasswordField getPwText() {
		return pwText;
	}

	public JPasswordField getConPwText() {
		return conPwText;
	}

	public JTextField getNickNameText() {
		return nickNameText;
	}

	public JFormattedTextField getAgeText() {
		return ageText;
	}
	
	public List<JTextField> getList() {
		return list;
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
	public String getPassword(char[] pw) {
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
		JOptionPane.showMessageDialog(RegistrationPanel.this, message, type, JOptionPane.WARNING_MESSAGE);
	}
}
