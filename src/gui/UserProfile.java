package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.SignUpClient;
import library.User;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class UserProfile extends JDialog {
	private SignUpClient socket;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfd_pw;
	private JTextField tfd_id;
	private JTextField tfd_nickName;
	private ImageIcon btnImage = new ImageIcon("btnImage1.png");
	private JLabel lbl_mainNickName;

	// User 객체를 받아서 띄우는 메서드
	public void showUserInfo(User user) {
		tfd_id.setText(user.getId());
		tfd_pw.setText(user.getPassword());
		tfd_nickName.setText(user.getNickname());
		lbl_mainNickName.setText(user.getNickname());
	}
	
	public UserProfile(SignUpPanel signUp) {
		
		socket = signUp.getSocket();
		
		User user = new User();
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
		pnl_fake.setBounds(22, 223, 299, 114);
		panel.add(pnl_fake);
		
		lbl_mainNickName = new JLabel();
		lbl_mainNickName.setBounds(85, 167, 170, 47);
		panel.add(lbl_mainNickName);
		lbl_mainNickName.setFont(new Font("함초롬바탕", Font.BOLD, 21));
		lbl_mainNickName.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfd_pw = new JTextField();
		tfd_pw.setColumns(10);
		tfd_pw.setBounds(109, 268, 170, 21);
		panel.add(tfd_pw);
		
		JLabel lbl_id = new JLabel("아이디");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		lbl_id.setBounds(40, 240, 57, 15);
		panel.add(lbl_id);
		
		tfd_id = new JTextField();
		tfd_id.setColumns(10);
		tfd_id.setEditable(false);
		tfd_id.setBounds(109, 237, 170, 21);
		panel.add(tfd_id);
		
		JLabel lbl_pw = new JLabel("비밀번호");
		lbl_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pw.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		lbl_pw.setBounds(22, 271, 75, 15);
		panel.add(lbl_pw);
		
		tfd_nickName = new JTextField();
		tfd_nickName.setColumns(10);
		tfd_nickName.setBounds(109, 299, 170, 21);
		panel.add(tfd_nickName);
		
		// 유저 정보 셋팅
		showUserInfo(user);
		
		JLabel lbl_nickName = new JLabel("닉네임");
		lbl_nickName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nickName.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		lbl_nickName.setBounds(40, 302, 57, 15);
		panel.add(lbl_nickName);
		
		JButton btn_profile = new JButton(" ");
		btn_profile.setBounds(96, 31, 151, 138);
		btn_profile.setIcon(btnImage);
		panel.add(btn_profile);
		btn_profile.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JDialog dialog = new JDialog();
	            JLabel jlabel = new JLabel();
	            jlabel.setIcon(btnImage);
	            dialog.getContentPane().add(jlabel);
	            dialog.setModal(true);
	            dialog.setSize(500, 500);
	            dialog.setVisible(true);
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
					User user;
					@Override
					public void actionPerformed(ActionEvent e) {
						pnl_fake.setVisible(true);
						btn_edit.setVisible(true);
						btn_confirm.setVisible(false);
						btn_load.setVisible(false);
						// 클라로 데이터 보내기
						
						// 기존 패스워드와 동일한 경우 거르기
							
						// 
						
					}
				});
				
				// 프로필 수정 버튼
				// TODO 눌렀을 때 수정한 내용 DB 전송
				btn_edit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pnl_fake.setVisible(false);
						btn_edit.setVisible(false);
						btn_confirm.setVisible(true);
						btn_load.setVisible(true);
						
					}
				});
				
				// TODO 프로필 사진 변경 버튼
				btn_load.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
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
}
