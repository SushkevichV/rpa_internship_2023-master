package by.fin.service.exception;

import java.time.LocalDate;

public class InvalidDateException extends RuntimeException {
	
	public InvalidDateException(LocalDate date) {
		super("Invalid date: " + date);
	}
	
	public InvalidDateException(String s) {
		super("Invalid date: " + s);
	}
}
