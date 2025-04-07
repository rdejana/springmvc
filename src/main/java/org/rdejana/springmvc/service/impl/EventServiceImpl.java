package org.rdejana.springmvc.service.impl;

import org.rdejana.springmvc.dto.EventDto;
import org.rdejana.springmvc.model.Club;
import org.rdejana.springmvc.model.Event;
import org.rdejana.springmvc.repository.ClubRepository;
import org.rdejana.springmvc.repository.EventRepository;
import org.rdejana.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.rdejana.springmvc.mapper.EventMapper.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    public void create(Long clubId, EventDto eventDto){
        //this doesn't handle errors...
        try {
            Optional<Club> optionalClub = clubRepository.findById(clubId);
            if (optionalClub.isEmpty()) {
                //throw and exception..
            }

            Club club = clubRepository.findById(clubId).get();//really should handle
            Event event = mapToEvent(eventDto);
            event.setClub(club);
            eventRepository.save(event);
        }catch (RuntimeException throwable) {
            System.err.println("We are here and there");
            System.out.println(throwable.getMessage());
             throw throwable;
        }
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());

    }

    @Override
    public EventDto findById(Long eventId) {
        return mapToEventDto(eventRepository.findById(eventId).get());
    }

    @Override
    public void updateEvents(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }


}
