package org.rdejana.springmvc.service.impl;

import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.model.Club;
import org.rdejana.springmvc.model.UserEntity;
import org.rdejana.springmvc.repository.ClubRepository;
import org.rdejana.springmvc.repository.UserRepository;
import org.rdejana.springmvc.security.SecurityUtil;
import org.rdejana.springmvc.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static org.rdejana.springmvc.mapper.ClubMapper.*;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        //this is wrong, we should not be updating the created by here
        //as this does bad things.
        Club orginalClub = clubRepository.findById(clubDto.getId()).get();

        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        UserEntity owner = orginalClub.getCreatedBy();
        if (!owner.getId().equals(user.getId())) {
           System.out.println("Wrong user...");
           //really need to handle here and not in the ui;
        }


        Club club = mapToClub(clubDto);


        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }
}