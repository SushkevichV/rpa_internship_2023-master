package by.fin.service.exception;

public class CurrencyNotFoundException extends RuntimeException {
	
	public CurrencyNotFoundException(String curAbbreviation) {
		super("Invalid currency symbolic code: " + curAbbreviation);
	}

}
