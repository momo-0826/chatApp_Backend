package chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
	// 対象ユーザの友達であるユーザのIdを取得
	public List<Friend> findByUserId(Long id);
	
}