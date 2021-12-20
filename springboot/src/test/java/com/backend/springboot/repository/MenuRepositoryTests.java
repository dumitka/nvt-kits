package com.backend.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.MenuConstants.ID_OF_CURRENT_MENU;

import com.backend.springboot.model.Menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MenuRepositoryTests {

	@Autowired
	MenuRepository menuRepository;
	
	
	@Test
	public void findByCurrent_CurrentExists_Menu() {
		Optional<Menu> current = menuRepository.findByCurrent();
		assertTrue(current.isPresent());
		assertEquals(ID_OF_CURRENT_MENU, current.get().getId());
	}
}
