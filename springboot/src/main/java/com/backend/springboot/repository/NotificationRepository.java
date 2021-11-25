package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	public Notification findOneById(Integer id);
}
