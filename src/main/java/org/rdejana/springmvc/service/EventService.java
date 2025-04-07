package org.rdejana.springmvc.service;

import jakarta.validation.Valid;
import org.rdejana.springmvc.dto.EventDto;
import org.rdejana.springmvc.model.Event;

import java.util.List;

public interface EventService {

    void create(Long clubId, EventDto event);


    List<EventDto> findAllEvents();

    EventDto findById(Long eventId);

    void updateEvents(EventDto eventDto);
}
