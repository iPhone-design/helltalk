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
import javax.swing.JPanel;
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
	private RegistrationPanel registrationPanel;
	private FirstPanel firstPanel;
	private LoginPanel loginPanel;
	private BufferedChatPanel bufferedChatPanel;
	private SignUpClient signUpClient;
	private RoomPanel roomPanel;
	private RoomListPanel roomListPanel;
	private ObjectInOut object;
	private UserProfile userProfile;

	public MainFrame(Socket socket) {
		this.socket = socket;
		setLayout(cards);
		setResizable(false);
		roomListPanel = new RoomListPanel();
		bufferedChatPanel = new BufferedChatPanel("닉네임");
		registrationPanel = new RegistrationPanel(this, socket);
		firstPanel = new FirstPanel(this, registrationPanel);
		loginPanel = new LoginPanel(MainFrame.this, signUpClient);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		getContentPane().add("First", firstPanel);
		getContentPane().add("Login", loginPanel);
		getContentPane().add("Chat", bufferedChatPanel);
		getContentPane().add("SignUp", registrationPanel);
		setLocationRelativeTo(null);
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
						oos.writeObject(new ObjectInOut(ObjectInOut.CHAT, "firstRoom", bufferedChatPanel.getRoomlistPanel().getAccountNicNameText().getText()));
						oos.flush();
						ChatClient chat = new ChatClient(socket, bufferedChatPanel);
						bufferedChatPanel.getChatPanel().setVisible(true);
						bufferedChatPanel.getRoomlistPanel().getExitRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getLogoutButton().setEnabled(false);
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
						oos.writeObject(new ObjectInOut(ObjectInOut.CHAT, "secondRoom", bufferedChatPanel.getRoomlistPanel().getAccountNicNameText().getText()));
						oos.flush();
						ChatClient chat = new ChatClient(socket, bufferedChatPanel);
						bufferedChatPanel.getChatPanel().setVisible(true);
						bufferedChatPanel.getRoomlistPanel().getExitRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getLogoutButton().setEnabled(false);
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
						oos.writeObject(new ObjectInOut(ObjectInOut.CHAT, "thirdRoom", bufferedChatPanel.getRoomlistPanel().getAccountNicNameText().getText()));
						oos.flush();
						ChatClient chat = new ChatClient(socket, bufferedChatPanel);
						bufferedChatPanel.getChatPanel().setVisible(true);
						bufferedChatPanel.getRoomlistPanel().getExitRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getLogoutButton().setEnabled(false);
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
						bufferedChatPanel.getChatPanel().setVisible(false);
						bufferedChatPanel.getChatPanel().getTextArea().setText("");
						bufferedChatPanel.getChatPanel().getTextField().setText("");
						bufferedChatPanel.getRoomlistPanel().getLogoutButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getExitRoomButton().setEnabled(false);
						bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().setEnabled(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			//로그아웃
			bufferedChatPanel.getRoomlistPanel().getLogoutButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						changeFirstPanel();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			// 로그인
			loginPanel.getLoginBtn().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (loginPanel.getIdText().getText().equals("") || loginPanel.getPwText().getPassword().length == 0) {
							JOptionPane.showMessageDialog(null, "빈 칸을 채워주세요.");
						} else {
							object = new ObjectInOut(ObjectInOut.LOGIN, loginPanel.getIdText().getText(), loginPanel.getPassword(loginPanel.getPwText().getPassword()), 0);
							oos.writeObject(object);
							oos.flush();
							object = (ObjectInOut) ois.readObject();
							if (object.getProtocol() == ObjectInOut.LOGIN) {
								if (object.getResult() == 0) {
									JOptionPane.showMessageDialog(registrationPanel, "로그인 실패 존재하지 않는 계정", "로그인", JOptionPane.WARNING_MESSAGE);
								} else if (object.getResult() == 1) {
									JOptionPane.showMessageDialog(registrationPanel, "로그인 성공", "로그인", JOptionPane.INFORMATION_MESSAGE);
									bufferedChatPanel.getRoomlistPanel().getAccountIdText().setText(object.getId());
									bufferedChatPanel.getRoomlistPanel().getAccountNicNameText().setText(object.getNickName());
									loginPanel.clearField();
									changeChatPanel();
								} else if (object.getResult() == 2) {
									JOptionPane.showMessageDialog(registrationPanel, "비밀번호가 틀렸습니다", "로그인", JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			// 회원가입
			registrationPanel.getAddUserButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String message = "";
						if (registrationPanel.checkData() == 1) {
							message = "빈칸을 채워주세요.";
							registrationPanel.showMessage("Error", message);
						} else if (registrationPanel.checkData() == 2) {
							message = "공백을 제거해주세요.";
							registrationPanel.showMessage("Error", message);
						} else if (!registrationPanel.checkPassword()) {
							message = "비밀번호가 틀립니다.";
							registrationPanel.showMessage("Error", message);
						} else {
							object = new ObjectInOut(ObjectInOut.REGISTRATION, registrationPanel.getIdText().getText(),
									registrationPanel.getPassword(registrationPanel.getPwText().getPassword()),
									registrationPanel.getNickNameText().getText(),
									Integer.valueOf(registrationPanel.getAgeText().getText()));
							oos.writeObject(object);
							oos.flush();
							
							object = (ObjectInOut) ois.readObject();
							if (object.getProtocol() == ObjectInOut.REGISTRATION) {
								if (object.getResult() == 0) {
									JOptionPane.showMessageDialog(registrationPanel, "회원가입 성공", "회원가입", JOptionPane.INFORMATION_MESSAGE);
									registrationPanel.clearField();
									changeLoginPanel();
								} else if (object.getResult() == 1) {
									JOptionPane.showMessageDialog(registrationPanel, "이미 존재하는 계정 입니다.", "회원가입", JOptionPane.WARNING_MESSAGE);
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			// 마이페이지
			bufferedChatPanel.getRoomlistPanel().getMyPageButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						object = new ObjectInOut(ObjectInOut.MYPAGE, bufferedChatPanel.getRoomlistPanel().getAccountIdText().getText());
						oos.writeObject(object);
						oos.flush();
						object = (ObjectInOut) ois.readObject();
						if (object.getProtocol() == ObjectInOut.MYPAGE) {
							userProfile = new UserProfile(oos, ois, MainFrame.this, object.getId(), object.getNickName());
							userProfile.setVisible(true);
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			// 방개설
			bufferedChatPanel.getRoomlistPanel().getCreateRoomButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("동작중?");
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
	
	public RegistrationPanel getSignUpPanel() {
		return registrationPanel;
	}

	public void setSignUpPanel(RegistrationPanel signUpPanel) {
		this.registrationPanel = signUpPanel;
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
