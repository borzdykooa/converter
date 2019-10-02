package com.borzdykooa.converter.template;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.borzdykooa.converter.exception.ApplicationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BankRestTemplate {

	private static final String BASE_URL = "http://www.nbrb.by/API/ExRates/Rates/";

	private final ObjectMapper objectMapper;
	private final RestTemplate restTemplate;

	public BigDecimal getRate(Integer curId, LocalDate localDate) {
		LocalDate onDate = localDate == null ? LocalDate.now() : localDate;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
				.path(curId.toString())
				.queryParam("onDate", onDate);
		HttpEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
		JsonNode root = getRoot(response);

		return new BigDecimal(root.findValue("Cur_OfficialRate").toString());
	}

	private JsonNode getRoot(HttpEntity<String> response) {
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(response.getBody());
		} catch (IOException e) {
			throw new ApplicationException("IOException occurred in BankRestTemplate::getRoot");
		}

		return jsonNode;
	}
}
