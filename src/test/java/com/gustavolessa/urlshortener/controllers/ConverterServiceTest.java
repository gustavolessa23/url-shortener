package com.gustavolessa.urlshortener.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import com.gustavolessa.urlshortener.services.ConverterService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

@QuarkusTest
public class ConverterServiceTest {

	
	@Inject
	ConverterService converter;
	
	
	@Test
	public void convertingIdToCode() {
		assertEquals("a", converter.convertIdToCode(1));
		assertEquals("b", converter.convertIdToCode(2));
	}
	
	@Test
	public void convertingCodeToId() {
		assertEquals(1, converter.convertCodeToId("a"));
		assertEquals(2, converter.convertCodeToId("b"));
	}

}