package com.azoomee.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OfficeTest {

	@Test
	void shouldBeSetOfficeFields() {
		Address address = new Address.AddressBuilder().setLine1("54 ROSEBANK AVE")
													  .setZipOrPostcode("HA0 2TW")
													  .setStateProvinceCounty("WEMBLEY")
													  .setCountryId("UK")
													  .build();
		
		Office office = new Office();
		office.setName("New Location");
		office.setAddress(address);
				
		assertEquals("New Location", office.getName());
		assertEquals("54 ROSEBANK AVE",office.getAddress().getLine1());
		assertEquals("HA0 2TW",office.getAddress().getZipOrPostcode());
		assertEquals("WEMBLEY",office.getAddress().getStateProvinceCounty());
		assertEquals("UK",office.getAddress().getCountryId());
	}

}
