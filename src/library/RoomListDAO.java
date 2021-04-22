package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		Connection conn = DriverManager.getConnection(DB_URL, ID, PASSWORD);
		return conn;
	}
	
	public int createRoom(String title, String roomMasterName, int headCount) {
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO roomlist (title, roomMasterName, headcount) VALUE (?, ?, ?)")) {
			pstmt.setString(1, title);
			pstmt.setString(2, roomMasterName);
			pstmt.setInt(3, headCount);
			int result = pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
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
					int headCount = rs.getInt("headcount");
					roomList.add(new Room(title, roomMasterName, headCount));
				}
			}
			return roomList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}