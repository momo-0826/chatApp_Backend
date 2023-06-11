package chatApp.dto;

import lombok.Data;

@Data
public class FriendDto {
	private Long id;
	private Long userId;
	private Long friendUserId;
	private UserInfoDto friendUser;
}
