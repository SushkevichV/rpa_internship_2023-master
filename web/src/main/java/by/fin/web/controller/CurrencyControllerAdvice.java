package by.fin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import by.fin.service.exception.CurrencyNotFoundException;
import by.fin.service.exception.InvalidDateException;
import by.fin.service.exception.NoCurrencyDataException;

@RestControllerAdvice()
public class CurrencyControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyControllerAdvice.class);
	
	@ResponseBody
	@ExceptionHandler(CurrencyNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String currencyNotFoundHandler(CurrencyNotFoundException e) {
		LOGGER.warn(e.getMessage(), e);
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidDateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String invalidDateHandler(InvalidDateException e) {
		LOGGER.warn(e.getMessage(), e);
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(NoCurrencyDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String NoCurrencyDataHandler(NoCurrencyDataException e) {
		LOGGER.warn(e.getMessage(), e);
		return e.getMessage();
	}

}
