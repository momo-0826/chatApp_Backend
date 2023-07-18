package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/**
 * ユーザの権限情報の主キー用
 */
@Data
public class RolesKey implements Serializable {
	
	// id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_info_seq")
    @SequenceGenerator(name = "role_info_seq", sequenceName = "role_id_seq", allocationSize = 1)
	private Long id;
	
}