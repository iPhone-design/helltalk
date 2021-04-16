package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.BufferedChatPanel;

public class ChatClient {
	private Socket socket;
	private BufferedChatPanel bufferedChatPanel;
	
	public ChatClient(Socket socket, BufferedChatPanel bufferedChatPanel) {
		this.bufferedChatPanel = bufferedChatPanel;
		this.socket = socket;
		try {
			System.out.println("클라접속");
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			
			Thread writeTextThead = new Thread(new Runnable() {
				@Override
				public void run() {
					String read = null;
					try {
						while ((read = dis.readUTF()) != null) {
							bufferedChatPanel.getChatPanel().getTextArea().setText(bufferedChatPanel.getChatPanel().getTextArea().getText() + read + "\n");
							bufferedChatPanel.getChatPanel().getScroll().getVerticalScrollBar().setValue(bufferedChatPanel.getChatPanel().getScroll().getVerticalScrollBar().getMaximum());
							bufferedChatPanel.getChatPanel().getTextField().setText("");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			writeTextThead.start();
			
			bufferedChatPanel.getChatPanel().getTextField().addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String write = bufferedChatPanel.getChatPanel().getTextField().getText();
						try {
							dos.writeUTF(write);
							dos.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
		} catch (UnknownHostException e) {
			// 어케 해결함?????????????????????????!!#@!#!@#@!#!@
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}