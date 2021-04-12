package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/hell_db?characterEncoding=UTF-8";
	private static String ID = "root";
	private static String PASSWORD = "root";
	// 모든 유저 기본 프로필이미지
	private File defaultUserImg = new File(".\\img\\defaultUser1.png"); 
	
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
	
	public int idCheck(String id) {
		int result = 0;
		String query = "SELECT * FROM user WHERE id = ?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 0; // 가입가능한 아이디일때
					return result;
				} else {
					result = 1; // 이미 존재하는 아이디일때
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("몬가 잘못됨.");
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
					int dbStatus = rs.getInt("status");
					
					System.out.println("입력한 아이디: " + id + ", 저장된 아이디: " + dbId);
					System.out.println("입력한 비밀번호: " + password + ", 저장된 비밀번호: " + dbPw);
					System.out.println("닉네임: " + rs.getString("nickname"));
					System.out.println("나이: " + rs.getInt("age"));
					System.out.println("유저상태(0:로그아웃, 1:로그인, 2:방장");
					System.out.println("↳" + dbStatus);
					if (id.equals(dbId) && !password.equals(dbPw)) {
						result = 2; // 비번틀리면 2 출력
						return result;
					} else if (id.equals(dbId) && password.equals(dbPw)) {
						result = 1; // id, password 일치할때 1 출력
						return result;
					}
				} else {
					result = 0; // 없는아이디일때 0출력
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("몬가 잘못됨.");
		}
		return -1;
	}
	
	
	public void insertImage() { // db에 이미지 저장하는 메소드
		String query = "INSERT INTO profile_img (id, filename, file) VALUES (?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(DB_URL,ID,PASSWORD);
				PreparedStatement pre = conn.prepareStatement(query);) {
			FileInputStream fis = new FileInputStream(defaultUserImg);
			
			pre.setInt(1,1);
			pre.setString(2,"default_user_img");
			pre.setBinaryStream(3,fis,(int)defaultUserImg.length()); //Stream형의 파일 업로드
			pre.executeUpdate();
			
			System.out.println("DB에 이미지 저장 완료!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("몬가 잘못됨.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("몬가 잘못됨.");
		}
	}
}