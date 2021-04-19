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
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedChatPanel bufferedChatPanel;
	private Thread writeTextThead;
	
	public ChatClient(DataOutputStream dos, DataInputStream dis, BufferedChatPanel bufferedChatPanel) {
		this.bufferedChatPanel = bufferedChatPanel;
		this.dos = dos;
		this.dis = dis;
		System.out.println("클라접속");
		writeTextThead = new Thread(new Runnable() {
			@Override
			public void run() {
				String read = null;
				try {
					while ((read = dis.readUTF()) != null) {
						if (read.equals("/종료")) {
							break;
						}
						bufferedChatPanel.getChatPanel().getTextArea().setText(bufferedChatPanel.getChatPanel().getTextArea().getText() + read + "\n");
						bufferedChatPanel.getChatPanel().getScroll().getVerticalScrollBar().setValue(bufferedChatPanel.getChatPanel().getScroll().getVerticalScrollBar().getMaximum());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		writeTextThead.start();
	}

	public Thread getWriteTextThead() {
		return writeTextThead;
	}
}