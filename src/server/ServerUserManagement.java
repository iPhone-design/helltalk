package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import library.LoginResult;
import library.UserRequest;

public class ServerUserManagement {
	private Socket socket;
	private UserDAO dao;
	
	public ServerUserManagement(Socket socket) {
		this.socket = socket;
		UserDAO dao = new UserDAO();
		UserRequest request = null;
		
		try {
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
			
			if (request.getChoose() == 2) {
				result = new LoginResult(LoginResult.GET_USERDATA);
				oos.writeObject(dao.getUserData("1"));
			}
			
			if (request.getChoose() == 3) {
				result = new LoginResult(LoginResult.UPDATE_USERDATA);
				oos.writeObject(dao.updateUserData("1a", "11", "1")); // 닉넴, 비번, id 순
			}
			
			System.out.println("result : " + result);
			oos.writeObject(result);
			oos.flush();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
