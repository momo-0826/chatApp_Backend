package chatApp.domain.service;

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
	
//	// 友達情報返却
//	public List<UserInfo> getFriendInfo(String usrName, String password){
//		List<UserInfo> res = userInfoRepository.findByNamePass(usrName, password);
//		return res;
//	}
	
	// 登録ユーザ情報作成
	public UserInfo createUserInfo(String userName, String password, String email) {
		
		// 登録したいユーザ名が既存のユーザ名と重複しているか確認
		UserInfo existingUser = userInfoRepository.findByUserName(userName);
		
		UserInfo newUSer = new UserInfo(userName, password, email);
		
		return userInfoRepository.save(newUSer);
	}
	
	// 登録ユーザ情報更新
	public UserInfo updateUserInfo(int id, UserInfo userInfo) {
		UserInfo saveUser = userInfoRepository.findById(id).get();
		saveUser.setUserName(userInfo.getUserName());
		saveUser.setEmail(userInfo.getEmail());
		saveUser.setPassword(userInfo.getPassword());
		
		return userInfoRepository.save(userInfo);
	}
	
	// 登録ユーザ情報削除
	public void deleteUserInfo(Integer id) {
		userInfoRepository.deleteById(id);
	}
}