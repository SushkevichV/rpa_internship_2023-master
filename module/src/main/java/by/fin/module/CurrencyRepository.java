package by.fin.module;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import by.fin.module.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	List<Currency> findByCurAbbreviation(String curAbbreviation);

	List<Currency> findByCurAbbreviationAndDateBetween(String curAbbreviation, LocalDate start, LocalDate end);
	
}
