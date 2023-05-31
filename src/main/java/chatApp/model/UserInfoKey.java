package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

/**
 * ユーザ情報の主キー用
 */
@Data
public class UserInfoKey implements Serializable {
	
	// id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ユーザid
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// パスワード
	private String password;
	
	// email
	private String email;
}