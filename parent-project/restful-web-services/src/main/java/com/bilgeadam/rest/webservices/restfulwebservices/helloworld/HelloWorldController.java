package com.bilgeadam.rest.webservices.restfulwebservices.helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {

    // GET
    // URI - /hello-world
    // method - "hello world"
    @GetMapping(path = "/hello-world")
    //ya da @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    // GET
    // URI - /hello-world
    // method - "hello world"
    @GetMapping(path = "/hello-world2")
    //ya da @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorldObjectToString() throws JsonProcessingException {
        //Json a çevirme örneği
        ExampleJSon json = new ExampleJSon("Erman", "ÖZARDIÇ", 35, 50000D);
        ObjectMapper person = new ObjectMapper();
        return person.writeValueAsString(json);
//        return "Hello World!";
    }

    /**
     * @GetMapping kullanımı
     */
    @GetMapping(path = "/hello-world-bean")
    public ExampleBean helloWorldBean() {
        return new ExampleBean("Erman", "ÖZARDIÇ", 35, 50000D);
    }

    /**
     * @PathVariable kullanımı tekli
     */
    @GetMapping(path = "/hello-world-bean/with-path-variable/{name}")
    public String helloWorldBean(@PathVariable String name) {
        return "Your Name is " + name;
    }

    /**
     * @PathVariable kullanımı
     */
    @GetMapping(path = "/hello-world-bean/with-path-variables/{name}/{surname}/{age}/{wage}")
    public ExampleBean helloWorldBean(@PathVariable String name, @PathVariable String surname, @PathVariable Integer age, @PathVariable Double wage) {
        return new ExampleBean(name, surname, age, wage);
    }

    // Hata yönetimi ve dispatcherServlet kullanımı. property dosyasına logging.level.org.springframework = debug detayda configuration ı incele

}
