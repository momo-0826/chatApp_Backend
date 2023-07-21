package chatApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
@IdClass(value = RolesKey.class)
public class Roles {
	
	// ID
	@Id
	@Column(name="id")
	private Long id;
	
	// ユーザ名
	@Column(name="name")
	private String name;

}
