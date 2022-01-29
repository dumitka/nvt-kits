package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.dto.CreateUpdateUserDto;
import com.backend.springboot.dto.UserDto;
import com.backend.springboot.dto.UserProfileDataDTO;
import com.backend.springboot.dtoTransformation.UserMapper;
import com.backend.springboot.model.User;
import com.backend.springboot.service.SalaryService;
import com.backend.springboot.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> employees = userService.getAllEmployees().stream()
                .map(user -> userMapper.convertUserToUserDto(user))
                .collect(Collectors.toList() );

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/waiters")
    @PreAuthorize("hasRole('WAITER')")
    public ResponseEntity<List<String>> getWaiterUsernames() {
    	List<String> usernames = new ArrayList<String>();
    	
    	for (User user : userService.getAllEmployees()) {
			List<String> roles = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
			if (roles.contains("ROLE_WAITER")) {
				usernames.add(user.getUsername());
			}
		}

    	return new ResponseEntity<>(usernames, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUpdateUserDto createUserDto) {
        //validacija, nullchecks
        if(userService.findByUsername(createUserDto.getUsername()) != null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        User user = userMapper.convertCreateUpdateUserDtoToUser(createUserDto);

        user = userService.registerUser(user);

        salaryService.createNewSalary(user, createUserDto.getSalary());

        return new ResponseEntity<>(userMapper.convertUserToUserDto(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody CreateUpdateUserDto updateUserDto) {
        //validacija, nullchecks

        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user = userService.updateUser(user, updateUserDto);

        return new ResponseEntity<>(userMapper.convertUserToUserDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
        User deletedUser = userService.deleteEmployee(id);
        if (deletedUser == null) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping(value = "/info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserProfileDataDTO> getPerson() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfileDataDTO dto = new UserProfileDataDTO(loggedUser.getId(), loggedUser.getName(), loggedUser.getLastName());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
