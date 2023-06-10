package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/**
 * 友達情報の主キー用
 */
@Data
public class FriendKey implements Serializable {
	
	// id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_info_seq")
    @SequenceGenerator(name = "friend_info_seq", sequenceName = "friend_id_seq", allocationSize = 1)
	private Long id;
	
}