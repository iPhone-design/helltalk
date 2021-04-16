package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.User;

public class UserDAO {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/hell_db?characterEncoding=UTF-8";
	private static String ID = "root";
	private static String PASSWORD = "root";

	private static final int BUFFER_SIZE = 1024;

	// 모든 유저 기본 프로필이미지
	private File defaultUserImg = new File(".\\img\\defaultUser1.png");
	private User user;

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

	public int addUser(String id, String password, String nickname, int age) {
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO user" + " (userid, password, nickname, age)" + " VALUE (?, ?, ?, ?)")) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, nickname);
			pstmt.setInt(4, age);

			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int idCheck(String id) {
		int result = 0;
		String query = "SELECT * FROM user WHERE userid = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 1; // 가입불가
					return result;
				} else {
					result = 0; // 가입가능
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
		String query = "SELECT * FROM user WHERE userid = ?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String dbId = rs.getString("userid");
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
		}
		return -1;
	}

	public User getUserData(String id) { // 마이페이지, 프로필보기용 유저데이터 조회
		String sql = "SELECT * FROM user WHERE userid = ?";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String dbId = rs.getString("userid");
					String dbPw = rs.getString("password");
					String dbNn = rs.getString("nickname");
					int dbAge = rs.getInt("age");
					int dbstatus = rs.getInt("status");
					
					user = new User();
					user.setId(dbId);
					user.setPassword(dbPw);
					user.setNickname(dbNn);
					user.setAge(dbAge);
					user.setStatus(dbstatus);
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateUserData (String nickname, String password, String userid) {
		String query = "UPDATE user SET nickname = ?, password = ?"
				+ " WHERE userid = ?";
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, nickname);
			pstmt.setString(2, password);
			pstmt.setString(3, userid);
			int result = pstmt.executeUpdate(); 
			System.out.println("수정된 행 개수: " + result);
			return 5;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void insertImage() { // db에 이미지 저장하는 메소드
		String query = "INSERT INTO profile_img (userid, filename, image) VALUES (?, ?, ?)";
		String id = "id";
		String password = "password";
		String nickname = "nickname";
		
		String fileName; // 파일명

		try (Connection conn = DriverManager.getConnection(DB_URL, ID, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			FileInputStream fis = new FileInputStream(defaultUserImg);

			pstmt.setString(1, "1");
			pstmt.setString(2, "default_user_img");
			pstmt.setBinaryStream(3, fis, (int) defaultUserImg.length()); // Stream형의 파일 업로드
			pstmt.executeUpdate();

			System.out.println("DB에 이미지 저장 완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void extractImage() {
		String query = "SELECT * FROM profile_img WHERE userid = ?";

		String fileName = "user_img_";
		String id = "1";

		FileOutputStream fos = null;
		try (Connection conn = DriverManager.getConnection(DB_URL, ID, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			InputStream is = rs.getBinaryStream("image"); // 파라미터 = 칼럼이름
			fos = new FileOutputStream(".\\img\\" + fileName + "1.png"); // 저장될 경로와 파일이름
			byte[] byteArrays = new byte[BUFFER_SIZE * 4];
			int n;
			while ((n = is.read(byteArrays)) > 0) {
				fos.write(byteArrays, 0, n);
			}
			System.out.println("DB에서 이미지 불러오기 완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}