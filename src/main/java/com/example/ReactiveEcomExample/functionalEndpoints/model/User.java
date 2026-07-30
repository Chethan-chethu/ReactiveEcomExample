package com.example.ReactiveEcomExample.functionalEndpoints.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User {
	private String userId;
	private String userName;
}
