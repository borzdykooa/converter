package com.borzdykooa.converter.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConverterDto {

	private Currency from;
	private Currency to;
	private BigDecimal amount;
	private LocalDate localDate;
}
