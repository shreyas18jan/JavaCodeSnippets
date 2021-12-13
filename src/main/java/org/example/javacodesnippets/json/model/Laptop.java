package org.example.javacodesnippets.json.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Laptop {
    private String name;
    private Double cost;
    private Boolean isRefurbished;
}
