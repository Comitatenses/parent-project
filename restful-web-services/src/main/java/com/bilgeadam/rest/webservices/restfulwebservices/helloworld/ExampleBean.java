package com.bilgeadam.rest.webservices.restfulwebservices.helloworld;

import lombok.Data;

@Data
public class ExampleBean {

    private String name;
    private String surname;
    private Integer age;
    private Double wage;

    public ExampleBean(String name, String surname, Integer age, Double wage) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.wage = wage;
    }
}
