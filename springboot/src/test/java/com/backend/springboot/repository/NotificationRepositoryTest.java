package com.backend.springboot.repository;

import com.backend.springboot.model.Notification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.NotificationConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void findOneByNotificationId_EverythingOk_ReturnNotification() {
        Notification pronadjen = this.notificationRepository.findOneById(ID_1);
        assertEquals(PORUKA_1, pronadjen.getMessage());
    }
}
