package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

/**
 * 友達情報の主キー用
 */
@Data
public class FriendKey implements Serializable {
	
	// id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ユーザid
	private UserInfo user1;
	
	// ユーザ名
	private UserInfo user2;
	
}