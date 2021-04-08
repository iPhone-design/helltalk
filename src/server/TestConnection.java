package server;
import java.sql.SQLException;

public class TestConnection {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		try {
			dao.getConnection();
			
//			System.out.println(dao.addUser("test1", "test1", "테스트닉넴", 22));
//			System.out.println(dao.idCheck("test1")); // 존재하는 id일때 1출력
			System.out.println(dao.login("test1", "test1")); // 활성계정이면 마지막에 1출력
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
