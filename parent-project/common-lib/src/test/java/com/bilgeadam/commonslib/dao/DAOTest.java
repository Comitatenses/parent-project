package com.bilgeadam.commonslib.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Data
@NoArgsConstructor
public class DAOTest {
    @Autowired
    private ExampleDAO exampleDAO;

    @Test
    public void insertOne() {
        exampleDAO.save(new ExampleEntity(1L, "TEST"));
    }

    @Test
    public void getAll() {
        exampleDAO.findAll();
    }

}
