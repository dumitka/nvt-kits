package com.backend.springboot.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.UserConstans.DB_USER_USERNAME;
import com.backend.springboot.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTests {
	@Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername(){
       User found = userRepository.findByUsername(DB_USER_USERNAME);
       assertEquals(DB_USER_USERNAME, found.getUsername());
    	/*List<User> users = userRepository.findAll();
    	System.out.print("TACA");
    	for(User u: users) {
    		System.out.println(u.getUsername());
    	}*/
    }
}
