package chatApp.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "message")
@IdClass(value = MessageKey.class)
public class Message implements Serializable {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="sender_id")
	private String senderId;
	
	@Column(name="recipient_id")
	private String recipientId;
	
	@Column(name="content")
	private String content;
	
	@Column(name="timestamp")
	private String timestamp;
	
	@Column(name="status")
	private String status;
	
}