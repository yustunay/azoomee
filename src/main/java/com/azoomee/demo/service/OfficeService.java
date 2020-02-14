package com.azoomee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.azoomee.demo.model.Office;
import com.azoomee.demo.repository.OfficeRepository;

@Service
public class OfficeService {
	
	@Autowired
	private OfficeRepository officeRepository;
	
	public @ResponseBody List<Office> getAllOffices() {
		return officeRepository.findAll();
	}

	public Office getOfficeById(long id) {
		return officeRepository.findById(id).orElse(new Office());
	}

	public Office createOffice(Office office) {		
		return officeRepository.save(office);
	}

	public Office updateOffice(long id, Office office) {
		return officeRepository.save(office);
	}

	public void deleteOfficeById(long id) {
		officeRepository.deleteById(id);		
	}

}
