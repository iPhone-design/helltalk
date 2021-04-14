package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import library.LoginResult;
import library.User;
import library.UserRequest;

public class ServerSignUp {
	private UserDAO dao = new UserDAO();
	private UserRequest request = null;
	private Socket socket;

	public ServerSignUp(Socket socket) {
		this.socket = socket;
		while (true) {
			try {
				System.out.println("서버 로그인 확인 대기중");
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				request = (UserRequest) ois.readObject();
				LoginResult result = null;
				int idCheck = dao.idCheck(request.getUser().getId());
				int loginCheck = dao.login(request.getUser().getId(), request.getUser().getPassword());
				
				System.out.println(idCheck); // 체크용 출력
				
				if (request.getChoose() == 1) { // 회원가입
					if (idCheck == 1) { // 1 = 중복된 아이디 없음, 가입가능한 상태
						result = new LoginResult(LoginResult.OK);
						dao.addUser(request.getUser().getId(), request.getUser().getPassword(),
								request.getUser().getNickname(), request.getUser().getAge());
					} else if (idCheck == 0) { // 0 = 이미 존재하는 id, 해당id로는 가입불가능
						result = new LoginResult(LoginResult.ID_EXIST);
					}
				} else if (request.getChoose() == 0) { // 로그인창
					if (loginCheck == 2) { // 비번틀림
						result = new LoginResult(LoginResult.WRONG_PASSWORD);
					} else if (loginCheck == 1) { // 로그인 성공
						result = new LoginResult(LoginResult.OK);
						request.getUser().setStatus(1);
					} else if (loginCheck == 0) { // 존재하지 않는 ID입니다.
						result = new LoginResult(LoginResult.NOT_EXIST);
					}
				}
				System.out.println(result);
				oos.writeObject(result);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
