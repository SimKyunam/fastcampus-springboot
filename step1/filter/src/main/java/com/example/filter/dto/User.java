package com.example.filter.dto;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
