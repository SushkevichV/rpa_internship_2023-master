package by.fin.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import by.fin.module.CurrencyRepository;
import by.fin.module.WeekendsRepository;
import by.fin.module.entity.Currency;
import by.fin.service.CurrencyService;
import by.fin.service.exception.CurrencyNotFoundException;
import by.fin.service.exception.InvalidDateException;
import by.fin.service.exception.NoCurrencyDataException;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private final CurrencyRepository currencyRepository;
	private final RestTemplate restTemplate;
	private final WeekendsRepository weekendsRepository;
	
	public CurrencyServiceImpl(CurrencyRepository currencyRepository, RestTemplateBuilder restTemplateBuilder, WeekendsRepository weekendsRepository) {
		this.currencyRepository = currencyRepository;
		this.restTemplate = restTemplateBuilder.build();
		this.weekendsRepository = weekendsRepository;
	}
	
	/* Загрузить курсы с сайта НБРБ и записать в БД
	 * По условиям задачи вернуть нужно массив курсов, которые были добавлены в БД
	 * По условиям задачи предполагается работа с курсами валют в диапазоне с 01.12.2022 по 31.05.2023.
	 */ 
	@Override
	public List<Currency> loadRatesForThePeriod(String curAbbreviation, LocalDate start, LocalDate end) {
		if(start.isAfter(end) || start.isBefore(LocalDate.of(2022, 12, 1))) {
			throw new InvalidDateException(start);
		}
		if(end.isAfter(LocalDate.of(2023, 5, 31))) {
			throw new InvalidDateException(end);
		}
		
		List<Currency> loadedCurrencies = currencyRepository.findByCurAbbreviationAndDateBetween(curAbbreviation, start, end);

		// https://api.nbrb.by/exrates/rates/USD?parammode=2
		String url = String.format("https://api.nbrb.by/exrates/rates/%s?parammode=2", curAbbreviation);
		Currency currency = null;
		try {
			currency = restTemplate.getForObject(url, Currency.class);
		} catch (HttpClientErrorException e) {
			throw new CurrencyNotFoundException(curAbbreviation);
		}

		List<Currency> currencies = null;
		String postfix = currency.getCurID() + "?startDate=" + start + "&endDate=" + end;
		// https://api.nbrb.by/ExRates/Rates/Dynamics/298?startDate=2021-07-01&endDate=2021-07-08
		url = String.format("https://api.nbrb.by/ExRates/Rates/Dynamics/%s", postfix);
		currencies = Arrays.asList(restTemplate.getForObject(url, Currency[].class)); 

		for (Currency item : currencies) {
			item.setCurAbbreviation(currency.getCurAbbreviation());
			item.setCurName(currency.getCurName());
			item.setCurScale(currency.getCurScale());
		}
		
		List<Currency> newCurrencies = new ArrayList<>(currencies); // currencies is unmodifiable, его изменение вызовет UnsupportedOperationException. Поэтому создаю новый List
		newCurrencies.removeAll(loadedCurrencies);
		return currencyRepository.saveAll(newCurrencies);
	}

	// Получить все курсы заданной валюты из БД
	@Override
	public List<Currency> getCurrencyRates(String curAbbreviation) {
		List<Currency> currencies = currencyRepository.findByCurAbbreviation(curAbbreviation);
		if(currencies.size() == 0) {
			throw new CurrencyNotFoundException(curAbbreviation);
		}
		return currencies;
	}

	/* Получить среднегеометрический курс валюты за месяц
	 * В задаче не указано, из каких источников брать данные для расчета. Беру из БД.
	 * В задаче сказано, средний курс = среднегеометрическое от ежедневных курсов за заданный месяц исключая выходные дни.
	 *  А в примере выходные не исключаются (учтены четыре дня вместо двух). Сделал по инструкции.
	 */
	@Override
	public Currency getAverageExchangeRatePerMonth(String curAbbreviation, int month, int year) {
		if(month > 12 || month < 1) {
			throw new InvalidDateException(year + "-" + month + "-" + 01);
		}
		if(year > LocalDate.now().getYear() || year < 2022) {
			throw new InvalidDateException(year + "-" + month + "-" + 01);
		}
		if(year == LocalDate.now().getYear() && month > LocalDate.now().getMonthValue()) {
			throw new InvalidDateException(LocalDate.of(year, month, 1));
		}
		
		LocalDate date = LocalDate.of(year, month, 1);
		List<Currency> currencies = currencyRepository.findByCurAbbreviationAndDateBetween(curAbbreviation, date, date.plusDays(date.lengthOfMonth()-1));
		if(currencies == null || currencies.size() == 0) {
			throw new CurrencyNotFoundException(curAbbreviation);
		}
		
		List<LocalDate> dates = weekendsRepository.findByIsDayOffAndCalendarDateBetween(false, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(date.plusDays(date.lengthOfMonth()-1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.stream()
				//.filter(i -> i.isDayOff() == false)
				.map(i -> i.getCalendarDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
				.toList(); // Нужна Java 17

		double average = 1.0;
		int count = 0;
		for(Currency item : currencies) {
			if(dates.contains(item.getDate())) {
				average *= item.getCurOfficialRate();
				count++;
			}
		}
		if(count == 0) {
			throw new NoCurrencyDataException(curAbbreviation);
		}
		Currency currency = currencies.get(currencies.size()-1);
		currency.setCurOfficialRate(Math.pow(average, 1.0/count));
		currency.setCurAbbreviation("Average" + curAbbreviation);
		currency.setCurName("Средний курс валюты " + currency.getCurName());
		return currency;
	}
	
}
