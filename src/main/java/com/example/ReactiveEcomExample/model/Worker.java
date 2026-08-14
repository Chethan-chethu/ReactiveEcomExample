package com.example.ReactiveEcomExample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table("worker")
@Getter
@Setter
@NoArgsConstructor
public class Worker {
    @Id
    private Long id;

    private String name;

    private String email;

    private Integer age;
}
