package chatApp.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chatApp.domain.service.UserInfoService;
import chatApp.model.UserInfo;

// ユーザー情報に関するコントローラ
@RestController
@RequestMapping("UserInfo")
@CrossOrigin(origins = "http://localhost:8080")
public class UserInfoController {
	
	private final UserInfoService userInfoService;
	
	public UserInfoController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	
	// 登録ユーザー情報を取得
	@GetMapping("/get-user-info")
	public UserInfo getUserInfo(@RequestParam("name") String name, @RequestParam("password") String password){
		UserInfo res = userInfoService.getUserInfo(name, password);
		return res;
	}
	
	// ユーザー情報を作成
	@PostMapping("/user")
	public UserInfo createUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {
		UserInfo res = userInfoService.createUserInfo(userName, password, email);
		return res;
	}
	
	// 登録ユーザー情報を更新
	@PostMapping("/user/{id}")
	public UserInfo updateUser(@PathVariable(value = "id") Long id, @RequestBody UserInfo UserInfo) {
		return userInfoService.updateUserInfo(id, UserInfo);
	}
	
	// 登録ユーザー情報を削除
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable(value = "id") Long id,@RequestBody UserInfo UserInfo) {
		userInfoService.deleteUserInfo(id);
	}
	
}