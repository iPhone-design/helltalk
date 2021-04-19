package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.ChatClient;
import client.SignUpClient;
import library.ChatMap;
import library.ObjectInOut;
import library.Room;

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
	private ObjectInOut object;
	private UserProfile userProfile;
	private CreateRoomFrame createRoomFrame;
	private Thread refreshRoomList;

	public MainFrame(Socket socket) {
		this.socket = socket;
		setLayout(cards);
		setResizable(false);
		createRoomFrame = new CreateRoomFrame(this);
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
			
			// 채팅 쓰기 부분
			bufferedChatPanel.getChatPanel().getTextField().addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String write = bufferedChatPanel.getChatPanel().getTextField().getText();
						bufferedChatPanel.getChatPanel().getTextField().setText("");
						try {
							dos.writeUTF(write);
							dos.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
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
						bufferedChatPanel.getRoomlistPanel().getLogoutButton().setEnabled(true);
						bufferedChatPanel.getRoomlistPanel().getExitRoomButton().setEnabled(false);
//						bufferedChatPanel.getRoomlistPanel().getFirstRoom().getEnterRoomButton().setEnabled(true);
//						bufferedChatPanel.getRoomlistPanel().getSecondRoom().getEnterRoomButton().setEnabled(true);
//						bufferedChatPanel.getRoomlistPanel().getThirdRoom().getEnterRoomButton().setEnabled(true);
						bufferedChatPanel.getChatPanel().getTextArea().setText("");
						bufferedChatPanel.getChatPanel().setVisible(false);
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
									refreshRoomList.start();
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
			
			// 방 개설 창
			bufferedChatPanel.getRoomlistPanel().getCreateRoomButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					createRoomFrame.setVisible(true);
				}
			});
			
			// 방 생성
			createRoomFrame.getCreateButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ObjectInOut object = new ObjectInOut(ObjectInOut.CREATEROOM, createRoomFrame.getRoomName().getText(), bufferedChatPanel.getRoomlistPanel().getAccountNicNameText().getText(), Integer.valueOf(createRoomFrame.getHeadCount().getText()), 0);
					try {
						oos.writeObject(object);
						oos.flush();
						object = (ObjectInOut) ois.readObject();
						if (object.getProtocol() == ObjectInOut.CREATEROOM) {
							if (object.getResult() == 1) {
								createRoomFrame.showMessage("Success", "방 생성 성공");
								createRoomFrame.getRoomName().setText("");
								createRoomFrame.getHeadCount().setText("");
								createRoomFrame.setVisible(false);
							} else {
								createRoomFrame.showMessage("Fail", "이미 존재하는 방입니다.");
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		// 방 새로고침
	    refreshRoomList = new Thread(new Runnable() {
			@Override
			public void run() {
				ObjectInOut object = new ObjectInOut();
				while (true) {
					try {
						object = new ObjectInOut(ObjectInOut.REFRESHROOM);
						oos.writeObject(object);
						oos.flush();
						object = (ObjectInOut) ois.readObject();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					if (object.getProtocol() == ObjectInOut.REFRESHROOM) {
						// TODO 고쳐야함 시부레!
						bufferedChatPanel.getRoomlistPanel().getPanelBackground().removeAll();
						bufferedChatPanel.getRoomlistPanel().getPanelBackground().repaint();
						bufferedChatPanel.getRoomlistPanel().getPanelBackground().revalidate();
						java.util.List<Room> roomlist = object.getRoomlist();
						for (int i = 0; i <= roomlist.size() - 1; i++) {
							RoomPanel roomPanel = new RoomPanel(roomlist.get(i).getTitle(), roomlist.get(i).getRoomMasterName(), roomlist.get(i).getHeadCount(), oos, ois, dos, dis, bufferedChatPanel);
							roomPanel.setBounds(8, 5 + (i * 85), 330, 80);
							bufferedChatPanel.getRoomlistPanel().getPanelBackground().add(roomPanel);
							bufferedChatPanel.getRoomlistPanel().getPanelBackground().repaint();
							bufferedChatPanel.getRoomlistPanel().getPanelBackground().revalidate();
						}
						try {
							Thread.sleep(300000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
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
