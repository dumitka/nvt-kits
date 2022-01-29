package com.backend.springboot.repository;

import com.backend.springboot.model.Desk;
import com.backend.springboot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class DeskRepositoryTest {

    private final int DESK_ID = 1;

    @Autowired
    private DeskRepository deskRepository;


    @Test
    public void testFindOneByIdAndDeletedFalse() {
        Desk desk = deskRepository.findOneByIdAndDeletedFalse(DESK_ID);
        assertEquals(DESK_ID, desk.getId());
        assertEquals(false, desk.isDeleted());
    }

    @Test
    public void testFindByDeletedFalse() {
        List<Desk> desks = deskRepository.findByDeletedFalse();
        assertEquals(2, desks.size());
    }

}
