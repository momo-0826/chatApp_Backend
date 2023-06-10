package chatApp.dto;

import lombok.Data;

@Data
public class UserInfoDto {
	private Long id;
	private String userName;
	private String password;
	private String email;
}
