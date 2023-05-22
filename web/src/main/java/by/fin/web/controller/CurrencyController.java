package by.fin.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.fin.module.entity.Currency;
import by.fin.service.CurrencyService;

// Для CurrencyController есть тестовый класс

@RestController
@RequestMapping("api/currencies")
public class CurrencyController {
	private final CurrencyService currencyService;
	
	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	// Загрузить курсы с сайта НБРБ и записать в БД
	// По условиям задачи вернуть нужно массив курсов, которые были добавлены в БД
	// пример запроса: /api/currencies/load/USD?start=2023-01-01&end=2023-01-31
	@GetMapping("/load/{curAbbreviation}")
	public ResponseEntity<List<Currency>> loadRatesForThePeriod(
			@PathVariable String curAbbreviation,
			@RequestParam(required = true) LocalDate start, 
			@RequestParam(required = true) LocalDate end) {
		return new ResponseEntity<>(currencyService.loadRatesForThePeriod(curAbbreviation, start, end), HttpStatus.OK);
	}
	
	// Получить все курсы заданной валюты из БД
	// пример запроса: /api/currencies/USD
	@GetMapping("/{curAbbreviation}")
	public ResponseEntity<List<Currency>>getCurrencies(@PathVariable String curAbbreviation){
	    return new ResponseEntity<>(currencyService.getCurrencyRates(curAbbreviation), HttpStatus.OK);
	}
	
	/* Получить среднегеометрический курс валюты за месяц
	 * Если год не указан, принимается текущий год
	 * пример запроса: /api/currencies/average/USD?month=1&year=2023
	 * В задаче не указано, из каких источников брать данные для расчета. Беру из БД.
	 * В задаче сказано, средний курс = среднегеометрическое от ежедневных курсов за заданный месяц исключая выходные дни.
	 *  А в примере выходные не исключаются (учтены четыре дня вместо двух). Сделал по инструкции.
	 */
	@GetMapping("/average/{curAbbreviation}")
    public ResponseEntity<Currency>getAverageExchangeRatePerMonth(
    		@PathVariable String curAbbreviation, 
    		@RequestParam(required = true) Integer month, 
			@RequestParam(required = false) Integer year){
		if(year == null) {
			year = LocalDate.now().getYear();
		}
        return new ResponseEntity<>(currencyService.getAverageExchangeRatePerMonth(curAbbreviation, month, year), HttpStatus.OK);
    }
	
}
