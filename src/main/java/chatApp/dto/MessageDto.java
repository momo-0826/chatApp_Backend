package chatApp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {
	private Long id;
	private String senderId;
	private String recipientId;
	private String content;
	private LocalDateTime timestamp;
	private String status;
}
