package chatApp.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chatApp.model.UserInfo;
import chatApp.repository.UserInfoRepository;

@Service
public class UserInfoService {
	private final UserInfoRepository userInfoRepository;
	
	public UserInfoService(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}
	
	// 登録ユーザ情報返却
	public UserInfo getUserInfo(String usrName, String password) {
		return userInfoRepository.findByUserNameAndPassword(usrName, password);
	}
	
	// 登録ユーザ情報作成
	public UserInfo createUserInfo(String userName, String password, String email) {
		
		// 登録したいユーザ名が既存のユーザ名と重複しているか確認
		UserInfo existingUser = userInfoRepository.findByUserName(userName);
		if (existingUser != null) {
			return new UserInfo();
		}
		
		// パスワードをハッシュ化
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncorder.encode(password);
		System.out.println("password");
		System.out.println(password);
		System.out.println("hashedPassword");
		System.out.println(hashedPassword);
		UserInfo newUser = new UserInfo(userName, hashedPassword, email);
		
		return userInfoRepository.save(newUser);
	}
	
	// 登録ユーザ情報更新
	public UserInfo updateUserInfo(Long id, UserInfo userInfo) {
		UserInfo saveUser = userInfoRepository.findById(id).get();
		saveUser.setUserName(userInfo.getUserName());
		saveUser.setEmail(userInfo.getEmail());
		saveUser.setPassword(userInfo.getPassword());
		
		return userInfoRepository.save(userInfo);
	}
	
	// 登録ユーザ情報削除
	public void deleteUserInfo(Long id) {
		userInfoRepository.deleteById(id);
	}
}