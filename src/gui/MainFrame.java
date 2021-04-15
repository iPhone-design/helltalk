package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.channels.AcceptPendingException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.ws.soap.AddressingFeature;

import client.ChatClient;
import client.SignUpClient;
import library.ChatMap;
import library.LoginResult;
import library.ObjectInOut;
import library.User;

public class MainFrame extends JFrame {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private Socket socket;
	private CardLayout cards = new CardLayout();
	private SignUpPanel signUpPanel;
	private FirstPanel firstPanel;
	private LoginPanel loginPanel;
	private BufferedChatPanel bufferedChatPanel;
	private SignUpClient signUpClient;
	private RoomPanel roomPanel;


	public MainFrame(Socket socket) {
		this.socket = socket;
		setLayout(cards);
		signUpPanel = new SignUpPanel(this);
		firstPanel = new FirstPanel(this, signUpPanel);
		loginPanel = new LoginPanel(MainFrame.this, signUpClient);
		bufferedChatPanel = new BufferedChatPanel("임시");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		getContentPane().add("Login", loginPanel);
		getContentPane().add("Chat", bufferedChatPanel);
		getContentPane().add("First", firstPanel);
		getContentPane().add("SignUp", signUpPanel);
		setVisible(true);
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			// 채팅
			bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						oos.writeObject(new ObjectInOut(ObjectInOut.CHAT, "firstRoom", bufferedChatPanel.getRoomlistPanel().getNickNameLbl().getText()));
						oos.flush();
						ChatClient chat = new ChatClient(socket, bufferedChatPanel);
						bufferedChatPanel.getChatText().setVisible(true);
						bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().setEnabled(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						oos.writeObject(new ObjectInOut(ObjectInOut.CHAT, "secondRoom", bufferedChatPanel.getRoomlistPanel().getNickNameLbl().getText()));
						oos.flush();
						ChatClient chat = new ChatClient(socket, bufferedChatPanel);
						bufferedChatPanel.getChatText().setVisible(true);
						bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().setEnabled(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						oos.writeObject(new ObjectInOut(ObjectInOut.CHAT, "thirdRoom", bufferedChatPanel.getRoomlistPanel().getNickNameLbl().getText()));
						oos.flush();
						ChatClient chat = new ChatClient(socket, bufferedChatPanel);
						bufferedChatPanel.getChatText().setVisible(true);
						bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().setEnabled(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			// 나가기
			bufferedChatPanel.getRoomlistPanel().getExitRoomButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						dos.writeUTF("/종료");
						dos.flush();
						bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().setEnabled(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			// 로그아웃
//			bufferedChatPanel.getRoomlistPanel().getExitRoomButton().addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					bufferedChatPanel.getRoomlistPanel().getPanelRoom().getEnterRoomButton().setEnabled(true);
//					changeLoginPanel();
//				}
//			});
			
			// 로그인
			loginPanel.getLoginBtn().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						oos.writeObject(new ObjectInOut(ObjectInOut.LOGIN));
						oos.flush();
						signUpClient = new SignUpClient(socket);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					System.out.println("클라 로그인 시도중");
					User user = null;
					String message = "";

					user = new User(loginPanel.getIdText().getText(), loginPanel.getPassword(loginPanel.getPwText().getPassword()));
					LoginResult response = signUpClient.login(user);
					
					int result = response.getResult();
					
					if (loginPanel.getIdText().getText().equals("") || loginPanel.getPwText().getPassword().length == 0) {
						JOptionPane.showMessageDialog(null, "빈 칸을 채워주세요.");
					} else {
						if (result == LoginResult.OK) {
							message = "로그인 완료";
							bufferedChatPanel.getRoomlistPanel().getNickNameLbl().setText(loginPanel.getIdText().getText());
							loginPanel.clearField();
							changeChatPanel();
						} else if (result == LoginResult.NOT_EXIST) {
							message = "존재하지 않는 아이디 입니다.";
							loginPanel.clearField();
						} else if (result == LoginResult.WRONG_PASSWORD) {
							message = "비밀번호를 다시 확인해주세요.";
							loginPanel.getPwText().setText("");
						}
						JOptionPane.showMessageDialog(loginPanel, message);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	

	public void changeFirstPanel() throws IOException {
		cards.show(this.getContentPane(), "First");
	}

	public void changeSignUpPanel() {
		cards.show(this.getContentPane(), "SignUp");
	}

	public void changeLoginPanel() {
		cards.show(this.getContentPane(), "Login");
	}
	
	public void changeChatPanel() {
		cards.show(this.getContentPane(), "Chat");
	}
	
	public SignUpPanel getSignUpPanel() {
		return signUpPanel;
	}

	public void setSignUpPanel(SignUpPanel signUpPanel) {
		this.signUpPanel = signUpPanel;
	}

	public FirstPanel getFirstPanel() {
		return firstPanel;
	}

	public void setFirstPanel(FirstPanel firstPanel) {
		this.firstPanel = firstPanel;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
}
