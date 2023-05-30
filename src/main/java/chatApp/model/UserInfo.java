package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
@Entity
@Table(name = "user")
@IdClass(value = UserInfoKey.class)
public class UserInfo implements Serializable {
	@Id
	@Column(name="id")
	private Long id;
	
	@Id
	@Column(name="user_id")
	private Long userId;
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Id
	@Column(name="password")
	private String password;
	
	@Id
	@Column(name="email")
	private String email;
	
}