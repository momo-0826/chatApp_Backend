package chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import chatApp.dto.UserAuthDto;
import chatApp.model.UserInfo;

@Repository
public interface UserAuthRepository extends JpaRepository<UserInfo, Long> {
	@Query(value = "SELECT * FROM USERS U INNER JOIN USER_ROLE UR ON U.ID = UR.USER_ID INNER JOIN ROLES ROLE ON UR.ROLE_ID = ROLE.ID WHERE U.USERNAME = :name", nativeQuery = true)
	public List<UserAuthDto> selectUserAuth(@Param("name") String name);
}