package com.azoomee.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WinnerTest {

	@Test
	void shouldBeSetTitleFields() {
		Winner winner = new Winner();
		winner.setId(1);
		winner.setEmpNo(1002);
		winner.setFullName("Diana R. Roberts");
		winner.setMonthYear("22020");
		
		assertEquals(1, winner.getId());
		assertEquals(1002, winner.getEmpNo());
		assertEquals("Diana R. Roberts", winner.getFullName());
		assertEquals("22020", winner.getMonthYear());
	}

}
