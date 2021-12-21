package com.backend.springboot.service;

import com.backend.springboot.dto.CreateReportDto;
import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedDrink;
import com.backend.springboot.model.OrderedMeal;
import com.backend.springboot.model.Report;
import com.backend.springboot.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private OrderedDrinkService orderedDrinkService;

    @Autowired
    private OrderedMealService orderedMealService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private DrinkCardService drinkCardService;

    @Autowired
    private MealPriceService mealPriceService;

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public Report createNewReport(CreateReportDto dto) {

        //income meals
        List<OrderedMeal> meals = orderedMealService.findByStatus(OrderedItemStatus.DELIVERED);
        Float incomeFromMeals = meals.stream()
                .map(orderedMeal -> orderedMeal.getMeal())
                .map(meal -> mealPriceService.findPriceOfMealForDate(dto.getDate(), meal.getId()))
                .map(mealPrice -> mealPrice.getPriceAmount())
                .reduce((float) 0, (subtotal, element) -> subtotal + element);

        //income drinks
        List<OrderedDrink> drinks = orderedDrinkService.findByStatus(OrderedItemStatus.DELIVERED);
        Float incomeFromDrinks = drinks.stream()
                .map(orderedDrink -> orderedDrink.getDrink())
                .map(drink -> drinkCardService.findPriceOfDrinkForDate(dto.getDate(), drink.getId()))
                .map(drinkPrice -> drinkPrice.getPrice())
                .reduce((float) 0, (subtotal, element) -> subtotal + element);


        //bills
        Float bills = (float) 40000; //todo implement logic
        //purchaseExpense
        Float purchaseExpense = (incomeFromDrinks + incomeFromDrinks) / (float) 0.2;

        //salaryexp
        Float salaryExpense = userService.getAllEmployees().stream()
                .map(user -> salaryService.getSalaryForUser(user.getId()))
                .map(salary -> salary.getAmount())
                .reduce((float) 0, (subtotal, element) -> subtotal + element);

        Report report = Report.builder()
                .salaryExpense(salaryExpense)
                .billExpense(bills)
                .incomeOfDrinkSale(incomeFromDrinks)
                .incomeOfFoodSale(incomeFromMeals)
                .purchaseExpense(purchaseExpense)
                .reportType(dto.getReportType())
                .date(dto.getDate())
                .tip(dto.getType())
                .build();

        report = reportRepository.save(report);

        return report;
    }
}
