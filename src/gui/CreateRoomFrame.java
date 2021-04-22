package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateRoomFrame extends JDialog {
	private JFrame mainFrame;
	private JButton createButton;
	private JTextField roomName;
	private JComboBox headCount;

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
		
		createButton = new JButton("생성");
		createButton.setBounds(192, 172, 84, 35);
		createButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		getContentPane().add(createButton);
		
		JLabel lblNewLabel = new JLabel("\uBC29\uC81C\uBAA9");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(12, 44, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC778\uC6D0\uC218");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 97, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		headCount = new JComboBox();
		headCount.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		headCount.setMaximumRowCount(10);
		headCount.setBounds(92, 94, 43, 21);
		getContentPane().add(headCount);
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

	public JComboBox getHeadCount() {
		return headCount;
	}

	// 메세지 띄우는 메서드
	public void showMessage(String type, String message) {
		JOptionPane.showMessageDialog(null, message, type, JOptionPane.WARNING_MESSAGE);
	}
}
