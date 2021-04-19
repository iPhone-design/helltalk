package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import library.ObjectInOut;

public class CreateRoomFrame extends JDialog {
	private JFrame mainFrame;
	private JButton createButton;
	private JTextField roomName;
	private JTextField headCount;

	public CreateRoomFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		setSize(328, 270);
		getContentPane().setLayout(null);
		setLocationRelativeTo(mainFrame);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 228, 225));
		
		roomName = new JTextField(10);
		roomName.setBounds(92, 34 , 184, 35);
		getContentPane().add(roomName);
		
		headCount = new JTextField(10);
		headCount.setBounds(92, 87 , 38, 35);
		getContentPane().add(headCount);
		
		createButton = new JButton("생성");
		createButton.setBounds(192, 172, 84, 35);
		createButton.setFont(new Font("함초롬바탕", Font.BOLD, 10));
		getContentPane().add(createButton);
		
		JLabel lblNewLabel = new JLabel("\uBC29\uC81C\uBAA9");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 44, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC778\uC6D0\uC218");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 97, 57, 15);
		getContentPane().add(lblNewLabel_1);
	}
	
	public JButton getCreateButton() {
		return createButton;
	}
	
	public JTextField getRoomName() {
		return roomName;
	}

	public void setRoomName(JTextField roomName) {
		this.roomName = roomName;
	}

	public JTextField getHeadCount() {
		return headCount;
	}

	public void setHeadCount(JTextField headCount) {
		this.headCount = headCount;
	}

	// 메세지 띄우는 메서드
	public void showMessage(String type, String message) {
		JOptionPane.showMessageDialog(null, message, type, JOptionPane.WARNING_MESSAGE);
	}

}
