package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class ChatPanel extends JPanel {
	private JTextField textField;
	private JLabel rommtitleLable;
	private JTextArea textArea;
	private JScrollPane scroll;
	public ChatPanel() {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		setPreferredSize(new Dimension(583, 583));
		setMaximumSize(new Dimension(583, 583));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 563, 78);
		panel.setBackground(Color.white);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		add(panel);
		panel.setLayout(null);
		
		rommtitleLable = new JLabel();
		rommtitleLable.setFont(new Font("굴림", Font.PLAIN, 18));
		rommtitleLable.setBounds(12, 10, 294, 32);
		panel.add(rommtitleLable);
		
		JButton btnNewButton = new JButton("유저 리스트");
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.setBounds(12, 45, 97, 23);
		panel.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		
		textField = new JTextField();
		textField.setBounds(14, 508, 559, 49);
		add(textField);
		textField.setColumns(10);
		
		scroll = new JScrollPane(textArea);
		scroll.setBounds(12, 102, 559, 396);
		scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
	}
	
	public JScrollPane getScroll() {
		return scroll;
	}

	public JLabel getRommtitleLable() {
		return rommtitleLable;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}
