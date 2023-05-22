package by.fin.service;

import java.time.LocalDate;
import java.util.List;

import by.fin.module.entity.Currency;

public interface CurrencyService {

	// Загрузить курсы с сайта НБРБ, записать в БД и вернуть те, которых не было в БД
	List<Currency> loadRatesForThePeriod(String curAbbraviation, LocalDate start, LocalDate end);

	// Получить все курсы заданной валюты из БД
	List<Currency> getCurrencyRates(String curAbbraviation);

	// Получить среднегеометрический курс валюты за месяц
	Currency getAverageExchangeRatePerMonth(String curAbbraviation, int month, int year);
	
}
