package com.backend.springboot.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.backend.springboot.dto.OrderDTO;
import com.backend.springboot.dto.OrderedDrinkDTO;
import com.backend.springboot.dto.OrderedMealDTO;
import com.backend.springboot.dtoTransformation.OrderToOrderDTO;
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
import com.backend.springboot.service.DrinkCardService;
import com.backend.springboot.service.MenuService;
import com.backend.springboot.service.NotificationService;
import com.backend.springboot.service.OrderService;
import com.backend.springboot.service.OrderedDrinkService;
import com.backend.springboot.service.OrderedMealService;

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
	private DrinkCardService drinkCardService;
	
	@Autowired
	private MenuService menuService;
	
	@PreAuthorize("hasRole('ROLE_WAITER')")
	@GetMapping("/deskOrder/{deskId}")
	public ResponseEntity<OrderDTO> getOrderForDesk(@PathVariable Integer deskId) {
		OrderDTO orderDTO = null;
		
		Order order = orderService.findOrderForDesk(deskId);
		if (order != null) {
			OrderToOrderDTO orderToDTO = new OrderToOrderDTO();
			orderDTO = orderToDTO.convert(order);
		}

		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PostMapping("/createOrder/{deskId}")
	public ResponseEntity<String> createOrder(@PathVariable Integer deskId, @RequestBody OrderDTO orderDTO) {
		Desk desk = deskService.findOne(deskId);
		if (desk.getDeskStatus() != DeskStatus.NOT_ORDERED) {
			return new ResponseEntity<String>("Za odabranim stolom je već poručeno!", HttpStatus.BAD_REQUEST);
		}
		
		Set<OrderedMeal> orderedMeals = new HashSet<OrderedMeal>();
		Set<OrderedDrink> orderedDrinks = new HashSet<OrderedDrink>();
		Set<Notification> notifications = new HashSet<Notification>();
		Set<OrderedMealDTO> orderedMealsDTO = orderDTO.getOrderedMeals();
		Set<OrderedDrinkDTO> orderedDrinkDTO = orderDTO.getOrderedDrinks();
		
		if (orderedMealsDTO == null && orderedDrinkDTO == null) {
			return new ResponseEntity<String>("Porudžbina mora imati bar jedno jelo ili piće!", HttpStatus.BAD_REQUEST);
		}
		
		if(orderedMealsDTO != null) {
			orderedMeals = orderDTO.getOrderedMeals().stream().map(m -> orderedMealService.findOne(m.getId())).collect(Collectors.toSet());
		}
		
		if(orderedDrinkDTO != null) {
			orderedDrinks = orderDTO.getOrderedDrinks().stream().map(d -> orderedDrinkService.findOne(d.getId())).collect(Collectors.toSet());
		}

		User waiter = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (orderDTO.getNotificationIds() != null) {
			notifications = orderDTO.getNotificationIds().stream().map(n -> notificationService.findOne(n)).collect(Collectors.toSet());
		}
		
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Nova porudžbina!")
				.build();
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		notifications.add(notification);

		desk.setDeskStatus(DeskStatus.ORDERED);
		deskService.save(desk);

		Order newOrder = Order.builder()
				.waiter(waiter)
				.desk(desk)
				.isDeleted(false)
				.build();
		Order savedOrder = orderService.save(newOrder);
		
		if (!orderedDrinks.isEmpty()) {
			orderedDrinks.forEach(d -> {
				d.setOrder(savedOrder);
				orderedDrinkService.save(d);
			});
		}
		
		if (!orderedMeals.isEmpty()) {
			orderedMeals.forEach(m -> {
				m.setOrder(savedOrder);
				orderedMealService.save(m);
			});
		}
		
		notification.setOrder(savedOrder);
		notificationService.save(notification);

		return new ResponseEntity<String>("Porudžbina je uspešno kreirana!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/updateOrder/{deskId}")
	public ResponseEntity<String> updateOrder(@PathVariable Integer deskId, @RequestBody OrderDTO orderDTO) {
		Desk desk = deskService.findOne(deskId);
		if (desk.getDeskStatus() == DeskStatus.NOT_ORDERED) {
			return new ResponseEntity<String>("Za odabranim stolom još nije poručeno!", HttpStatus.BAD_REQUEST);
		} else if (desk.getDeskStatus() == DeskStatus.DELIVERED) {
			return new ResponseEntity<String>("Za odabranim stolom je dostavljena porudžbina!", HttpStatus.BAD_REQUEST);
		}
		
		Set<OrderedMeal> orderedMeals = new HashSet<OrderedMeal>();
		Set<OrderedDrink> orderedDrinks = new HashSet<OrderedDrink>();
		Set<OrderedMealDTO> orderedMealsDTO = orderDTO.getOrderedMeals();
		Set<OrderedDrinkDTO> orderedDrinkDTO = orderDTO.getOrderedDrinks();
		
		if (orderedMealsDTO == null && orderedDrinkDTO == null) {
			return new ResponseEntity<String>("Porudžbina mora imati bar jedno jelo ili piće!", HttpStatus.BAD_REQUEST);
		}
		
		if(orderedMealsDTO != null) {
			orderedMeals = orderDTO.getOrderedMeals().stream().map(m -> orderedMealService.findOne(m.getId())).collect(Collectors.toSet());
		}
		
		if(orderedDrinkDTO != null) {
			orderedDrinks = orderDTO.getOrderedDrinks().stream().map(d -> orderedDrinkService.findOne(d.getId())).collect(Collectors.toSet());
		}
		
		Set<Notification> notifications = orderDTO.getNotificationIds().stream().map(n -> notificationService.findOne(n)).collect(Collectors.toSet());
		Notification notification = Notification.builder()
				.status(NotificationStatus.SENT)
				.message("Porudžbina je izmenjena!")
				.build();
		brokerMessagingTemplate.convertAndSend("/topic/hi", notification);
		notifications.add(notification);

		Order updatedOrder = orderService.findOne(orderDTO.getId());
		
		if (!orderedDrinks.isEmpty()) {
			orderedDrinks.forEach(d -> {
				d.setOrder(updatedOrder);
				orderedDrinkService.save(d);
			});
		}
		
		if (!orderedMeals.isEmpty()) {
			orderedMeals.forEach(m -> {
				m.setOrder(updatedOrder);
				orderedMealService.save(m);
			});
		}
		
		notification.setOrder(updatedOrder);
		notificationService.save(notification);
		
		return new ResponseEntity<String>("Porudžbina je uspešno izmenjena!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/deleteOrder/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
		Order order = orderService.findOne(id);
		if (order == null) {
			return new ResponseEntity<String>("Za odabranim stolom ne postoji porudžbina za brisanje!", HttpStatus.BAD_REQUEST);
		}
		order.setIsDeleted(true);
		orderService.save(order);
		
		Desk desk = deskService.findOne(order.getDesk().getId());
		desk.setDeskStatus(DeskStatus.NOT_ORDERED);
		deskService.save(desk);

		return new ResponseEntity<String>("Porudžbina je uspešno obrisana!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@GetMapping("/chargeOrder/{id}")
	public ResponseEntity<String> chargeOrder(@PathVariable Integer id) {
		Order order = orderService.findOne(id);
		Desk desk = deskService.findOne(order.getDesk().getId());
		if (desk.getDeskStatus() != DeskStatus.DELIVERED) {
			return new ResponseEntity<String>("Za odabranim stolom još nije dostavljena porudžbina!", HttpStatus.BAD_REQUEST);
		}
		
		float charge = 0, price = 0, tip = 0;
		
		if (order.getOrderedDrinks() != null) {
			for (OrderedDrink orderedDrink : order.getOrderedDrinks()) {
				price = drinkCardService.getLatestForDrink(orderedDrink.getDrink().getId());
				charge += orderedDrink.getAmount() * price;
			}
		}
		
		if (order.getOrderedMeals() != null) {
			for (OrderedMeal orderedMeal : order.getOrderedMeals()) {
				price = menuService.getLatestForMeal(orderedMeal.getMeal().getId());
				charge += orderedMeal.getAmount() * price;
			}
		}
		
		tip = charge * 0.15f; // Bakšiš je 15%
		charge += tip;

		desk.setTip(tip);
		desk.setDeskStatus(DeskStatus.NOT_ORDERED);
		deskService.save(desk);
		
		order.setDesk(null);
		orderService.save(order);
		
		return new ResponseEntity<String>("Vaš račun je " + String.format("%.2f", charge) + " dinara.", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/deliverDrinks/{deskId}")
	public ResponseEntity<String> deliverDrinks(@PathVariable Integer deskId) {
		Desk desk = deskService.findOne(deskId);
		if (desk.getDeskStatus() == DeskStatus.NOT_ORDERED) {
			return new ResponseEntity<String>("Za odabranim stolom još nije poručeno!", HttpStatus.BAD_REQUEST);
		} else if (desk.getDeskStatus() == DeskStatus.DELIVERED || desk.getDeskStatus() == DeskStatus.DELIVERED_DRINKS) {
			return new ResponseEntity<String>("Za odabranim stolom su već dostavljena pića!", HttpStatus.BAD_REQUEST);
		}
		
		Order order = orderService.findOrderForDesk(deskId);
		
		Set<OrderedDrink> orderedDrinks = order.getOrderedDrinks();
		if (orderedDrinks != null) {
			for (OrderedDrink orderedDrink : orderedDrinks) {
				if (orderedDrink.getStatus() != OrderedItemStatus.DONE) {
					return new ResponseEntity<String>("Pića nisu završena!", HttpStatus.BAD_REQUEST);
				}
				orderedDrink.setStatus(OrderedItemStatus.DELIVERED);
				orderedDrinkService.save(orderedDrink);
			}
			desk.setDeskStatus(DeskStatus.DELIVERED_DRINKS);
		} else {
			return new ResponseEntity<String>("Za odabranim stolom nisu poručena pića!", HttpStatus.BAD_REQUEST);
		}

		if (desk.getDeskStatus() == DeskStatus.DELIVERED_MEALS || order.getOrderedMeals() == null) {
			desk.setDeskStatus(DeskStatus.DELIVERED);
		}

		deskService.save(desk);

		return new ResponseEntity<String>("Pića su uspešno poslužena!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_WAITER')")
	@PutMapping("/deliverMeals/{deskId}")
	public ResponseEntity<String> deliverMeals(@PathVariable Integer deskId) {
		Desk desk = deskService.findOne(deskId);
		if (desk.getDeskStatus() == DeskStatus.NOT_ORDERED) {
			return new ResponseEntity<String>("Za odabranim stolom još nije poručeno!", HttpStatus.BAD_REQUEST);
		} else if (desk.getDeskStatus() == DeskStatus.DELIVERED || desk.getDeskStatus() == DeskStatus.DELIVERED_MEALS) {
			return new ResponseEntity<String>("Za odabranim stolom su već dostavljena jela!", HttpStatus.BAD_REQUEST);
		}
		
		Order order = orderService.findOrderForDesk(deskId);
		
		Set<OrderedMeal> orderedMeals = order.getOrderedMeals();
		if (orderedMeals != null) {
			for (OrderedMeal orderedMeal : orderedMeals) {
				if (orderedMeal.getStatus() != OrderedItemStatus.DONE) {
					return new ResponseEntity<String>("Jela nisu završena!", HttpStatus.BAD_REQUEST);
				}
				orderedMeal.setStatus(OrderedItemStatus.DELIVERED);
				orderedMealService.save(orderedMeal);
			}
			desk.setDeskStatus(DeskStatus.DELIVERED_MEALS);
		} else {
			return new ResponseEntity<String>("Za odabranim stolom nisu poručena jela!", HttpStatus.BAD_REQUEST);
		}
		
		if (desk.getDeskStatus() == DeskStatus.DELIVERED_DRINKS || order.getOrderedDrinks() == null) {
			desk.setDeskStatus(DeskStatus.DELIVERED);
		}

		deskService.save(desk);

		return new ResponseEntity<String>("Jela su uspešno poslužena!", HttpStatus.OK);
	}
}
