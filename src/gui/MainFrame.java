package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import client.ChatClient;
import client.SignUpClient;
import library.ObjectInOut;

public class MainFrame extends JFrame {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ObjectInOut object;
	
	private Socket socket;
	private CardLayout cards = new CardLayout();
	private SignUpPanel signUpPanel;
	private FirstPanel firstPanel;
	private LoginPanel loginPanel;
	private BufferedChatPanel bufferedChatPanel;
	private SignUpClient signUpClient;
	private RoomPanel roomPanel;


	public MainFrame(Socket socket) {
		setLayout(cards);
		signUpPanel = new SignUpPanel(this);
		firstPanel = new FirstPanel(this);
		bufferedChatPanel = new BufferedChatPanel();
		this.socket = socket;
		
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
//			oos.writeObject(new ObjectInOut(0, "test"));
//			oos.flush();
			
			oos.writeObject(new ObjectInOut(ObjectInOut.LOGIN, loginPanel.getIdText().getText(), loginPanel.getPassword(loginPanel.getPwText().getPassword())));
			oos.flush();
			signUpClient = new SignUpClient(socket);
			loginPanel = new LoginPanel(MainFrame.this, signUpClient);
//			signUpClient = new SignUpClient(socket);
//			loginPanel = new LoginPanel(this, signUpClient);
			
			Thread reading = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						DataInputStream in;
						try {
							in = new DataInputStream(socket.getInputStream());
							if (in.readUTF().equals("채팅")) {
								ChatClient chat = new ChatClient(socket);
							} else if (in.readUTF().equals("로그인")) {
//								changeChatPanel();
							} else {
								System.out.println("무야호!");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
//			reading.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		getContentPane().add("First", firstPanel);
		getContentPane().add("SignUp", signUpPanel);
		getContentPane().add("Chat", bufferedChatPanel);
		getContentPane().add("Login", loginPanel);
		setVisible(true);
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
