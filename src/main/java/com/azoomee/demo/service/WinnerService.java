package com.azoomee.demo.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.azoomee.demo.model.Employee;
import com.azoomee.demo.model.Winner;
import com.azoomee.demo.repository.WinnerRepository;

@Service
public class WinnerService {

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
	public void runForWinnerOfTheMonth() {
		final Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
			Employee winnerEmp = (Employee) entityManager
					.createNativeQuery("SELECT * FROM EMPLOYEES ORDER BY RAND()", Employee.class)
					.setMaxResults(1)
					.getSingleResult();

			Winner winner = new Winner(LocalDate.now().getMonth().getValue() + "" + LocalDate.now().getYear(),
					winnerEmp.getId(), winnerEmp.getFirstName().concat(" ").concat(winnerEmp.getLastName()));
			// System.out.println(winner.getFirstName());
			winnerRepository.save(winner);
		}

	}

}
