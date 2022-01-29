package com.backend.springboot.repository;

import com.backend.springboot.constants.UserConstans;
import com.backend.springboot.model.Salary;
import com.backend.springboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static com.backend.springboot.constants.UserConstans.DB_USER_ID;
import static com.backend.springboot.constants.MenuConstants.NON_EXISTING_DATE_MENU;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class SalaryRepositoryTest {

    @Autowired
    private SalaryRepository salaryRepository;

    @Test
    public void findByUserIdTest() {
        List<Salary> all = salaryRepository.findByUserId(DB_USER_ID);
        assertEquals(all.size(), 2);
    }

    @Test
    public void findAllTest() {
        List<Salary> all = salaryRepository.findAll();
        assertEquals(all.size(), 4);
    }
}
