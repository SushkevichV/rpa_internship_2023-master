package by.fin.module;


import by.fin.module.entity.Weekend;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekendsRepository extends JpaRepository<Weekend, Long> {

	List<Weekend> findByIsDayOffAndCalendarDateBetween(boolean isDayOff, Date startDate, Date endDate);
}
