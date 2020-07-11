package com.bilgeadam.rest.webservices.restfulwebservices.exception;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//Burada bu sınıfın tüm restcontroller sınıflarında kullanılmasını istiyoruz.
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // class adı firma ismi veya daha farklı bir isimle özelleşebilir.
    // Burada jenerik olarak çalışan ve tüm uygulamada kullanılabilecek bir exception yapısı kurulması amaçlanmakta.

}
