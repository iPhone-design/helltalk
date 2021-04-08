package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class UserEntrance {
	private Socket client;
	private DataInputStream reader;
	private DataOutputStream writer;
	private Thread chat;
	
	public UserEntrance(Socket client) {
		this.client = client;
		try {
			writer = new DataOutputStream(client.getOutputStream());
			reader = new DataInputStream(client.getInputStream());
			chat.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeText() {
		Scanner scan = new Scanner(System.in);
		String write = null;
		try {
			while ((write = scan.nextLine()) != null) {
				if (reader.readUTF().equals("/종료")) {
					break;
				}
				writer.writeUTF(write);
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readText() {
		String read = null;
		try {
			while ((read = reader.readUTF())!= null) {
				if (read.equals("/종료")) {
					break;
				}
				System.out.println(read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void readWriteThread() {
		chat = new Thread(new Runnable() {
			@Override
			public void run() {
				readText();
				writeText();
			}
		});
	}
}
