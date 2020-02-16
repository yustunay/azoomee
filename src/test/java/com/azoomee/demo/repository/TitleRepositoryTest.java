package com.azoomee.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.azoomee.demo.model.Title;

@SpringBootTest
class TitleRepositoryTest {

	@Autowired
	private TitleRepository titleRepository;
	
	@Test
	void testFindByTitleCode() {
		Title title = titleRepository.findByTitleCode("MSRDEV");
		
		//insert into titles(title_id, title_code, title, from_date, to_date)
		//values(2, 'MSRDEV', 'Mid-Senior Developer', sysdate(), null);
		
		assertEquals(2, title.getId());
		assertEquals("Mid-Senior Developer", title.getTitle());
		assertEquals(null, title.getToDate());
	}

}
