package org.rdejana.springmvc.repository;

import org.rdejana.springmvc.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
