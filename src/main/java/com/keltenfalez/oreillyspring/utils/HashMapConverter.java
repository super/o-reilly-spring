package com.keltenfalez.oreillyspring.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/*
	Based on the dummy data provided, I noticed that tenderDetails is of type HashMap.
	I am assuming that I am required to persist this JSON object to the DB.
	In other words, I'm trying to store the JSON tenderDetails as a string in a column via JPA.
	I followed this tutorial (https://www.baeldung.com/hibernate-persist-json-object) and decided to use a generic option using Jackson and Hibernate.
 */
@Slf4j
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String convertToDatabaseColumn(Map<String, Object> tenderDetails) {
		String tenderDetailsJson = null;

		try {
			tenderDetailsJson = objectMapper.writeValueAsString(tenderDetails);
		} catch (final JsonProcessingException e) {
			log.error("JSON writing error: ", e);
		}

		return tenderDetailsJson;
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String tenderDetailsJson) {
		Map<String, Object> tenderDetails = null;
		try {
			tenderDetails = objectMapper.readValue(tenderDetailsJson, new TypeReference<HashMap<String, Object>>() {
			});

		} catch (final JsonProcessingException e) {
			log.error("JSON reading error: ", e);
		}

		return tenderDetails;
	}

}
