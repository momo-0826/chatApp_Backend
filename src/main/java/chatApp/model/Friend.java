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
	
	@Id
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user1_id")
	private UserInfo user1;
	
	@ManyToOne
	@JoinColumn(name = "user2_id")
	private UserInfo user2;

}
