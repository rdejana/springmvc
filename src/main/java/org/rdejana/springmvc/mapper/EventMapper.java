package org.rdejana.springmvc.mapper;

import org.rdejana.springmvc.dto.EventDto;
import org.rdejana.springmvc.model.Event;

public class EventMapper {

    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder().
                id(eventDto.getId()).
                name(eventDto.getName()).
                photoUrl(eventDto.getPhotoUrl()).
                startTime(eventDto.getStartTime()).
                endTime(eventDto.getEndTime()).
                type(eventDto.getType()).
                createdOn(eventDto.getCreatedOn()).
                updatedOn(eventDto.getUpdatedOn()).
                club(eventDto.getClub()).
                build();
    }

    public static EventDto mapToEventDto(Event eventDto) {
        return EventDto.builder().
                id(eventDto.getId()).
                name(eventDto.getName()).
                photoUrl(eventDto.getPhotoUrl()).
                startTime(eventDto.getStartTime()).
                endTime(eventDto.getEndTime()).
                type(eventDto.getType()).
                createdOn(eventDto.getCreatedOn()).
                updatedOn(eventDto.getUpdatedOn()).
                club(eventDto.getClub()).
                build();
    }
}
