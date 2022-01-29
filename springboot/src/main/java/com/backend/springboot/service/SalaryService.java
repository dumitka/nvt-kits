package com.backend.springboot.service;

import com.backend.springboot.model.Salary;
import com.backend.springboot.model.User;
import com.backend.springboot.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Salary createNewSalary(User user, float amount) {
        Salary salary = Salary.builder()
                .amount(amount)
                .user(user)
                .dateOfValidation(LocalDateTime.now())
                .build();

        return save(salary);
    }

    public Salary getSalaryForUser(Integer userId) {//todo dates

        return salaryRepository.findOneByUserId(userId);
    }
}
