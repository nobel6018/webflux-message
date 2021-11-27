package me.leedo.message.person.controller;

import me.leedo.message.person.controller.domain.Person;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {
    private ReactiveRedisOperations<String, Person> personOps;

    public PersonController(ReactiveRedisOperations<String, Person> personOps) {
        this.personOps = personOps;
    }


    @GetMapping("/person/{id}")
    public Mono<Person> person(@PathVariable long id) {
        return personOps.opsForValue().get("person:" + id);
    }

    @GetMapping("/persons")
    public Flux<Person> allPerson() {
        return personOps.keys("*")
            .flatMap(personOps.opsForValue()::get);
    }
}