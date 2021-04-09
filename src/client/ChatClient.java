package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	private static final String ADDRESS = "192.168.100.33";
	private static final int PORT = 2222;
	
	public static void main(String[] args) {
		try (Socket socket = new Socket(ADDRESS, PORT);) {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner scan = new Scanner(System.in);
			
			Thread writeTextThead = new Thread(new Runnable() {
				@Override
				public void run() {
					String read = null;
					try {
						while ((read = dis.readUTF()) != null) {
							System.out.println(read);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			writeTextThead.start();
			
			while (true) {
				String write = null;
				write = scan.nextLine();
				if (write.equals("/종료")) {
					break;
				}
				dos.writeUTF(write);
				dos.flush();
			}
		} catch (UnknownHostException e) {
			// 어케 해결함?????????????????????????!!#@!#!@#@!#!@
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}