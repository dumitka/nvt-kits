package com.backend.springboot.service;

import com.backend.springboot.model.User;
import com.backend.springboot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class UserServiceUnitTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    SalaryService salaryService;

    @MockBean
    RoleService roleService;


    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Test
    public void TestRegisterUser() {
        User user = User.builder()
                .username("registered")
                .name("Name")
                .lastName("LastName")
                .password("123")
                .enabled(true)
                .fired(false)
                .build();

        when(userRepository.save(user)).thenReturn(User.builder()
                .username("registered")
                .name("Name")
                .lastName("LastName")
                .password("$2a$12$IVmf6KPQ/EYhtKpeTAjhuuLLo/xMmCQr2q.6CnWLzu5RvMVmejaDy")
                .enabled(true)
                .fired(false)
                .build());
        User registered = userService.registerUser(user, "ROLE_BARTENDER");

        verify(userRepository).save(user);
        assertEquals("Name", registered.getName());
        assertEquals("$2a$12$IVmf6KPQ/EYhtKpeTAjhuuLLo/xMmCQr2q.6CnWLzu5RvMVmejaDy", registered.getPassword());
    }


}
