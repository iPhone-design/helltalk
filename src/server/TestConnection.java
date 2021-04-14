package server;
import java.sql.SQLException;

public class TestConnection {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		try {
			dao.getConnection();
			
			//addUser = 새로 추가됐을때 1 출력, 이미 있는 id일때 -1 출력
//			System.out.println(dao.addUser("moderator1", "moderator1", "테스트방장", 11));
			
//			System.out.println(dao.idCheck("test1")); // 존재하는 id일때 1출력
//			System.out.println(dao.idCheck("없는id")); // 없는 id일때는 0출력
			
//			System.out.println(dao.login("moderator1", "moderator1")); // id, password 맞으면 1출력
//			System.out.println(dao.login("test1", "틀린비번")); // 비번틀리면 2출력
//			System.out.println(dao.login("없는계정", "없는비번")); // 없는아이디일때 0출력
			
//			dao.insertImage(); // db에 이미지 저장테스트, 테스트할때 db에서 이미지 지우고 할것
//			dao.extractImage(); // db에 저장된 이미지 불러오기 테스트
			
//			System.out.println(dao.getUserData("1").toString()); // db에 저장된 id가 1이라는 유저의 칼럼들을 toString
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
