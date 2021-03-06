package com.bilgeadam.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserService {

    //@Autowired anotasyonu yerine constructor ile injection yapılması önerilmekte.
    private UserDAOService userDAOService;

    @Autowired
    public UserService(UserDAOService userDAOService) {
        this.userDAOService = userDAOService;
    }

    @GetMapping(path = "/users")
    public List<User> findAllUsers() {
        return userDAOService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User findUser(@PathVariable Integer id) {
        return userDAOService.findById(id);
    }

    //Önerilmiyor
    @PostMapping(path = "/users")
    public User createUserDirectly(@RequestBody User user) {
        return userDAOService.save(user);
    }

    //iyi yöntem
    @PostMapping(path = "/createUserAndReturn")
    public User createUserAndReturn(@RequestBody User user) {
        //status 200
        return new ResponseEntity<User> (userDAOService.save(user), HttpStatus.OK).getBody();
    }

    //en iyi yöntem
    @PostMapping(path = "/createUser")
    public void createUser(@RequestBody User user) {
        User newUser = userDAOService.save(user);
        URI resource = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((newUser.getId())).toUri();
        //status 201
        ResponseEntity.created(resource);
        // Bu yazım ile response header içerisinde oluşturulan kullanıcının kaynak bilgisi de yer alacaktır.
    }


    // İşler Ters Giderse Ne Yapılır
    // ÖRN: olmayan bir kaydı sorguladığınızda da successful(200) alırsınız ama body boş olur. Burada sizce bir terslik yok mu? Önce bu çözümlenmeli

}
