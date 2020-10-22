package com.bilgeadam.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


//Bu sınıf http tarafından gelen requestlerin karşılandığı alan. User servisine yapılan çağrılar buradan karşılanmakta.
//Sınıf adlandırmada Controller türündeki sınıflar bazı projelerde Resource olarak da adlandırılabilir.
//Servisler arası iletişim için Controller sınıflar kullanılır.
//Controller ifadesi aslında REST için MVC deki frontend deki controller kısmına karşılık geldiğinden bu şekilde ifade edilmekte.
//@Component -> @Controller( == @Component, @RequestMapping ile kullanıldığını ifade eder) -> @RestController(@Controller+@ResponseBody)
//@RestController ise @Controller'ın kullanacağı @RequestMapping ifadelerinin @ResponseBody ifadelerini de içereceğini varsayar. Özet: Tamamiyle Rest e uygun controller dır.
@RestController
public class UserServiceController {

    //@Autowired anotasyonu yerine constructor ile injection yapılması önerilmekte.
    private UserDAOService userDAOService;

    @Autowired
    public UserServiceController(UserDAOService userDAOService) {
        this.userDAOService = userDAOService;
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/users")
    @GetMapping(path = "/users")
    public List<User> findAllUsers() {
        return userDAOService.findAll();
    }

/*

    @GetMapping(path = "/users/{id}")
    public User findUser(@PathVariable Integer id) {
        return userDAOService.findById(id);
    }
*/

    @GetMapping(path = "/users/{id}")
//    public User findUser(@PathVariable Integer id) {
    public EntityModel<User> findUser(@PathVariable Long id) {
        Optional<User> user = userDAOService.findById(id);
        if (!user.isPresent())
            throw new  UserNotFoundException("id: " + id);

        // Buraya HATEOAS eklenecek. Burada tekil bir sorgulama yapıldığı zaman tüm kayıtları getiren Resource linki ekleneek /users
        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        //
        EntityModel<User> resource = EntityModel.of(user.get());

        // methodOn(Class<T> controller) kısmını kullanarak controller metodlarını dummy(yalnızca linkini) alabilecek kadar mock eder.
        // linkTo static metodu ile de link kısmını alarak WebMvcLinkBuilder sınıfına uygun olarak oluşturur.
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).findAllUsers());

        resource.add(linkTo.withRel("all-users"));

        // EntityModel wrapper sınıfı User sınıfını kendine dahil ederek eklenecek linki de kaynak olarak body message a ekleyecektir.
        // Eski metod User nesnesi dönüyorken artık EntityModel<User> türünde nesne dönmelidir.
        return resource;
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
        return new ResponseEntity<User>(userDAOService.save(user), HttpStatus.OK).getBody();
    }

    //en iyi yöntem
    //@Valid anotasyonu eklenerek kullanıcıdan gelen validasyon uyarılarını 400 Bad Request olarak dönülmesini sağlar.

    /* Ancak bu durumda 400 mesajı sonrası kullanıcıyı bilgilendiren bir hata mesajı oluşmayacaktır. Tüketen tarafı
    bilgilendirmek için CustomExceptionHandler a hangi alanın yanlış olduğunu ifade eden custom bir exception ekleyerek problemi çözebiliriz.
    */
    /*Not: Validation API den daha kapsamlı olarak Validasyon özelliklerini içeren kütüphane Hibernate-Validator API ıdır.
     Bu ikisi spring-boot-web-starter dependency dahilinde projede mevcuttur.
     */
    @PostMapping(path = "/createUser")
    public void createUser(@Valid @RequestBody User user) {
        User newUser = userDAOService.save(user);
        URI resource = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand((newUser.getId()))
                .toUri();
        //status 201
        ResponseEntity.created(resource).build();
        // Bu yazım ile response header içerisinde oluşturulan kullanıcının kaynak bilgisi de yer alacaktır.
    }


    // İşler Ters Giderse Ne Yapılır
    // ÖRN: olmayan bir kaydı sorguladığınızda da successful(200) alırsınız ama body boş olur. Burada sizce bir terslik yok mu? Önce bu çözümlenmeli
    @GetMapping(path = "/usersNotFound/{id}")
    public User findUserThrowException(@PathVariable Long id) {
        Optional<User> user = userDAOService.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id:" + id);
        return user.get();
    }

    //Eğer silinecek kayıt bulunmadıysa UserNotFoundExp fırlatır. Eğer kayıt silindi ise DeleteMapping Status.OK (200) döner.
    @DeleteMapping(path = "/users/{id}") // POSTMAN  DELETE + /users/{id}
    public void deleteUser(@PathVariable Long id) {
        User user = userDAOService.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);
    }

}
