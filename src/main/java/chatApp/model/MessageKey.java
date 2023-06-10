package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/**
 * メッセージの主キー用
 */
@Data
public class MessageKey implements Serializable {
	
	// id
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_info_seq")
    @SequenceGenerator(name = "messages_info_seq", sequenceName = "messages_id_seq", allocationSize = 1)
	private Long id;
}