package com.backend.springboot.dtoTransformation;

import com.backend.springboot.dto.CreateUpdateUserDto;
import com.backend.springboot.dto.SalaryDto;
import com.backend.springboot.dto.UserDto;
import com.backend.springboot.model.Salary;
import com.backend.springboot.model.User;
import com.backend.springboot.repository.RoleRepository;
import com.backend.springboot.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    public User convertCreateUpdateUserDtoToUser(CreateUpdateUserDto userDto) {
        return User.builder()
                .enabled(true)
                .fired(false)
                .username(userDto.getUsername())
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .roles(Arrays.asList(roleRepository.findOneByName(userDto.getRoleName())))
                .build();
    }

    public UserDto convertUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .roleName(user.getRoles().get(0).getName())
                .salary(extractCurrentSalary(user.getId()))
                .build();
    }

    private List<Salary> initSalaries(float amount) {
        Salary salary = Salary.builder()
                .amount(amount)
                .dateOfValidation(LocalDateTime.now())
                .build();
        return Arrays.asList(salary);
    }

    private float extractCurrentSalary(Integer userId) {

        List<Salary> salaries = salaryRepository.findByUserId(userId).stream()
                .sorted(Comparator.comparing(salary -> salary.getDateOfValidation()))
                .collect(Collectors.toList());
        if (salaries.isEmpty()) {
            return 0;
        }

        return salaries.get(0).getAmount();
    }

    public SalaryDto convertSalaryToSalaryDto(Salary salary) {
        return SalaryDto.builder()
                .id(salary.getId())
                .userId(salary.getUser().getId())
                .localDateTime(salary.getDateOfValidation())
                .amount(salary.getAmount())
                .build();
    }
}
