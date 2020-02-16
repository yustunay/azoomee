package com.azoomee.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.azoomee.demo.model.Winner;

@SpringBootTest
class WinnerRepositoryTest {
	
	@Autowired
	private WinnerRepository winnerRepository;

	@Test
	void testFindByMonthYear() {
		Winner winner = winnerRepository.findByMonthYear("12020");
		
		//Winner [id=1, monthYear=12020, empNo=1002, fullName=Diana Roberts]
		assertEquals(1, winner.getId());
		assertEquals("12020", winner.getMonthYear());
		assertEquals(1002, winner.getEmpNo());
		assertEquals("Diana Roberts", winner.getFullName());
	}

}
