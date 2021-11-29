package me.leedo.message;

import me.leedo.message.study2.controller.SimpleHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(
            RequestPredicates.GET("/"),
            request -> ServerResponse.ok().body(Flux.just("Hello", "World"), String.class)
        );
    }

    @Bean
    RouterFunction<ServerResponse> study2Routes(SimpleHandler simpleHandler) {
        return RouterFunctions.route(
            RequestPredicates.POST("/study2/echo"),
            simpleHandler::echo
        );
    }

}
