package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.enums.NotificationStatus;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Notification;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedDrink;
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

	@PreAuthorize("hasRole('ROLE_BARTENDER')")
    @PutMapping("/acceptDrink/{id}")
    public ResponseEntity<String> acceptOrderedDrink(@PathVariable Integer id) {
		OrderedDrink drink = orderedDrinkService.findOne(id);
		//drink.setBartender(userService.findById()); //TODO: dobaviti trenutno ulogovanog
		drink.setStatus(OrderedItemStatus.IN_PROGRESS);
		orderedDrinkService.save(drink);
		
		Notification notification = Notification.builder()
												.status(NotificationStatus.SENT)
												.message("Drink " + drink.getDrink().getName() + " is in progress!")
												.build();
		notificationService.save(notification);
        brokerMessagingTemplate.convertAndSend("/topic/hi", notification);

		Order order = orderService.findOne(drink.getOrder().getId());
		order.getNotifications().add(notification);
		orderService.save(order);
				
        return new ResponseEntity<String>("Ordered drink successfully accepted!", HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_BARTENDER')")
    @PutMapping("/finishDrink/{id}")
    public ResponseEntity<String> finishOrderedDrink(@PathVariable Integer id) {
		OrderedDrink drink = orderedDrinkService.findOne(id);
		drink.setStatus(OrderedItemStatus.DONE);
		orderedDrinkService.save(drink);
		
		Notification notification = Notification.builder()
												.status(NotificationStatus.SENT)
												.message("Drink " + drink.getDrink().getName() + " is done!")
												.build();
		notificationService.save(notification);
        brokerMessagingTemplate.convertAndSend("/topic/hi", notification);

		Order order = orderService.findOne(drink.getOrder().getId());
		order.getNotifications().add(notification);
		orderService.save(order);
				
        return new ResponseEntity<String>("Ordered drink successfully finished!", HttpStatus.OK);
    }
}
