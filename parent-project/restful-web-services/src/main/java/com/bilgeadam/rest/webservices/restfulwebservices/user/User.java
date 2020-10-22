package com.bilgeadam.rest.webservices.restfulwebservices.user;

import com.bilgeadam.commonslib.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User extends BaseEntity {

    // javax.validation, minimum 2 karakterden oluşmalı validasyonu
    @Size(min = 2, message = "İsim en az 2 karakterden oluşmalı")
    private String name;

    // javax.validation, geçmişte olma validasyonu
    @Past(message = "Tarih geçmişte olmalıdır.")
    private Date birthDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public User(Long id, String name, Date birthDate) {
        setId(id);
        this.name = name;
        this.birthDate = birthDate;
    }
}
