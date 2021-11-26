package com.backend.springboot.controller;

import com.backend.springboot.enums.NotificationStatus;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Notification;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.service.NotificationService;
import com.backend.springboot.service.OrderService;
import com.backend.springboot.service.OrderedMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orderedMeals")
public class OrderedMealController {
	@Autowired
	private OrderedMealService orderedMealService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@PutMapping("/acceptMeal/{id}")
	public ResponseEntity<String> acceptOrderedMeal(@PathVariable Integer id) {
		OrderedMeal meal = orderedMealService.findOne(id);
		//meal.setCook(userService.findById()); //TODO: dobaviti trenutno ulogovanog
		meal.setStatus(OrderedItemStatus.IN_PROGRESS);
		orderedMealService.save(meal);

		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Meal " + meal.getMeal().getName() + " is in progress!")
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);

		Order order = orderService.findOne(meal.getOrder().getId());
		order.getNotifications().add(notification);
		orderService.save(order);

		return new ResponseEntity<String>("Ordered meal successfully accepted!", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@PutMapping("/finishMeal/{id}")
	public ResponseEntity<String> finishOrderedMeal(@PathVariable Integer id) {
		OrderedMeal meal = orderedMealService.findOne(id);
		meal.setStatus(OrderedItemStatus.DONE);
		orderedMealService.save(meal);

		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Meal " + meal.getMeal().getName() + " is done!")
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);

		Order order = orderService.findOne(meal.getOrder().getId());
		order.getNotifications().add(notification);
		orderService.save(order);

		return new ResponseEntity<String>("Ordered meal successfully finished!", HttpStatus.OK);
	}
}
