package chatApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "frined")
@IdClass(value = FriendKey.class)
public class Friend {
	
	@Id
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	private UserInfo user1;
	
	@ManyToOne
	private UserInfo user2;

}
