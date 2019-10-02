package com.borzdykooa.converter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borzdykooa.converter.dto.ConverterDto;
import com.borzdykooa.converter.dto.ResultDto;
import com.borzdykooa.converter.service.ConverterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ConverterController {

	private final ConverterService converterService;

	@PostMapping("/convert")
	public ResultDto convert(@RequestBody ConverterDto converterDto) {
		return converterService.convert(converterDto);
	}
}
