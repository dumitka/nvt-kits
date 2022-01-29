package com.backend.springboot.service;


import com.backend.springboot.model.Desk;
import com.backend.springboot.repository.DeskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.backend.springboot.model.Drink;
import com.backend.springboot.model.DrinkPrice;
import com.backend.springboot.repository.DrinkPriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.backend.springboot.constants.DrinkPriceConstrants.*;
import static com.backend.springboot.constants.DrinkConstants.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static com.backend.springboot.constants.DeskConstants.*;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeskServiceUnitTest {

    @MockBean
    private DeskRepository deskRepository;

    @Autowired
    private DeskService deskService;

    @Test
    public void findAll_ok_returnDesks (){
        when(deskRepository.findByDeletedFalse()).thenReturn(Arrays.asList(DESK));

        List<Desk> desks= deskService.findAll();

        assertEquals(desks.size(), 1);
    }

    @Test
    public void findOne_ok_returnDesk (){
        when(deskRepository.findOneByIdAndDeletedFalse(DESK.getId())).thenReturn(DESK);

        Desk desk= deskService.findOne(DESK.getId());
        verify(deskRepository).findOneByIdAndDeletedFalse(DESK.getId());
        assertEquals(desk.getId(), DESK.getId());
    }

    @Test
    public void deleteAll_ok_void() {
        when(deskRepository.findAll()).thenReturn(Arrays.asList(DESK));

        deskService.deleteAll();

        verify(deskRepository).findAll();
        verify(deskRepository).save(any());
    }

    @Test
    public void delete_ok_void() {
        when(deskRepository.findOneByIdAndDeletedFalse(DESK.getId())).thenReturn(DESK);

        deskService.delete(DESK);

        verify(deskRepository).findOneByIdAndDeletedFalse(DESK.getId());
        verify(deskRepository).save(DESK_DELETED);
    }
}
