package com.example.ReactiveEcomExample.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ReactorService {

    // -------------------------
    // Mono Examples
    // -------------------------

    public Mono<String> monoJust() {
        return Mono.just("Hello Reactor");
    }

    public Mono<String> emptyMono() {
        return Mono.empty();
    }

    public Mono<String> errorMono() {
        return Mono.error(new RuntimeException("Something went wrong"));
    }

    public Mono<String> neverMono() {
        return Mono.never();
    }

    public Mono<String> deferMono() {
        return Mono.defer(() ->
                Mono.just("Created at : " + System.currentTimeMillis()));
    }

    public Mono<String> fromCallable() {
        return Mono.fromCallable(() -> {
            Thread.sleep(2000);
            return "Loaded from Callable";
        });
    }

    // -------------------------
    // Flux Examples
    // -------------------------

    public Flux<String> fluxJust() {
        return Flux.just("Java", "Spring", "Reactor");
    }

    public Flux<String> fromIterable() {
        return Flux.fromIterable(
                List.of("Apple", "Banana", "Orange"));
    }

    public Flux<Integer> range() {
        return Flux.range(1, 10);
    }

    public Flux<Integer> generate() {

        return Flux.generate(
                () -> 1,
                (state, sink) -> {

                    sink.next(state);

                    if (state == 5) {
                        sink.complete();
                    }

                    return state + 1;
                });
    }

    public Flux<String> create() {

        return Flux.create(sink -> {

            sink.next("First");

            sink.next("Second");

            sink.next("Third");

            sink.complete();

        });
    }

    public Flux<Long> interval() {

        return Flux.interval(Duration.ofSeconds(1))
                .take(10);

    }

}
