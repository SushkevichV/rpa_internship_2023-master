package by.fin.service.exception;

public class NoCurrencyDataException extends RuntimeException{

	public NoCurrencyDataException(String curAbbreviation) {
		super("No currency data: " + curAbbreviation);
	}

}
