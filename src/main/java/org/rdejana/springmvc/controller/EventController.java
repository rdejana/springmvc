package org.rdejana.springmvc.controller;

import jakarta.validation.Valid;
import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.dto.EventDto;
import org.rdejana.springmvc.model.Event;
import org.rdejana.springmvc.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";

    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findById(eventId);
        model.addAttribute("event", eventDto);
        return "events-details";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        //really should try to find the club here
        //if we don't find it,..move along..
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);

        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute EventDto eventDto, BindingResult bindingResult, Model model) {
       if(bindingResult.hasErrors()) {

           model.addAttribute("event", eventDto);
           return "events-create";
       }

        eventService.create(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }


    @GetMapping("/events/{eventId}/edit")
    public String editEvent(@PathVariable Long eventId, Model model) {
        EventDto eventDto = eventService.findById(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable Long eventId,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
           model.addAttribute("event", eventDto);
            return "events-edit";
        }
        EventDto loadedDto = eventService.findById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(loadedDto.getClub());
        eventService.updateEvents(eventDto);
        return "redirect:/events";
    }
}
