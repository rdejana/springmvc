package org.rdejana.springmvc.service;

import jakarta.validation.Valid;
import org.rdejana.springmvc.dto.EventDto;
import org.rdejana.springmvc.model.Event;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();
    EventDto findByEventId(Long eventId);
    void updateEvent(EventDto eventDto);
    void deleteEvent(long eventId);
}