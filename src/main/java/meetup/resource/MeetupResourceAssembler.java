package main.java.meetup.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import main.java.meetup.domain.Meetup;

@Component 
public class MeetupResourceAssembler extends ResourceAssembler<Meetup, MeetupResource> {
    
    @Autowired 
    protected EntityLinks entityLinks;

    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "delete";

    @Override
    public MeetupResource toResource(Meetup meetup) {

        MeetupResource meetupResource = new MeetupResource(meetup);

        final Link selfLink = entityLinks.linkToSingleResource(meetup);

        meetupResource.add(selfLink.withSelfRel());
        meetupResource.add(selfLink.withRel(UPDATE_REL));
        meetupResource.add(selfLink.withRel(DELETE_REL));

        return meetupResource;
    }
}