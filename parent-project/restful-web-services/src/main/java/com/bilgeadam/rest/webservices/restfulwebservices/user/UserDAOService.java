package com.bilgeadam.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


//springframework te managedBean olarak kullanıldığını ifade eder
@Component
//@Repository ifadesi kullanılarak bu bean in Database ile bağlı çalıştığı bildirilmiş olur.
public class UserDAOService {

    UserDAO userDAO;

    @Autowired
    public UserDAOService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDAO.findById(id);
    }

    public User save(User user) {
        return userDAO.save(user);
    }

    public User deleteById(Long id) {
        Optional<User> user = findById(id);
        if (user.isPresent()) {
            userDAO.delete(user.get());
            return user.get();
        }
        else {
            return null;
        }
    }


//    private static List<User> users = new ArrayList<>();
//    private static Integer usersCount = 3;
//
//    //geçici DB miz
//    static {
//        users.add(new User(1, "Ahmet", new Date()));
//        users.add(new User(2, "Mehmet", new Date()));
//        users.add(new User(3, "Fatma", new Date()));
//    }
//
//    public List<User> findAll() {
//        return users;
//    }
//
//    public User save(User user) {
//        if (user.getId() == null) {
//            user.setId(++usersCount);
//        }
//        users.add(user);
//        return user;
//    }
//
//    public User findById(Integer id) {
//        //OneLiner version
//        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
//        /*
//        for (User user: users) {
//            if (user.getId().equals(id))
//                return user;
//        }
//        return null;
//        */
//    }
//
//    public User deleteById(Integer id) {
//        //memory de arraylist kullandığımız için iterator kullanarak liste elemanı sildik.
//        //DB kullanılsaydı delete metodu ile sileceğimiz tablo ve id alanını map ederek bunu silebilirdik.
//        Iterator<User> iterator = users.iterator();
//        while(iterator.hasNext()) {
//            User user = iterator.next();
//            if (user.getId().equals(id)) {
//                iterator.remove();
//                return user;
//            }
//        }
//        return null;
//    }

}
