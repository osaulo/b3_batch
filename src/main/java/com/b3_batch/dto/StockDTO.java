package com.b3_batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema (name = "StockDTO", description = "Represents stock")
public class StockDTO implements Serializable {

	private Long id;
	
	@NotNull(message = "Missing fields")
	@Schema(description = "Sigla", type = "string", example = "vale3")
	private String acronym;

	@NotNull(message = "Missing fields")
	@Schema(description = "Tipo", type = "string", example = "Ações")
	private String type;

	@NotNull(message = "Missing fields")
	@Schema(description = "Setor", type = "string", example = "Mineração")
	private String sector;

	@NotNull(message = "Missing fields")
	@Schema(description = "Fechamento anterior", type = "string", example = "96.35")
	private double previous_close;

	@NotNull(message = "Missing fields")
	@Schema(description = "Abertura", type = "string", example = "97.36")
	private double opening;

	@NotNull(message = "Missing fields")
	@Schema(description = "Negócios", type = "string", example = "32610")
	private double business;

	@NotNull(message = "Missing fields")
	@Schema(description = "Volume", type = "string", example = "1360000000")
	private double Volume;

	@NotNull(message = "Missing fields")
	@Schema(description = "Minimo dia", type = "string", example = "96.01")
	private double min_day;

	@NotNull(message = "Missing fields")
	@Schema(description = "Maximo dia", type = "string", example = "97.36")
	private double max_day;

	@NotNull(message = "Missing fields")
	@Schema(description = "Variação dia", type = "string", example = "-0.01")
	private double variation_day;

	@NotNull(message = "Missing fields")
	@Schema(description = "Variação mes", type = "string", example = "9.52")
	private double variation_month;

	@NotNull(message = "Missing fields")
	@Schema(description = "variacao ano", type = "string", example = "10.16")
	private double variation_year;

	@NotNull(message = "Missing fields")
	@Schema(description = "Variação 52 semanas", type = "string", example = "90.06")
	private double variation_52_weeks;

	@NotNull(message = "Missing fields")
	@Schema(description = "Receita Líquida", type = "string", example = "170600000000")
	private BigDecimal net_earnings;

	@NotNull(message = "Missing fields")
	@Schema(description = "Lucro Líquido (LL)", type = "string", example = "12390000000")
	private BigDecimal net_profit;

	@NotNull(message = "Missing fields")
	@Schema(description = "Margem Líquida", type = "string", example = "7.26")
	private double net_margin;

	@NotNull(message = "Missing fields")
	@Schema(description = "Ebitda", type = "string", example = "53350000000")
	private BigDecimal ebitda;

	@NotNull(message = "Missing fields")
	@Schema(description = "Margem Ebitda", type = "string", example = "39.88")
	private double ebitda_margin;

	@NotNull(message = "Missing fields")
	@Schema(description = "Ativo Total", type = "string", example = "451130000000")
	private BigDecimal total_assets;

	@NotNull(message = "Missing fields")
	@Schema(description = "Dívida Bruta", type = "string", example = "84980000000")
	private BigDecimal gross_debt;

	@NotNull(message = "Missing fields")
	@Schema(description = "Dívida Líquida", type = "string", example = "35090000000")
	private BigDecimal net_debt;

	@NotNull(message = "Missing fields")
	@Schema(description = "Patrimônio Líquido (PL)", type = "string", example = "187360000000")
	private BigDecimal shareholders_equity;

	@NotNull(message = "Missing fields")
	@Schema(description = "Índice de preço sobre lucro (P/L)", type = "string", example = "31.27")
	private double price_profit_index;

	@NotNull(message = "Missing fields")
	@Schema(description = "Retorno sobre o PL (ROE)", type = "string", example = "6.61")
	private double return_shareholders_equity;

	@NotNull(message = "Missing fields")
	@Schema(description = "Retorno sobre o Capital (ROIC)", type = "string", example = "8.94")
	private double return_on_capital;
}
