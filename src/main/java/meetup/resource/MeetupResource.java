package main.java.meetup.resource;

import org.springframework.hateoas.ResourceSupport;
import java.util.Date;

import main.java.meetup.domain.Meetup;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeetupResource extends ResourceSupport{
    private final long id;
    private final String name;
    private final String location;
    private final Date date;

    public MeetupResource(Meetup meetup) {
        id = meetup.getId();
        name = meetup.getName();
        location = meetup.getLocation();
        date = meetup.getDate();
    }

    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }
}