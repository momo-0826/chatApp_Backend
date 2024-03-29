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
	// ID
	@Id
	@Column(name="id")
	private Long id;
	
	// 送信者のユーザID
	@ManyToOne
	@JoinColumn(name="sender_id")
	private UserInfo senderId;
	
	// 受信者のユーザID
	@ManyToOne
	@JoinColumn(name="recipient_id")
	private UserInfo recipientId;
	
	// メッセージの内容
	@Column(name="content")
	private String content;
	
	// 送付時間
	@Column(name="timestamp")
	private LocalDateTime timestamp;
	
	// ステータス(未読,既読の2パターン)
	@Column(name="status")
	private String status;
	
}