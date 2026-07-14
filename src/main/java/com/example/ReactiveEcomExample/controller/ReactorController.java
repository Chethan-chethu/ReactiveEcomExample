package com.example.ReactiveEcomExample.controller;

import com.example.ReactiveEcomExample.service.ReactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactor")
public class ReactorController {

    private final ReactorService service;

    // Mono

    @GetMapping("/mono/just")
    public Mono<String> monoJust() {
        return service.monoJust();
    }

    @GetMapping("/mono/empty")
    public Mono<String> emptyMono() {
        return service.emptyMono();
    }

    @GetMapping("/mono/error")
    public Mono<String> errorMono() {
        return service.errorMono();
    }

    @GetMapping("/mono/never")
    public Mono<String> neverMono() {
        return service.neverMono();
    }

    @GetMapping("/mono/defer")
    public Mono<String> deferMono() {
        return service.deferMono();
    }

    @GetMapping("/mono/callable")
    public Mono<String> callableMono() {
        return service.fromCallable();
    }

    // Flux

    @GetMapping("/flux/just")
    public Flux<String> fluxJust() {
        return service.fluxJust();
    }

    @GetMapping("/flux/iterable")
    public Flux<String> iterable() {
        return service.fromIterable();
    }

    @GetMapping("/flux/range")
    public Flux<Integer> range() {
        return service.range();
    }

    @GetMapping("/flux/generate")
    public Flux<Integer> generate() {
        return service.generate();
    }

    @GetMapping("/flux/create")
    public Flux<String> create() {
        return service.create();
    }

    @GetMapping(value="/flux/interval",
            produces = "text/event-stream")
    public Flux<Long> interval() {
        return service.interval();
    }

}
