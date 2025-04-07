package org.rdejana.springmvc.service.impl;

import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.model.Club;
import org.rdejana.springmvc.repository.ClubRepository;
import org.rdejana.springmvc.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static org.rdejana.springmvc.mapper.ClubMapper.*;
import static org.rdejana.springmvc.mapper.EventMapper.*;


@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public  List<ClubDto> findAllClubs(){
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    public Club saveClub(ClubDto clubDto) {
        return clubRepository.save(mapToClub(clubDto));

    }

   public  void updateClub(ClubDto club){
         clubRepository.save(mapToClub(club));
    }

    public ClubDto findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId).get();

        return mapToClubDto(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    public List<ClubDto> searchClubs(String query){
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }



}
