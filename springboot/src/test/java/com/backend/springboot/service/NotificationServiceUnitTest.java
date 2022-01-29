package com.backend.springboot.service;

import com.backend.springboot.model.Notification;
import com.backend.springboot.repository.NotificationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.NotificationConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationServiceUnitTest {

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private NotificationRepository notificationRepository;

    @Before
    public void setUpp() {
        Notification sacuvanoObavestenje = Notification.builder().id(ID_1).message(PORUKA_1).build();

        given(this.notificationRepository.findOneById(ID_1)).willReturn(sacuvanoObavestenje);

    }

    @Test
    public void findOne_EverythingOk_ReturnNotification() {
        Notification pronadjena = this.notificationService.findOne(ID_1);
        verify(this.notificationRepository).findOneById(ID_1);
        assertEquals(PORUKA_1, pronadjena.getMessage());
    }
}
