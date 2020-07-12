package com.bilgeadam.rest.webservices.restfulwebservices.user;

import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {
    private Integer id;

    // javax.validation, minimum 2 karakterden oluşmalı validasyonu
    @Size(min = 2, message = "İsim en az 2 karakterden oluşmalı")
    private String name;

    // javax.validation, geçmişte olma validasyonu
    @Past(message = "Tarih geçmişte olmalıdır.")
    private Date birthDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
