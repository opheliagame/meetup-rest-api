package main.java.meetup.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.meetup.domain.Meetup;
import main.java.meetup.repository.MeetupRepository;
import main.java.meetup.resource.MeetupResource;
import main.java.meetup.resource.MeetupResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Meetup.class)
@RequestMapping(value = "/meetup", produces = "application/json")
public class MeetupController {
    @Autowired
    private MeetupRepository repository;

    @Autowired
    private MeetupResourceAssembler assembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<MeetupResource>> findAllMeetups() {
        List<Meetup> meetups = repository.findAll();
        return new ResponseEntity<>(assembler.toResourceCollection(meetups), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<MeetupResource> createMeetup(@RequestBody Meetup meetup) {
        Meetup createdMeetup = repository.create(meetup);
        return new ResponseEntity<>(assembler.toResource(meetup), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<MeetupResource> findMeetupById(@PathVariable Long id) {
		Optional<Meetup> meetup = repository.findById(id);

		if (meetup.isPresent()) {
			return new ResponseEntity<>(assembler.toResource(meetup.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		boolean wasDeleted = repository.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<MeetupResource> updateOrder(@PathVariable Long id, @RequestBody Meetup updatedMeetup) {
		boolean wasUpdated = repository.update(id, updatedMeetup);
		
		if (wasUpdated) {
			return findMeetupById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}