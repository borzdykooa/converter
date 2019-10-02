package com.borzdykooa.converter.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.borzdykooa.converter.dto.ConverterDto;
import com.borzdykooa.converter.dto.Currency;
import com.borzdykooa.converter.dto.ResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ConverterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void convertFromEurToUsd() throws Exception {
		ConverterDto dto = ConverterDto.builder()
				.from(Currency.EUR)
				.to(Currency.USD)
				.amount(BigDecimal.TEN)
				.localDate(LocalDate.of(2019, 10, 1))
				.build();
		String json = objectMapper.writeValueAsString(dto);

		MvcResult result = mockMvc.perform(post("/api/v1/convert")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();
		ResultDto resultDto = objectMapper.readValue(content, ResultDto.class);

		assertEquals(BigDecimal.valueOf(10.93), resultDto.getResult());
	}

	@Test
	public void convertFromUsdToEur() throws Exception {
		ConverterDto dto = ConverterDto.builder()
				.from(Currency.USD)
				.to(Currency.EUR)
				.amount(BigDecimal.TEN)
				.localDate(LocalDate.of(2019, 10, 1))
				.build();
		String json = objectMapper.writeValueAsString(dto);

		MvcResult result = mockMvc.perform(post("/api/v1/convert")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();
		ResultDto resultDto = objectMapper.readValue(content, ResultDto.class);

		assertEquals(BigDecimal.valueOf(9.15), resultDto.getResult());
	}
}
