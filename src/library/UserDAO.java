package library;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/hell_db?characterEncoding=UTF-8";
	private static String ID = "root";
	private static String PASSWORD = "root";

	private static final int BUFFER_SIZE = 1024;

	// 모든 유저 기본 프로필이미지
	private File defaultUserImg = new File(".\\img\\defaultUser1.png");
	private User user;
	private Image image;
	private ImageFile imagefile;

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
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String dbId = rs.getString("userid");
					String dbPw = rs.getString("password");
					int dbStatus = rs.getInt("status");
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
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
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
	
	public int setEnterUserName(String roomName, String userName) { // 마이페이지, 프로필보기용 유저데이터 조회
		String sql = "INSERT INTO userlist(roomName, userName) VALUES(?, ?);";
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, roomName);
			pstmt.setString(2, userName);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<String> getEnterUserName(String roomName) { // 마이페이지, 프로필보기용 유저데이터 조회
		List<String> userList = new ArrayList<>();
		String sql = "SELECT userName FROM userlist WHERE roomName = ?";
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, roomName);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String userName = rs.getString("userName");
					System.out.println(userName);
					userList.add(userName);
				}
				return userList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int userDelete(String id) { // 마이페이지, 프로필보기용 유저데이터 조회
		String sql = "DELETE FROM user WHERE userid = ?";
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, id);
			int rs = pstmt.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 유저 정보 변경
	public int updateUserData (String userid, String password, String nickname) {
		String query = "UPDATE user SET nickname = ?, password = ? WHERE userid = ?";
		
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, nickname);
			pstmt.setString(2, password);
			pstmt.setString(3, userid);
			int result = pstmt.executeUpdate(); 
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 프로필 확인
	public User myProfile(String id) {
		String sql = "SELECT userid, nickname FROM user WHERE userid = ?";
		try (Connection conn = getConnection();	PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String dbId = rs.getString("userid");
					String dbNn = rs.getString("nickname");
					User user = new User(dbId, "null", dbNn);
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// DB 이미지 저장
	public void insertImage(String userid, String fileName, File file) {
		String query = "INSERT INTO profile_img (userid, filename, image) VALUES (?, ?, ?)";
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(query);) {
			FileInputStream fis = new FileInputStream(file);
			pstmt.setString(1, userid);
			pstmt.setString(2, userid + "_" + fileName);
			pstmt.setBinaryStream(3, fis, (int) file.length()); // Stream형의 파일 업로드
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// DB 이미지 다운로드
		public ImageFile extractImage(String userid) {
			String query = "SELECT filename, image FROM profile_img WHERE userid = ?";
			String fileName = null;
			Blob image = null;
			FileOutputStream fos = null;
			byte[] imageByte = null;
			try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(query);) {
				pstmt.setString(1, userid);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					fileName = rs.getString("filename");
					image = rs.getBlob("image");
					imageByte = image.getBytes(1, (int) image.length());
				}
				imagefile = new ImageFile(fileName, imageByte);
			} catch (SQLException e) {
				e.printStackTrace();
			} 			
			return imagefile;
		}
		
	// DB 이미지 이름 로드
	public String extractImageName(String userid) {
		String query = "SELECT filename FROM profile_img WHERE userid = ?";
		String fileName = null;
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				fileName = rs.getString("filename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 			
		return fileName;
	}

	// DB 이미지 변경
	public void updateImage(String userid, String fileName, File file) {
		String query = "UPDATE profile_img SET filename = ?, image = ? WHERE userid = ?";
		try (Connection conn = getConnection();PreparedStatement pstmt = conn.prepareStatement(query);) {
			FileInputStream fis = new FileInputStream(file);
			pstmt.setString(1, userid + "_" + fileName);
			pstmt.setBinaryStream(2, fis, (int) file.length()); // Stream형의 파일 업로드
			pstmt.setString(3, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}