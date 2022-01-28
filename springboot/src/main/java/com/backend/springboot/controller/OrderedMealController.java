package com.backend.springboot.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.dtoTransformation.OrderedMealToOrderedMealDTO;
import com.backend.springboot.enums.NotificationStatus;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Notification;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.model.User;
import com.backend.springboot.service.NotificationService;
import com.backend.springboot.service.OrderService;
import com.backend.springboot.service.OrderedMealService;

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
	
	private OrderedMealToOrderedMealDTO orderedMealToDTO;

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@GetMapping("/notAccepted")
	public ResponseEntity<Set<OrderedMealDTO>> notAcceptedOrderedMeals() {
		List<OrderedMeal> listOrderedMeals = orderedMealService.findByStatus(OrderedItemStatus.ORDERED);
		Set<OrderedMeal> setOrderedMeals = new HashSet<OrderedMeal>(listOrderedMeals);
        Set<OrderedMealDTO> dto = orderedMealToDTO.convertSet(setOrderedMeals);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@PutMapping("/acceptMeal/{id}")
	public ResponseEntity<String> acceptOrderedMeal(@PathVariable Integer id) {
		OrderedMeal meal = orderedMealService.findOne(id);
		User cook = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		meal.setCook(cook);
		meal.setStatus(OrderedItemStatus.IN_PROGRESS);
		orderedMealService.save(meal);

		Order order = orderService.findOne(meal.getOrder().getId());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Jelo " + meal.getMeal().getName() + " je u pripremi!")
				.order(order)
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);

		return new ResponseEntity<String>("Poručeno jelo je uspešno prihvaćeno!", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@GetMapping("/accepted/{userId}")
	public ResponseEntity<Set<OrderedMealDTO>> acceptedOrderedMeals(@PathVariable Integer userId) {
		List<OrderedMeal> listOrderedMeals = orderedMealService.findByUser(userId);
		Set<OrderedMeal> setOrderedMeals = new HashSet<OrderedMeal>(listOrderedMeals);
        Set<OrderedMealDTO> dto = orderedMealToDTO.convertSet(setOrderedMeals);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@PutMapping("/finishMeal/{id}")
	public ResponseEntity<String> finishOrderedMeal(@PathVariable Integer id) {
		OrderedMeal meal = orderedMealService.findOne(id);
		meal.setStatus(OrderedItemStatus.DONE);
		orderedMealService.save(meal);

		Order order = orderService.findOne(meal.getOrder().getId());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Jelo " + meal.getMeal().getName() + " je gotovo!")
				.order(order)
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);

		return new ResponseEntity<String>("Poručeno jelo je uspešno završeno!", HttpStatus.OK);
	}
}
