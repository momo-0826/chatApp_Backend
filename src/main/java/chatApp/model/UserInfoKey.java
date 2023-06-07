package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/**
 * ユーザ情報の主キー用
 */
@Data
public class UserInfoKey implements Serializable {
	
	// id
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_seq")
    @SequenceGenerator(name = "user_info_seq", sequenceName = "users_id_seq", allocationSize = 1)
	private Long id;
}