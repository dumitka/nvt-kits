package com.backend.springboot.repository;

import com.backend.springboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.backend.springboot.constants.UserConstans.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTests {
	@Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User found = userRepository.findByUsername(DB_USER_USERNAME);
        assertEquals(DB_USER_USERNAME, found.getUsername());
    }

    @Test
    public void testFindByFiredFalse() {
        List<User> firedEmployees = userRepository.findByFiredFalse();
        User user = firedEmployees.get(0);
        assertEquals(firedEmployees.size(), 2);
        assertEquals(DB_NOT_FIRED_USERNAME, user.getUsername());
        assertEquals(false, user.getFired());
    }

    @Test
    public void testSaveUser() {
        User user = User.builder()
                .username(DB_USER_NEW_USERNAME)
                .name(DB_USER_NAME)
                .lastName(DB_USER_LASTNAME)
                .password(DB_PASSWORD)
                .fired(false)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);
        assertNotNull(user);
        assertEquals(DB_USER_NEW_USERNAME, savedUser.getUsername());
        assertEquals(false, savedUser.getFired());
    }
}
