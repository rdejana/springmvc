package org.rdejana.springmvc.rest;


import org.rdejana.springmvc.dto.EventDto;
import org.rdejana.springmvc.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventRestController {

    private EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/rest/v1/events")
    public List<EventDto>  eventList() {
        //I'd use a a different dto to prevent looping
            List<EventDto> events = eventService.findAllEvents();
            System.out.println("Number of events: " + events.size());
            for (EventDto event : events) {
                event.setClub(null);
                //System.out.println(event.getId() + " " + event.getName());
            }
            return events;
    }
}
