package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import gui.RoomPanel;
import library.ObjectInOut;

public class Server {
	private final static int PORT = 2222;
	private static Socket socket;
	
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)){
			System.out.println("서버 오픈");
			while (true) {
				socket = server.accept();
				Thread controller = new Thread(new Controller(socket));
				controller.start();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}


