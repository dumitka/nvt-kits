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

import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.dtoTransformation.OrderedMealToOrderedMealDTO;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.Order;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.model.User;
import com.backend.springboot.service.MealService;
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
	private MealService mealService;

	@Autowired
	private SimpMessagingTemplate brokerMessagingTemplate;

	@Autowired
	private OrderedMealToOrderedMealDTO orderedMealToDTO;
	
	
	//constructor
	public OrderedMealController() {
		this.orderedMealToDTO = new OrderedMealToOrderedMealDTO();
	}
		

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@GetMapping("/notAccepted")
	public ResponseEntity<Set<OrderedMealDTO>> notAcceptedOrderedMeals() {
		List<OrderedMeal> listOrderedMeals = orderedMealService.findByStatus(OrderedItemStatus.ORDERED);
		Set<OrderedMeal> setOrderedMeals = new HashSet<OrderedMeal>(listOrderedMeals);
        Set<OrderedMealDTO> dto = orderedMealToDTO.convertSet(setOrderedMeals);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@PutMapping("/acceptMeal")
	public ResponseEntity<Boolean> acceptOrderedMeal(@RequestBody Integer id) {
		System.out.println("TACA");
		System.out.println(id);
		OrderedMeal meal = orderedMealService.findOne(id);
		System.out.println(meal.getId());
		User cook = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(cook.getId());
		meal.setCook(cook);
		meal.setStatus(OrderedItemStatus.IN_PROGRESS);
		orderedMealService.save(meal);

		//Ovo je uzrokovalo neku gresku, nemam pojma, pa sam ostavila za kasnije --> TACA :)
		/*    
		Order order = orderService.findOne(meal.getOrder().getId());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Jelo " + meal.getMeal().getName() + " je u pripremi!")
				.order(order)
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		*/
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@GetMapping("/accepted/{userId}")
	public ResponseEntity<Set<OrderedMealDTO>> acceptedOrderedMeals(@PathVariable Integer userId) {
		List<OrderedMeal> listOrderedMeals = orderedMealService.findByCookIdAndStatus(userId, OrderedItemStatus.IN_PROGRESS);
		Set<OrderedMeal> setOrderedMeals = new HashSet<OrderedMeal>(listOrderedMeals);
        Set<OrderedMealDTO> dto = orderedMealToDTO.convertSet(setOrderedMeals);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_COOK', 'ROLE_CHEF')")
	@PutMapping("/finishMeal")
	public ResponseEntity<Boolean> finishOrderedMeal(@RequestBody Integer id) {
		OrderedMeal meal = orderedMealService.findOne(id);
		meal.setStatus(OrderedItemStatus.DONE);
		orderedMealService.save(meal);

		/*
		Order order = orderService.findOne(meal.getOrder().getId());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Jelo " + meal.getMeal().getName() + " je gotovo!")
				.order(order)
				.build();
		notificationService.save(notification);
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		*/
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('WAITER')")
	@PostMapping("/createOrderedMeal/{orderId}")
	public ResponseEntity<String> createOrderedDrink(@PathVariable Integer orderId, @RequestBody OrderedMealDTO dto) {
		Order order = orderService.findOne(orderId);
		if (order == null) {
			return new ResponseEntity<String>("Porudžbina ne postoji!", HttpStatus.BAD_REQUEST);
		}
		
		OrderedMeal orderedMeal = OrderedMeal.builder()
				.amount(dto.getAmount())
				.meal(mealService.findOne(dto.getMealId()))
				.order(order)
				.status(OrderedItemStatus.ORDERED)
				.build();
		orderedMealService.save(orderedMeal);

		return new ResponseEntity<String>("Poručeno jelo je uspešno kreirano!", HttpStatus.OK);
	}
}
