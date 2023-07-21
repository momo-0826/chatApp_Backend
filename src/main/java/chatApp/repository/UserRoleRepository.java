package chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	public List<UserRole> findAllById(Long id);
}