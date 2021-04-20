package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.MainFrame;


public class Client {
	private static final String ADDRESS = "192.168.0.71";
//	private static final String ADDRESS = "Localhost";
	private static final int PORT = 2222;
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(ADDRESS, PORT);
			MainFrame main = new MainFrame(socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
