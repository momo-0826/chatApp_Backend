package chatApp.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chatApp.domain.service.ChatInfoService;
import chatApp.dto.ChatInfoDto;

// チャット情報に関するコントローラ
@RestController
@RequestMapping("ChatInfo")
@CrossOrigin(origins = "http://localhost:8080")
public class ChatInfoController {
	
	private final ChatInfoService chatInfoService;
	
	public ChatInfoController(ChatInfoService chatInfoService) {
		this.chatInfoService = chatInfoService;
	}
	
	
	// ユーザー情報、友達情報、メッセージを取得し、まとめて返却する
	@GetMapping("/get-chat-info")
	public ChatInfoDto getChatInfo(@RequestParam("name") String name, @RequestParam("password") String password){
		ChatInfoDto res = chatInfoService.getChatInfo(name, password);
		return res;
	}
	
}