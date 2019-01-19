package main.java.meetup.repository;

import org.springframework.stereotype.Repository;

import main.java.meetup.domain.Meetup;

@Repository
public class MeetupRepository extends InMemoryRepository<Meetup> {
    protected void updateIfExists(Meetup original, Meetup updated) {
        original.setName(updated.getName());
        original.setLocation(updated.getLocation());
        original.setDate(updated.getDate());
    }
}