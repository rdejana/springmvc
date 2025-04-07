package org.rdejana.springmvc.repository;

import org.rdejana.springmvc.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByTitle(String title);

    @Query("SELECT c from Club c where c.title like CONCAT('%',:query,'%')")
    List<Club> searchClubs(@Param("query") String query);
}
