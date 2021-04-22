package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

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
		rommtitleLable.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		rommtitleLable.setBounds(12, 10, 294, 32);
		panel.add(rommtitleLable);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(Color.BLACK, 1));
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setBounds(14, 508, 559, 49);
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
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
