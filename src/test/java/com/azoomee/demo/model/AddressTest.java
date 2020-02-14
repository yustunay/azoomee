package com.azoomee.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddressTest {

	@Test
	void shouldBeSetAddressFields() {
		Address address = new Address.AddressBuilder().setLine1("54 ROSEBANK AVE")
													  .setZipOrPostcode("HA0 2TW")
													  .setStateProvinceCounty("WEMBLEY")
													  .setCountryId("UK")
													  .build();
		
		assertEquals("54 ROSEBANK AVE",address.getLine1());
		assertEquals("HA0 2TW",address.getZipOrPostcode());
		assertEquals("WEMBLEY",address.getStateProvinceCounty());
		assertEquals("UK",address.getCountryId());
	}

}
