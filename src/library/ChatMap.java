package library;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatMap {
	private static Map<String, Map<String, DataOutputStream>> roomMap = new HashMap<>();
	
	public static void enterUser(String roomName, String id, DataOutputStream dos) {
		synchronized (roomMap) {
			roomMap.get(roomName).put(id, dos);
		}
	}
	
	public static void createRoom(String roomName) {
		synchronized (roomMap) {
			Map<String, DataOutputStream> userMap = new HashMap<>();
			roomMap.put(roomName, userMap);
		}
	}
	
	public static void messageToAll(String roomName, String message)	{
		synchronized (roomMap) {
			for (DataOutputStream dos : roomMap.get(roomName).values()) {
				try {
					dos.writeUTF(message);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void removeUser(String roomName, String id) {
		synchronized (roomMap) {
			roomMap.get(roomName).remove(id);
		}
	}
	
	public static void removeRoom(String roomName) {
		synchronized (roomMap) {
			roomMap.remove(roomName);
		}
	}
	//("님의 귓속말: ")앞에 id어케 붙이지 ㅠ
	public static void sendMessageToOne(String roomName, String id, String read) {
		int start = read.indexOf(" ") + 1;
		int end = read.indexOf(" ", start);
		
		if (start != -1 && end != -1) {
			String targetId = read.substring(start, end);
			String message = read.substring(end + 1);
			ChatMap.messageToOne(roomName, targetId, id + " 님의 귓속말: " + message);
		}
	}
	
	private static void messageToOne(String roomName, String targetId, String message)	{
		synchronized (roomMap) {
			DataOutputStream dos = roomMap.get(roomName).get(targetId);
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
