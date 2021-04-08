package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
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
	
	public int addUser(String id
			, String password
			, String nickname
			, int age) {
		try (Connection conn = getConnection();
			PreparedStatement pstmt
			= conn.prepareStatement("INSERT INTO user"
					+ " (id, password, nickname, age)"
					+ " VALUE (?, ?, ?, ?)")) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, nickname);
			pstmt.setInt(4, age);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("몬가 잘못됨.");
		}
		return -1;
	}
	
	public int idCheck(String id) { // 아이디 중복여부>어차피 pk인데 필요한가?
		int result = 0;
		String query = "SELECT * FROM user WHERE id = ?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 0; // 이미 존재하는 아이디일때
					return result;
				} else {
					result = 1; // 가입가능한 아이디일때
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int login(String id, String password) {
		int result = 0;
		String query = "SELECT * FROM user WHERE id = ?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String dbId = rs.getString("id");
					String dbPw = rs.getString("password");
					int dbActive = rs.getInt("active");
					
					System.out.println("입력한 아이디: " + id + ", 저장된 아이디: " + dbId);
					System.out.println("입력한 비밀번호: " + password + ", 입력한 비밀번호: " + dbPw);
					System.out.println("휴면계정여부(0:휴면, 1:활성) : " + dbActive);
					if (id.equals(dbId) && !password.equals(dbPw)) {
						result = 2;
						return result;
					} else if (id.equals(dbId) && password.equals(dbPw) && dbActive == 0) {
						result = 3;
						return result;
					} else if (id.equals(dbId) && password.equals(dbPw) && dbActive == 1) {
						result = 1;
						return result;
					}
				} else {
					result = 0;
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}