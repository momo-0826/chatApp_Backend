package chatApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chatApp.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	public Optional<Roles> findById(Long id);
	
	public List<Roles> findByIdIn(List<Long> id);
}