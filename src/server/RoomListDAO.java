package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.Room;

public class RoomListDAO {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/hell_db?characterEncoding=UTF-8";
	private static String ID = "root";
	private static String PASSWORD = "root";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager
				.getConnection(DB_URL, ID, PASSWORD);
		return conn;
	}
	
	public int addRoom(String title, String roomMasterName) {
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO roomlist (title, roomMasterName) VALUE (?, ?)")) {
			
			pstmt.setString(1, title);
			pstmt.setString(2, roomMasterName);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
//	public int idCheck(String id) {
//		int result = 0;
//		String query = "SELECT * FROM user WHERE id = ?";
//		try (Connection conn = getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(query)) {
//			pstmt.setString(1, id);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					result = 0; // 가입가능한 아이디일때
//					return result;
//				} else {
//					result = 1; // 이미 존재하는 아이디일때
//					return result;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}
	
	public List<Room> RoomlistAll() {
		List<Room> roomList = new ArrayList<>();
		int result = 0;
		String query = "SELECT * FROM roomlist";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String title = rs.getString("title");
					String roomMasterName = rs.getString("roomMasterName");
					int amount = rs.getInt("amount");
					roomList.add(new Room(title, roomMasterName, amount));
				}
			}
			return roomList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}