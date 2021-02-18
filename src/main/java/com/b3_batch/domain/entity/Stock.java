package com.b3_batch.domain.entity;

import com.b3_batch.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock extends AbstractAuditingEntity implements Serializable {
	
	private static final long serialVersionUID = -5812478667253417292L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "acronym", nullable = false, unique = true)
	private String acronym;
	
	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "sector", nullable = false)
	private String sector;

	@Column(name = "previous_close", nullable = false)
	private double previous_close;

	@Column(name = "opening", nullable = false)
	private double opening;

	@Column(name = "min_day", nullable = false)
	private double min_day;

	@Column(name = "max_day", nullable = false)
	private double max_day;

	@Column(name = "variation_day", nullable = false)
	private double variation_day;

	@Column(name = "variation_month", nullable = false)
	private double variation_month;

	@Column(name = "variation_year", nullable = false)
	private double variation_year;

	@Column(name = "variation_52_weeks", nullable = false)
	private double variation_52_weeks;

	@Column(name = "net_earnings", nullable = false)
	private BigDecimal net_earnings;

	@Column(name = "net_profit", nullable = false)
	private BigDecimal net_profit;

	@Column(name = "net_margin", nullable = false)
	private double net_margin;

	@Column(name = "ebitda", nullable = false)
	private BigDecimal ebitda;

	@Column(name = "ebitda_margin", nullable = false)
	private double ebitda_margin;

	@Column(name = "total_assets", nullable = false)
	private BigDecimal total_assets;

	@Column(name = "gross_debt", nullable = false)
	private BigDecimal gross_debt;

	@Column(name = "net_debt", nullable = false)
	private BigDecimal net_debt;

	@Column(name = "shareholders_equity", nullable = false)
	private BigDecimal shareholders_equity;

	@Column(name = "price_profit_index", nullable = false)
	private double price_profit_index;

	@Column(name = "return_shareholders_equity", nullable = false)
	private double return_shareholders_equity;

	@Column(name = "return_on_capital", nullable = false)
	private double return_on_capital;

}
