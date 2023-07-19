package chatApp.dto;

import chatApp.model.Roles;
import lombok.Data;

@Data
public class UserAuthDto {
	private Long id;
	private String userName;
	private String password;
	private String email;
	private Boolean enabled;
	private Roles role;
}
