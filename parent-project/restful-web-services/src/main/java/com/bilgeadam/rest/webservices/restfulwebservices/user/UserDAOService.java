package com.bilgeadam.rest.webservices.restfulwebservices.user;

import com.bilgeadam.commonslib.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;


//springframework te managedBean olarak kullanıldığını ifade eder
//@Component
@Repository //ifadesi kullanılarak bu bean in Database ile bağlı çalıştığı bildirilmiş olur.
public class UserDAOService extends BaseService<User, UserDAO> implements UserDAOServiceCustom{

}
