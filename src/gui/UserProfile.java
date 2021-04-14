package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import client.SignUpClient;
import library.LoginResult;
import library.User;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

public class UserProfile extends JDialog {
	private SignUpClient socket;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField tfd_pw;
	private JPasswordField tfd_confirm_pw;
	private JTextField tfd_id;
	private JTextField tfd_nickName;
	private ImageIcon btnImage = new ImageIcon("btnImage1.png");
	private JLabel lbl_mainNickName;
	private File currentFile;

	private List<JTextField> list = new ArrayList();
	private User user;
	private JLabel lbl_id;
	private JLabel lbl_pw;
	private JLabel lbl_nickName;
	private JLabel lbl_confirm_pw;
	public UserProfile(SignUpPanel signUp) {
		
		socket = signUp.getSocket();
		
//		User user = new User();
		user = socket.getUserData();
		System.out.println("패널 : " + user.toString());
		
		
		setBounds(100, 100, 350, 480);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 35, 334, 359);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel pnl_fake = new JPanel();
		pnl_fake.setBackground(Color.WHITE);
		pnl_fake.setBounds(0, 205, 334, 154);
		panel.add(pnl_fake);
		
		lbl_mainNickName = new JLabel();
		lbl_mainNickName.setBounds(85, 167, 170, 47);
		panel.add(lbl_mainNickName);
		lbl_mainNickName.setFont(new Font("함초롬바탕", Font.BOLD, 21));
		lbl_mainNickName.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfd_pw = new JPasswordField();
		tfd_pw.setColumns(10);
		tfd_pw.setBounds(121, 283, 170, 21);
		panel.add(tfd_pw);
		
		tfd_confirm_pw = new JPasswordField();
		tfd_confirm_pw.setColumns(10);
		tfd_confirm_pw.setBounds(121, 314, 170, 21);
		panel.add(tfd_confirm_pw);
		
		lbl_id = new JLabel("아이디");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setFont(new Font("함초롬바탕", Font.BOLD, 13));
		lbl_id.setBounds(21, 253, 98, 15);
		panel.add(lbl_id);
		
		tfd_id = new JTextField();
		tfd_id.setColumns(10);
		tfd_id.setEditable(false);
		tfd_id.setBounds(121, 252, 170, 21);
		panel.add(tfd_id);
		
		lbl_pw = new JLabel("비밀번호");
		lbl_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pw.setFont(new Font("함초롬바탕", Font.BOLD, 13));
		lbl_pw.setBounds(21, 284, 97, 15);
		panel.add(lbl_pw);
		
		tfd_nickName = new JTextField();
		tfd_nickName.setColumns(10);
		tfd_nickName.setBounds(121, 223, 170, 21);
		panel.add(tfd_nickName);
		
		// 유저 정보 셋팅
		showUserInfo(user);
		
		
		// 리스트에 담기
		list.add(tfd_nickName);
		list.add(tfd_pw);
		list.add(tfd_confirm_pw);
		
		lbl_nickName = new JLabel("닉네임");
		lbl_nickName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nickName.setFont(new Font("함초롬바탕", Font.BOLD, 13));
		lbl_nickName.setBounds(22, 224, 97, 15);
		panel.add(lbl_nickName);
		
		JButton btn_profile = new JButton(" ");
		btn_profile.setBounds(96, 31, 151, 138);
		btn_profile.setIcon(btnImage);
		panel.add(btn_profile);
		
