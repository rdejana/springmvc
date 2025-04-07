package org.rdejana.springmvc.repository;

import org.rdejana.springmvc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
        UserEntity findByEmail(String email);
        UserEntity findByUsername(String username);
        UserEntity findFirstByUsername(String username);
}
