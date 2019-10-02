package com.borzdykooa.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {

	USD(145),
	EUR(292);

	private Integer value;
}
