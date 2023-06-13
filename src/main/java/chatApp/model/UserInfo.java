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
	
	// ID
	@Id
	@Column(name="id")
	private Long id;
	
	// ユーザ名
	@Column(name="user_name")
	private String userName;
	
	// パスワード
	@Column(name="password")
	private String password;
	
	// メールアドレス
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Friend> friend1;
	
	@OneToMany(mappedBy = "friendUser", cascade = CascadeType.ALL)
	private List<Friend> friend2;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
}