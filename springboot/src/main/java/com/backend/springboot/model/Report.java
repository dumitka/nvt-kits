package com.backend.springboot.model;

import com.backend.springboot.enums.ReportType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "report_type", nullable = false)
	private ReportType reportType;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	@Column(name = "salary_expense")
	private Float salaryExpense;

	@Column(name = "purchase_expense")
	private Float purchaseExpense;

	@Column(name = "bill_expense") //trosak na rezije
	private Float billExpense;

	@Column(name = "income_of_food_sale")
	private Float incomeOfFoodSale;

	@Column(name = "income_of_drink_sale")
	private Float incomeOfDrinkSale;

	@Column(name = "tip")
	private Float tip;

}
