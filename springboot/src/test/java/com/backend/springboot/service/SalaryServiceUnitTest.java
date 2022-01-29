package com.backend.springboot.service;

import com.backend.springboot.model.Salary;
import com.backend.springboot.repository.SalaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static com.backend.springboot.constants.SalaryConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalaryServiceUnitTest {

    @MockBean
    private SalaryRepository repository;

    @Autowired
    private SalaryService service;

    @Test
    public void createNewSalary_ok_returnSalary() {
        when(repository.save(any())).thenReturn(SALARY);
        Salary salary = service.createNewSalary(USER, AMOUNT);

        verify(repository).save(any());
        assertEquals(AMOUNT, salary.getAmount());
    }
}
