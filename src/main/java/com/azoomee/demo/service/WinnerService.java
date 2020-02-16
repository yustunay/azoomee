package com.azoomee.demo.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.Winner;
import com.azoomee.demo.repository.WinnerRepository;

@Service
public class WinnerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WinnerService.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private WinnerRepository winnerRepository;

	public Winner getWinnerByMonthYear(String monthYear) {
		return winnerRepository.findByMonthYear(monthYear);
	}

	public List<Winner> getAllWinners() {
		return winnerRepository.findAll();
	}

	@Scheduled(cron = "0 0 10 28-31 * ?") // Fire at 10:00 AM on the last day of every month
	private void runForWinnerOfTheMonth() {
		String monthYear = LocalDate.now().getMonth().getValue() + "" + LocalDate.now().getYear();
		this.runForWinnerOfTheMonth(monthYear, false);
	}

	public void runForWinnerOfTheMonth(String monthYear, boolean force) {
		Winner winner = winnerRepository.findByMonthYear(monthYear);

		if (winner == null) {
			final Calendar c = Calendar.getInstance();
			if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE) || force) {
				LOGGER.info("Running to get winner employee from database for the month-year : " + monthYear);

				Employee winnerEmp = (Employee) entityManager
						.createNativeQuery("SELECT * FROM EMPLOYEES ORDER BY RAND()", Employee.class).setMaxResults(1)
						.getSingleResult();

				if (winnerEmp == null) {
					LOGGER.warn("Could not get a winner employee from database for the month-year : " + monthYear);
				} else {
					winner = new Winner(monthYear, winnerEmp.getId(),
							winnerEmp.getFirstName().concat(" ").concat(winnerEmp.getLastName()));
					winnerRepository.save(winner);
				}
			}
		} else {
			LOGGER.warn("A winner is exist for this month-year : " + monthYear + ", It will not be processed again!");
		}
	}
}
