package me.leedo.message.person.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class PersonController {
    //    private ReactiveRedisOperations<String, Person> personOps;
    //
    //    public PersonController(ReactiveRedisOperations<String, Person> personOps) {
    //        this.personOps = personOps;
    //    }


    @GetMapping("/hello")
    public Flux<String> hello() {
        return Flux.just("Hello", "World");
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return Flux.fromStream(stream)
            .map(i -> Collections.singletonMap("value", i));
    }

    @GetMapping("/steam2")


    @PostMapping("/echo")
    Mono<String> echo(@RequestBody Mono<String> body) {
        return body.map(String::toUpperCase);
    }


    //    @GetMapping("/person/{id}")
    //    public Mono<Person> person(@PathVariable long id) {
    //        return personOps.opsForValue().get("person:" + id);
    //    }
    //
    //    @GetMapping("/persons")
    //    public Flux<Person> allPerson() {
    //        return personOps.keys("*")
    //            .flatMap(personOps.opsForValue()::get);
    //    }
}