package chatApp.dto;

import java.util.List;

import lombok.Data;

// ユーザー情報、友達情報、メッセージを取得し、まとめて返却するためのDto
@Data
public class ChatInfoDto {
	private Long id;
	private UserInfoDto userInfo;
	private List<FriendDto> friends;
	private List<MessageDto> messages;
}
