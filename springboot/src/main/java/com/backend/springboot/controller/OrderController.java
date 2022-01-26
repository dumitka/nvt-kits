package com.backend.springboot.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.dto.OrderDTO;
import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.enums.DeskStatus;
import com.backend.springboot.enums.NotificationStatus;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Desk;
import com.backend.springboot.model.Notification;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.model.User;
import com.backend.springboot.service.DeskService;
import com.backend.springboot.service.NotificationService;
import com.backend.springboot.service.OrderService;
import com.backend.springboot.service.OrderedDrinkService;
import com.backend.springboot.service.OrderedMealService;
import com.backend.springboot.service.UserService;

@RestController
@RequestMapping("api/orders")
public class OrderController {
	@Autowired
	private OrderedMealService orderedMealService;

	@Autowired
	private OrderedDrinkService orderedDrinkService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private DeskService deskService;

	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	//@PreAuthorize("hasRole('ROLE_WAITER')")
	@PostMapping("/createOrder/{deskId}")
	public ResponseEntity<String> createOrder(@PathVariable Integer deskId, @RequestBody OrderDTO orderDTO) {
		Set<OrderedMeal> orderedMeals = new HashSet<OrderedMeal>();
		Set<OrderedDrink> orderedDrinks = new HashSet<OrderedDrink>();
		Set<Notification> notifications = new HashSet<Notification>();
		Set<OrderedMealDTO> orderedMealsDTO = orderDTO.getOrderedMeals();
		Set<OrderedDrinkDTO> orderedDrinkDTO = orderDTO.getOrderedDrinks();
		
		/*if (orderedMealsDTO == null && orderedDrinkDTO == null) {
			return new ResponseEntity<String>("Order must have either drinks or meals!", HttpStatus.BAD_REQUEST);
		}*/
		
		if(orderedMealsDTO != null) {
			orderedMeals = orderDTO.getOrderedMeals().stream().map(m -> orderedMealService.findOne(m.getId())).collect(Collectors.toSet());
		}
		
		if(orderedDrinkDTO != null) {
			orderedDrinks = orderDTO.getOrderedDrinks().stream().map(d -> orderedDrinkService.findOne(d.getId())).collect(Collectors.toSet());
		}

		User waiter = userService.findById(3);// TODO: (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (orderDTO.getNotificationIds() != null) {
			notifications = orderDTO.getNotificationIds().stream().map(n -> notificationService.findOne(n)).collect(Collectors.toSet());
		}
		
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("New order!")
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		notifications.add(notification);

		Desk desk = deskService.findOne(deskId);
		desk.setDeskStatus(DeskStatus.ORDERED);
		deskService.save(desk);

		Order newOrder = Order.builder()
				.orderedMeals(orderedMeals)
				.orderedDrinks(orderedDrinks)
				.waiter(waiter)
				//.notifications(notifications)
				.desk(desk)
				.isDeleted(false)
				.build();
		Order savedOrder = orderService.save(newOrder); // TODO: ordered items
		notification.setOrder(savedOrder);
		notificationService.save(notification);

		return new ResponseEntity<String>("Order successfully created!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')") // TODO: ispraviti kao kreiranje
	@PutMapping("/updateOrder/{deskId}")
	public ResponseEntity<String> updateOrder(@PathVariable Integer deskId, @RequestBody OrderDTO orderDTO) {
		Set<OrderedMeal> orderedMeals = orderDTO.getOrderedMeals().stream().map(m -> orderedMealService.findOne(m.getId())).collect(Collectors.toSet());
		Set<OrderedDrink> orderedDrinks = orderDTO.getOrderedDrinks().stream().map(d -> orderedDrinkService.findOne(d.getId())).collect(Collectors.toSet());

		Set<Notification> notifications = orderDTO.getNotificationIds().stream().map(n -> notificationService.findOne(n)).collect(Collectors.toSet());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Order updated!")
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		notifications.add(notification);

		Order updatedOrder = orderService.findOne(orderDTO.getId());
		updatedOrder.setOrderedMeals(orderedMeals);
		updatedOrder.setOrderedDrinks(orderedDrinks);
		updatedOrder.setNotifications(notifications);
		orderService.save(updatedOrder);

		return new ResponseEntity<String>("Order successfully updated!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/deleteOrder/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
		Order order = orderService.findOne(id);
		order.setIsDeleted(true);
		orderService.save(order);
		
		Desk desk = deskService.findOne(order.getDesk().getId());
		desk.setDeskStatus(DeskStatus.NOT_ORDERED);
		deskService.save(desk);

		return new ResponseEntity<String>("Order successfully deleted!", HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ROLE_WAITER')")
	@GetMapping("/chargeOrder/{id}")
	public ResponseEntity<String> chargeOrder(@PathVariable Integer id) {
		Order order = orderService.findOne(id);
		float charge = 0;
		
		for (OrderedDrink orderedDrink : order.getOrderedDrinks()) {
			//charge += orderedDrink.getAmount() * orderedDrink.getDrinkPrice().getPrice();
		}
		
		for (OrderedMeal orderedMeal : order.getOrderedMeals()) {
			//charge += orderedMeal.getAmount() * orderedMeal.getMealPrice().getPrice();
		}
		
		Desk desk = deskService.findOne(order.getDesk().getId());
		desk.setDeskStatus(DeskStatus.CHARGED);
		deskService.save(desk);

		return new ResponseEntity<String>("Order successfully charged!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/deliverDrinks/{id}")
	public ResponseEntity<String> deliverDrinks(@PathVariable Integer id) {
		Order order = orderService.findOne(id);
		order.getOrderedDrinks().forEach(d -> {
			d.setStatus(OrderedItemStatus.DELIVERED);
			orderedDrinkService.save(d);
		});

		Set<OrderedMeal> notDelivered = order.getOrderedMeals().stream().filter(m -> m.getStatus().equals(OrderedItemStatus.ORDERED) || m.getStatus().equals(OrderedItemStatus.IN_PROGRESS)).collect(Collectors.toSet());
		if (notDelivered.isEmpty()) {
			Desk desk = deskService.findOne(order.getDesk().getId());
			desk.setDeskStatus(DeskStatus.DELIVERED);
			deskService.save(desk);
		}

		orderService.save(order);

		return new ResponseEntity<String>("Drinks successfully delivered!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/deliverMeals/{id}")
	public ResponseEntity<String> deliverMeals(@PathVariable Integer id) {
		Order order = orderService.findOne(id);
		order.getOrderedMeals().forEach(m -> {
			m.setStatus(OrderedItemStatus.DELIVERED);
			orderedMealService.save(m);
		});

		Set<OrderedDrink> notDelivered = order.getOrderedDrinks().stream().filter(d -> d.getStatus().equals(OrderedItemStatus.ORDERED) || d.getStatus().equals(OrderedItemStatus.IN_PROGRESS)).collect(Collectors.toSet());
		if (notDelivered.isEmpty()) {
			Desk desk = deskService.findOne(order.getDesk().getId());
			desk.setDeskStatus(DeskStatus.DELIVERED);
			deskService.save(desk);
		}

		orderService.save(order);

		return new ResponseEntity<String>("Meals successfully delivered!", HttpStatus.OK);
	}
}
