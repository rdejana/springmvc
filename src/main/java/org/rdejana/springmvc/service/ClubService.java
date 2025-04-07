package org.rdejana.springmvc.service;

import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.model.Club;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    ClubDto findClubById(Long clubId);
    Club saveClub(ClubDto clubDto);
    void updateClub(ClubDto clubDto);
    void deleteClub(Long clubId);
    List<ClubDto> searchClubs(String query);
}
