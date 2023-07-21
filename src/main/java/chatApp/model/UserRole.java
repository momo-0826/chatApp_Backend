package chatApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_role")
@IdClass(value = UserRoleKey.class)
public class UserRole {
	
	// ID
	@Id
	@Column(name="id")
	private Long id;
	
	// ユーザID
	@Column(name="user_id")
	private Long userId;
	
	// ロールID
	@Column(name="role_id")
	private Long roleId;

}
