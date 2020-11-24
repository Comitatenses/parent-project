package com.bilgeadam.rest.webservices.restfulwebservices.user;

import java.util.List;

public interface UserDAOServiceCustom {
    List<User> findUserByFirstLetter(String letter);
}
