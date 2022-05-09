package org.blc.security.repository;

import java.util.Optional;

import org.blc.security.enums.ERole;
import org.blc.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByName(ERole name);
}
