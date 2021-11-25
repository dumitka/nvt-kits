package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.springboot.model.Notification;
import com.backend.springboot.repository.NotificationRepository;

public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;
	
	public Notification findOne(Integer id) {
		return notificationRepository.findOneById(id);
	}
	
	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}
}