		lbl_confirm_pw = new JLabel("비밀번호 확인");
		lbl_confirm_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_confirm_pw.setFont(new Font("함초롬바탕", Font.BOLD, 13));
		lbl_confirm_pw.setBounds(21, 316, 88, 15);
		panel.add(lbl_confirm_pw);
		btn_profile.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JDialog dialog1 = new JDialog();
	            JLabel jlabel = new JLabel();
	            jlabel.setIcon(btnImage);
	            dialog1.getContentPane().add(jlabel);
	            dialog1.setModal(true);
	            dialog1.setSize(500, 500);
	            dialog1.setVisible(true);
	         }
	      });
		
		contentPanel.setBackground(new Color(255, 228, 225));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 228, 225));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				
				JButton btn_edit = new JButton("프로필 수정");
				btn_edit.setFont(new Font("함초롬바탕", Font.BOLD, 14));
				
				JButton btn_confirm = new JButton("완료");
				btn_confirm.setFont(new Font("함초롬바탕", Font.BOLD, 14));
				btn_confirm.setLayout(new FlowLayout(FlowLayout.RIGHT));
				btn_confirm.setVisible(false);
				
				JButton btn_load = new JButton("파일 열기");
				btn_load.setFont(new Font("함초롬바탕", Font.BOLD, 14));
				btn_load.setVisible(false);

				// TODO
				// 완료 버튼 눌렀을 때 pw,닉네임이 서버로 보내지게하기
				// 동기화? 새로고침 or 필드내용 자체 변경?

				btn_confirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						socket.closeSocket();
						try {
							socket = new SignUpClient();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						
						String message = "";
						// 빈칸 & 공백 거르기
						if (checkData() == 1) {
							message = "빈칸을 채워주세요.";
							showMessage("Error", message);
						} else if (checkData() == 2) {
							message = "공백을 제거해주세요.";
							showMessage("Error", message);
						} else if (!checkPassword()) {
							message = "비밀번호가 일치하지 않습니다.";
							showMessage("Error", message);
						} else {
							// 클라로 변경될 유저 데이터 보내기
							LoginResult response = socket.updateUserData(getUserInfo());
							// 변경 결과
							int result = response.getResult();
							
							if (result == LoginResult.UPDATE_USERDATA) {
								message = "회원정보가 성공적으로 변경되었습니다.";
								showMessage("회원 정보", message);
								
//								pnl_fake.setVisible(true);
								
								try {
									socket = new SignUpClient();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								user = socket.getUserData();
								System.out.println("패널 : " + user.toString());
								showUserInfo(user);
//								hideField();
								
								
								
								
							} else {
								message = "정보 변경 실패 !";
								showMessage("회원 정보", message);
							}
							
							btn_edit.setVisible(true);
							btn_confirm.setVisible(false);
							btn_load.setVisible(false);
						}
					}
				});
				

				// 프로필 수정 버튼
				// TODO 눌렀을 때 수정한 내용 DB 전송
				//프로필 수정
				btn_edit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pnl_fake.setVisible(false);
						btn_edit.setVisible(false);
						btn_confirm.setVisible(true);
						btn_load.setVisible(true);
					}
				});
				
				//파일 열기
				btn_load.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						pnl_fake.setVisible(false);
						btn_edit.setVisible(false);
						btn_confirm.setVisible(true);
						JFileChooser chooser = new JFileChooser(".");
						int i = chooser.showOpenDialog(null);
						if (i == JFileChooser.APPROVE_OPTION) {
							currentFile = chooser.getSelectedFile();
							btn_profile.setIcon(new ImageIcon(currentFile.getPath()));
							btnImage = new ImageIcon(currentFile.getPath());
						} else if (i == JFileChooser.CANCEL_OPTION) {
							
						}
					}
				});
				
				btn_edit.setHorizontalTextPosition(SwingConstants.LEFT);
				btn_edit.setActionCommand("Cancel");
				buttonPane.add(btn_edit);
				buttonPane.add(btn_load);
				buttonPane.add(btn_confirm);
			}
		}
	}
	// User 객체를 받아서 띄우는 메서드
	public void showUserInfo(User user) {
		tfd_id.setText(user.getId());
		tfd_pw.setText(user.getPassword());
		tfd_nickName.setText(user.getNickname());
		lbl_mainNickName.setText(user.getNickname());
	}
	// 패스워드 String 변환 메서드
	private String getPassword(char[] pw) {
		String password = "";
		for (char p : pw) {
			Character.toString(p);
			password += p;
		}
		System.out.println(password);
		return password;
	}
	// 텍스트 필드 오류 체크 메서드
	public int checkData() {
		for (int i = 0; i < 3; i++) {
			if (list.get(i).getText().equals("")) { 			// 빈칸 체크
				return 1;
			} else if (list.get(i).getText().contains(" ")) {	// 공백 체크
				return 2;
			}
		}
		return 0;
	}
	// 패스워드 일치 여부 체크 메서드
	public boolean checkPassword() {
		String pw = getPassword(tfd_pw.getPassword());
		String conPw = getPassword(tfd_confirm_pw.getPassword());
		if (pw.equals(conPw)) {
			return true;
		}
		return false;
	}
	// 메세지 띄우는 메서드
	public void showMessage(String type, String message) {
		JOptionPane.showMessageDialog(null, message, type, JOptionPane.WARNING_MESSAGE);
	}
	public User getUserInfo() {
		user = new User(tfd_id.getText()
				, getPassword(tfd_pw.getPassword())
				, tfd_nickName.getText());
		return user;
	}
	public void hideField() {
//		List<JTextField> temp = new ArrayList();
		lbl_id.setVisible(false);
		lbl_pw.setVisible(false);
		lbl_confirm_pw.setVisible(false);
		lbl_nickName.setVisible(false);
		tfd_id.setVisible(false);
		tfd_pw.setVisible(false);
		tfd_confirm_pw.setVisible(false);
		tfd_nickName.setVisible(false);
	}
}
