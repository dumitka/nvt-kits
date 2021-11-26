package com.backend.springboot.repository;

import com.backend.springboot.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	public Notification findOneById(Integer id);
}
