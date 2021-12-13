package org.example.javacodesnippets.json.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AvailableLaptops {
    private List<Laptop> laptops;
}
