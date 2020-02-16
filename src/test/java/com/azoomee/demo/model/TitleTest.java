package com.azoomee.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TitleTest {

	@Test
	void shouldBeSetTitleFields() {
		Title title = new Title();
		title.setId(1);
		title.setTitle("Senior Developer");
		title.setTitleCode("SEN01");
		title.setFromDate(LocalDate.of(2020,02,12));
		title.setToDate(LocalDate.of(2022,02,12));
		
		assertEquals(1, title.getId());
		assertEquals("SEN01", title.getTitleCode());
		assertEquals(LocalDate.of(2020, 2, 12), title.getFromDate());
		assertEquals(LocalDate.of(2022, 2, 12), title.getToDate());
	}

}
