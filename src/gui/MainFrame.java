package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private CardLayout cards = new CardLayout();
	private SignUpPanel signUpPanel;
	private FirstPanel firstPanel;
	private LoginPanel loginPanel;
	private BufferedChatPanel bufferedChatPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setLayout(cards);
		signUpPanel = new SignUpPanel(this);
		firstPanel = new FirstPanel(this);
		loginPanel = new LoginPanel(this, signUpPanel);
		bufferedChatPanel = new BufferedChatPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		getContentPane().add("First", firstPanel);
		getContentPane().add("SignUp", signUpPanel);
		getContentPane().add("Login", loginPanel);
		getContentPane().add("Chat", bufferedChatPanel);
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
}
