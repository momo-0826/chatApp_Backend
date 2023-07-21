package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/**
 * 権限情報の主キー用
 */
@Data
public class UserRoleKey implements Serializable {
	
	// id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_info_seq")
    @SequenceGenerator(name = "user_role_info_seq", sequenceName = "user_role_id_seq", allocationSize = 1)
	private Long id;
	
}