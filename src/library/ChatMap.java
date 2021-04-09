package library;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatMap {
	private static Map<String, DataOutputStream> userMap = new HashMap<>();
	
	public static void addUser(String id, DataOutputStream dos) {
		synchronized (userMap) {
			userMap.put(id, dos);
		}
	}
	
	public static void createRoom() {
		userMap = new HashMap<>();
	}
	
	public static void messageToAll(String message)	{
		synchronized (userMap) {
			for (DataOutputStream dos : userMap.values()) {
				try {
					dos.writeUTF(message);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void removeSocket(String id) {
		synchronized (userMap) {
			userMap.remove(id);
		}
	}
	//("님의 귓속말: ")앞에 id어케 붙이지 ㅠ
	public static void sendMessageToOne(String read) {
		int start = read.indexOf(" ") + 1;
		int end = read.indexOf(" ", start);
		
		if (start != -1 && end != -1) {
			String id = read.substring(start, end);
			String message = read.substring(end + 1);
			ChatMap.messageToOne(id, "님의 귓속말: " + message);
		}
	}
	
	private static void messageToOne(String id, String message)	{
		synchronized (userMap) {
			DataOutputStream dos = userMap.get(id);
			try {
				if (dos != null) {
					dos.writeUTF(message);
					dos.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
