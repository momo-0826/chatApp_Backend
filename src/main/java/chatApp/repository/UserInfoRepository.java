package chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
//	public Optional<UserInfo> findById(Long id);
	
	public List<UserInfo> findByIdIn(List<Long> idList);
	
	public UserInfo findByUserName(String userName);
	
	public UserInfo findByUserNameAndPassword(String userName, String password);
}