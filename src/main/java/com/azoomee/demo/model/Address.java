package com.azoomee.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String line1;
	private String line2;
	private String line3;
	private String city;
	private String zipOrPostcode;
	private String stateProvinceCounty;
	private String countryId;
	private String otherAddressDetails;

	public Address() {}
	
	public Address(AddressBuilder builder) {
		line1 = builder.line1;
		line2 = builder.line2;
		line3 = builder.line3;
		city = builder.city;
		zipOrPostcode = builder.zipOrPostcode;
		stateProvinceCounty = builder.stateProvinceCounty;
		countryId = builder.countryId;
		otherAddressDetails = builder.otherAddressDetails;
	}

	public long getId() {
		return id;
	}

	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getLine3() {
		return line3;
	}

	public String getCity() {
		return city;
	}

	public String getZipOrPostcode() {
		return zipOrPostcode;
	}

	public String getStateProvinceCounty() {
		return stateProvinceCounty;
	}

	public String getCountryId() {
		return countryId;
	}

	public String getOtherAddressDetails() {
		return otherAddressDetails;
	}
	
	public static class AddressBuilder{
		private String line1;
		private String line2;
		private String line3;
		private String city;
		private String zipOrPostcode;
		private String stateProvinceCounty;
		private String countryId;
		private String otherAddressDetails;
		
		public AddressBuilder setLine1(String line1) {
			this.line1 = line1;
			return this;
		}

		public AddressBuilder setLine2(String line2) {
			this.line2 = line2;
			return this;
		}
		
		public AddressBuilder setLine3(String line3) {
			this.line3 = line3;
			return this;
		}
		
		public AddressBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		public AddressBuilder setZipOrPostcode(String zipOrPostcode) {
			this.zipOrPostcode = zipOrPostcode;
			return this;
		}

		public AddressBuilder setStateProvinceCounty(String stateProvinceCounty) {
			this.stateProvinceCounty = stateProvinceCounty;
			return this;
		}

		public AddressBuilder setCountryId(String countryId) {
			this.countryId = countryId;
			return this;
		}

		public AddressBuilder setOtherAddressDetails(String otherAddressDetails) {
			this.otherAddressDetails = otherAddressDetails;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
		
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", line1=" + line1 + ", line2=" + line2 + ", line3=" + line3 + ", city=" + city
				+ ", zipOrPostcode=" + zipOrPostcode + ", stateProvinceCounty=" + stateProvinceCounty + ", countryId="
				+ countryId + ", otherAddressDetails=" + otherAddressDetails + "]";
	}
	
}
