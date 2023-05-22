package by.fin.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import by.fin.module.entity.Currency;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {

	@Value(value = "${local.server.port}")
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testLoadRatesForThePeriod() {
		Currency[] currencies = this.restTemplate.getForObject("http://localhost:" + port + "/api/currencies/load/USD?start=2023-01-01&end=2023-01-10", Currency[].class);
		assertThat(currencies).hasSize(10);
		assertEquals("USD", currencies[0].getCurAbbreviation());
		assertEquals(2.7364, currencies[0].getCurOfficialRate());
		
		Currency[] updCurrencies = this.restTemplate.getForObject("http://localhost:" + port + "/api/currencies/load/USD?start=2023-01-01&end=2023-01-15", Currency[].class);
		assertThat(updCurrencies).hasSize(5);
	}
	
	@Test
	public void testLoadRatesForThePeriodStatusOk() throws Exception {
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/load/USD?start=2023-02-01&end=2023-02-10"))
				.andExpect(status().isOk());
	}
	
	@Test
	void testGetCurrencies() throws Exception{
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/load/EUR?start=2023-01-01&end=2023-01-31"))
				.andExpect(status().isOk());
		Currency[] currencies = this.restTemplate.getForObject("http://localhost:" + port + "/api/currencies/EUR", Currency[].class);
		assertThat(currencies).hasSize(31);
		assertEquals("EUR", currencies[10].getCurAbbreviation());
		assertEquals(2.8856, currencies[10].getCurOfficialRate());
	}

	@Test
	void testGetAverageExchangeRatePerMonth() throws Exception {
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/load/USD?start=2023-02-01&end=2023-03-10"))
				.andExpect(status().isOk());
		Currency currency = this.restTemplate.getForObject("http://localhost:" + port + "/api/currencies/average/USD?month=2", Currency.class);
		assertThat(currency).isNotNull();
		assertEquals(2.7554, currency.getCurOfficialRate(), 0.0001);
	}
	
	// тест на вызов ошибки, если объект не найден
	@Test()
	public void testCurrencyNotFound() throws Exception {
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/load/USR?start=2023-01-01&end=2023-01-10")).andExpect(status().isNotFound());
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/USR")).andExpect(status().isNotFound());
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/average/US?month=1")).andExpect(status().isNotFound());
	}
	
	// тест на вызов ошибки, если дата или диапазон заданы неверно
	@Test
	public void testInvalidDate() throws Exception {
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/load/USD?start=2023-02-01&end=2023-01-10")).andExpect(status().isBadRequest());
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/average/USD?month=-1")).andExpect(status().isBadRequest());
		mockMvc.perform(get("http://localhost:" + port + "/api/currencies/average/USD?month=1&year=2021")).andExpect(status().isBadRequest());
	}

}
