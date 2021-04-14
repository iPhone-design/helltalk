package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.MainFrame;


public class Client {
	private static final String ADDRESS = "192.168.100.33";
	private static final int PORT = 2222;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", PORT);
			MainFrame main = new MainFrame(socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
