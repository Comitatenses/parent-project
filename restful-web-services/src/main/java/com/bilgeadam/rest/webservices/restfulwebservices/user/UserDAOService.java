package com.bilgeadam.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//springframework te managedBean olarak kullanıldığını ifade eder
@Component
//@Repository ifadesi kullanılarak bu bean in Database ile bağlı çalıştığı bildirilmiş olur.
public class UserDAOService {

    private static List<User> users = new ArrayList<>();
    private static Integer usersCount = 3;

    //geçici DB miz
    static {
        users.add(new User(1, "Ahmet", new Date()));
        users.add(new User(2, "Mehmet", new Date()));
        users.add(new User(3, "Fatma", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findById(Integer id) {
        //OneLiner version
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        /*
        for (User user: users) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
        */
    }



}
