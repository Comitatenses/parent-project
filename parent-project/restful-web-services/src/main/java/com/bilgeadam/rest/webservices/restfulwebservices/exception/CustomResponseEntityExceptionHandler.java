package com.bilgeadam.rest.webservices.restfulwebservices.exception;

import com.bilgeadam.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


//Burada bu sınıfın tüm restcontroller sınıflarında kullanılmasını istiyoruz. bunu @ControllerAdvice ifadesi ile yaparız.
//@ControllerAdvice aslında bildiğimiz @Component anotasyonunun ExceptionHandling extend edenleri için kullandığımız özelleşmiş halidir.
//Benzeri örnekler DAO katmanlarına verilen @Repository servisinin ya da Business mantık işletilen @Service katmanı için kullanılır.
//Bu sınıf da controller olarak ifade edilmelidir çünkü geri dönüş olarak ayrıca bir response dönmekte.
@ControllerAdvice
@RestController
//Bu tür bir sınıf yaratmaya çoğu zaman spring in kendi exception sınıfları yeterli geleceğinden ihtiyacınız olmayabilir.
//Ancak spring scope unun dışında kalan bir exception dönüşüne ihtiyacınız varsa bu custom handler dan yazılması gerekli olur.
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // class adı firma ismi veya daha farklı bir isimle özelleşebilir.
    // Burada jenerik olarak çalışan ve tüm uygulamada kullanılabilecek bir exception yapısı kurulması amaçlanmakta.

    @ExceptionHandler(Exception.class) //extend edilen sınıftaki handleException metoduna benzer bir metod ekliyoruz.
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        //Json formatında ExceptionResponse sınıfını dönüyor
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) //extend edilen sınıftaki handleException metoduna benzer bir metod ekliyoruz.
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
        //Json formatında ExceptionResponse sınıfını dönüyor
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    // 400 Bad Request te alının hatanın detaylandırılmasını sağlayan custom metodu yazıyoruz.
    // Burada extend edilen sınıftaki metodu kendi yazılımımıza uygun olarak değiştireceğiz.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Burada exception detail bilgisi olarak User nesnesinin değerleri bind edilirken hangi fieldlarında(bind edilirken),
        // validasyonu takıldığını exception ın içerisine ekliyoruz.
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
