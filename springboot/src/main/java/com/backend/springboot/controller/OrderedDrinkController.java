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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.dtoTransformation.OrderedDrinkToOrderedDrinkDTO;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.model.User;
import com.backend.springboot.service.DrinkService;
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
	private DrinkService drinkService;

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
	public ResponseEntity<String> acceptOrderedDrink(@RequestBody Integer id) {

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

		return new ResponseEntity<String>("Poručeno piće je uspešno prihvaćeno!", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_BARTENDER')")
	@GetMapping("/accepted/{userId}")
	public ResponseEntity<Set<OrderedDrinkDTO>> acceptedOrderedDrinks(@PathVariable Integer userId) {
		List<OrderedDrink> listOrderedDrinks = orderedDrinkService.findByBartender(userId);
		Set<OrderedDrink> setOrderedDrinks = new HashSet<OrderedDrink>(listOrderedDrinks);
        Set<OrderedDrinkDTO> dto = orderedDrinkToDTO.convertSet(setOrderedDrinks);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BARTENDER')")
	@PutMapping("/finishDrink")
	public ResponseEntity<String> finishOrderedDrink(@RequestBody Integer id) {
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


		return new ResponseEntity<String>("Poručeno piće je uspešno završeno!", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('WAITER')")
	@PostMapping("/createOrderedDrink/{orderId}")
	public ResponseEntity<String> createOrderedDrink(@PathVariable Integer orderId, @RequestBody OrderedDrinkDTO dto) {
		Order order = orderService.findOne(orderId);
		if (order == null) {
			return new ResponseEntity<String>("Porudžbina ne postoji!", HttpStatus.BAD_REQUEST);
		}
		
		OrderedDrink orderedDrink = OrderedDrink.builder()
				.amount(dto.getAmount())
				.drink(drinkService.findOne(dto.getDrinkId()))
				.order(order)
				.status(OrderedItemStatus.ORDERED)
				.build();
		orderedDrinkService.save(orderedDrink);

		return new ResponseEntity<String>("Poručeno piće je uspešno kreirano!", HttpStatus.OK);
	}
}
