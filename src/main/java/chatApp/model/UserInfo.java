package chatApp.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@IdClass(value = UserInfoKey.class)
public class UserInfo implements Serializable {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
	private List<Friend> friend1;
	
	@OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
	private List<Friend> friend2;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.userName = email;
	}
	
}