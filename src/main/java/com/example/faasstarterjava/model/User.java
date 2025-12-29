package com.example.faasstarterjava.model;

import java.time.LocalDateTime;

public record User(String id, String name, String email, LocalDateTime registeredAt) {

}
