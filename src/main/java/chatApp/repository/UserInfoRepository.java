package chatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	public UserInfo findByUserName(String userName);
	
	public UserInfo findByUserNameAndPassword(String userName, String password);
}