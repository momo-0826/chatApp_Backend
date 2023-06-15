package chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	public List<Message> findBySenderIdAndRecipientIdIn(Long senderId, List<Long> recipientId);
}