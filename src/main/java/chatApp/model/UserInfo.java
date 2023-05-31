package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "userInfo")
@IdClass(value = UserInfoKey.class)
public class UserInfo implements Serializable {
	@Id
	@Column(name="id")
	private Long id;
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Id
	@Column(name="password")
	private String password;
	
	@Id
	@Column(name="email")
	private String email;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.userName = email;
	}
	
}