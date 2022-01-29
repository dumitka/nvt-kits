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
import org.springframework.web.bind.annotation.*;

import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.dtoTransformation.OrderedDrinkToOrderedDrinkDTO;
import com.backend.springboot.enums.NotificationStatus;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Notification;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.model.User;
import com.backend.springboot.service.NotificationService;
import com.backend.springboot.service.OrderService;
import com.backend.springboot.service.OrderedDrinkService;

@RestController
@RequestMapping("api/orderedDrinks")
public class OrderedDrinkController {
	@Autowired
	private OrderedDrinkService orderedDrinkService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;

	@Autowired
	private OrderedDrinkToOrderedDrinkDTO orderedDrinkToDTO;
	
	@PreAuthorize("hasAnyRole('ROLE_BARTENDER')")
	@GetMapping("/notAccepted")
	public ResponseEntity<Set<OrderedDrinkDTO>> notAcceptedOrderedMeals() {
		List<OrderedDrink> listOrderedDrinks = orderedDrinkService.findByStatus(OrderedItemStatus.ORDERED);
		Set<OrderedDrink> setOrderedDrinks = new HashSet<OrderedDrink>(listOrderedDrinks);
        Set<OrderedDrinkDTO> dto = orderedDrinkToDTO.convertSet(setOrderedDrinks);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BARTENDER')")
	@PutMapping("/acceptDrink")
	public ResponseEntity<Boolean> acceptOrderedDrink(@RequestBody Integer id) {

		OrderedDrink drink = orderedDrinkService.findOne(id);

		User bartender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		drink.setBartender(bartender);
		drink.setStatus(OrderedItemStatus.IN_PROGRESS);
		orderedDrinkService.save(drink);

		/*
		Order order = orderService.findOne(drink.getOrder().getId());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Piće " + drink.getDrink().getName() + " je u pripremi!")
				.order(order)
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification); */

		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_BARTENDER')")
	@GetMapping("/accepted/{userId}")
	public ResponseEntity<Set<OrderedDrinkDTO>> acceptedOrderedDrinks(@PathVariable Integer userId) {
		List<OrderedDrink> listOrderedDrinks = orderedDrinkService.findByBartenderIdAndStatus(userId, OrderedItemStatus.IN_PROGRESS );
		Set<OrderedDrink> setOrderedDrinks = new HashSet<OrderedDrink>(listOrderedDrinks);
        Set<OrderedDrinkDTO> dto = orderedDrinkToDTO.convertSet(setOrderedDrinks);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BARTENDER')")
	@PutMapping("/finishDrink")
	public ResponseEntity<Boolean> finishOrderedDrink(@RequestBody Integer id) {
		OrderedDrink drink = orderedDrinkService.findOne(id);
		drink.setStatus(OrderedItemStatus.DONE);
		orderedDrinkService.save(drink);

		/*
		Order order = orderService.findOne(drink.getOrder().getId());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Piće " + drink.getDrink().getName() + " je gotovo!")
				.order(order)
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		*/


		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
