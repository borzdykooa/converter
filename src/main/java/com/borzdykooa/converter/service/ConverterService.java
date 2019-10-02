package com.borzdykooa.converter.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.borzdykooa.converter.dto.ConverterDto;
import com.borzdykooa.converter.dto.ResultDto;
import com.borzdykooa.converter.template.BankRestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConverterService {

	private final BankRestTemplate bankRestTemplate;

	public ResultDto convert(ConverterDto converterDto) {
		BigDecimal fromRate = bankRestTemplate.getRate(converterDto.getFrom().getValue(), converterDto.getLocalDate());
		BigDecimal toRate = bankRestTemplate.getRate(converterDto.getTo().getValue(), converterDto.getLocalDate());

		return new ResultDto(converterDto.getAmount().multiply(fromRate).divide(toRate, 2, RoundingMode.HALF_UP));
	}
}
