package chatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
	public Friend findByUserName(String userName);
	
}