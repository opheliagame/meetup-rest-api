package main.java.meetup.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class IdGenerator {
    private AtomicLong nextId = new AtomicLong(1);

    public Long getNextId() {
        return nextId.getAndIncrement();
    }
}