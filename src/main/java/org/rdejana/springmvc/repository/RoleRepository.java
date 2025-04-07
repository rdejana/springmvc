package org.rdejana.springmvc.repository;

import org.rdejana.springmvc.model.Role;
import org.rdejana.springmvc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
