package com.backend.springboot.service;

import com.backend.springboot.enums.NotificationStatus;
import com.backend.springboot.model.Notification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.springboot.constants.NotificationConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class NotificationServiceIntegrationTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void findOne_EverythingOk_ReturnNotification() {
        Notification pronadjena = this.notificationService.findOne(ID_1);
        assertEquals(PORUKA_1, pronadjena.getMessage());
    }

    @Test
    public void save_EverythingOk_ReturnNotification() {
        Notification poruka = Notification.builder().message("nana").status(NotificationStatus.SENT).build();
        Notification pronadjena = this.notificationService.save(poruka);
        System.out.println(pronadjena);
        assertTrue(ID_NOVI == pronadjena.getId());
        assertEquals("nana", pronadjena.getMessage());
    }
}
