package chatApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "friend")
@IdClass(value = FriendKey.class)
public class Friend {
	
	// ID
	@Id
	@Column(name="id")
	private Long id;
	
	// ユーザID(誰が誰の友達であるか、の'誰の'の方のユーザID)
	@ManyToOne
	@JoinColumn(name = "user1_id")
	private UserInfo user1;
	
	// ユーザID(誰が誰の友達であるか、の'誰が'の方のユーザID)
	@ManyToOne
	@JoinColumn(name = "user2_id")
	private UserInfo user2;

}
