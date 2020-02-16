package com.azoomee.demo.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.azoomee.demo.model.Winner;

@SpringBootTest
@Transactional
class WinnerServiceTest {

	@Autowired
	private WinnerService winnerService;
	
	@Test
	@Rollback(true)
	void testRunForWinnerOfTheMonth() {		
		Winner winner = winnerService.getWinnerByMonthYear("122019");
		assertNull(winner);		
		winnerService.runForWinnerOfTheMonth("122019",true);
		winner = winnerService.getWinnerByMonthYear("122019");
		assertTrue(winner!=null);
	}

}
