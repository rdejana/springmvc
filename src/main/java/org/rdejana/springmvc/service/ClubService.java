package org.rdejana.springmvc.service;

import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.model.Club;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(Long clubId);
    void updateClub(ClubDto club);
    void delete(Long clubId);
    List<ClubDto> searchClubs(String query);
}
