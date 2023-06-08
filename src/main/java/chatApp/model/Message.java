package chatApp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "message")
@IdClass(value = MessageKey.class)
public class Message implements Serializable {
	@Id
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	private UserInfo senderId;
	
	@ManyToOne
	@JoinColumn(name="recipient_id")
	private UserInfo recipientId;
	
	@Column(name="content")
	private String content;
	
	@Column(name="timestamp")
	private LocalDateTime timestamp;
	
	@Column(name="status")
	private String status;
	
}