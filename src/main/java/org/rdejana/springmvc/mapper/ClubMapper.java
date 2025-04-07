package org.rdejana.springmvc.mapper;

import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.model.Club;

import java.util.stream.Collectors;

import static org.rdejana.springmvc.mapper.EventMapper.*;

public class ClubMapper {

    public static Club mapToClub(ClubDto clubDto){
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn()).build();
    }

    public static ClubDto mapToClubDto(Club club){

        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();

        //return clubDto;
    }
}
