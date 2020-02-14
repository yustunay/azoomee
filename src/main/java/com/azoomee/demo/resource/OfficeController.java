package com.azoomee.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.azoomee.demo.model.Office;
import com.azoomee.demo.service.OfficeService;

@Controller
public class OfficeController {

	@Autowired
	private OfficeService officeService;

	@GetMapping(path = "/getAllOffices")
	public @ResponseBody List<Office> getAllOffices() {
		return officeService.getAllOffices();
	}

	@GetMapping(path = "/getOffice/{id}")
	public Office getOfficeById(@PathVariable long id) {
		return officeService.getOfficeById(id);
	}
	
	@PostMapping(path = "/createOffice")
	public Office createOffice(@RequestBody Office office) {
		return officeService.createOffice(office);
	}

	@PutMapping("/updateOffice/{id}")
	public ResponseEntity<Office> updateOffice(@PathVariable long id, @RequestBody Office office) {
		Office updatedOffice = officeService.updateOffice(id, office);
		return new ResponseEntity<Office>(updatedOffice, HttpStatus.OK);
	}

	@DeleteMapping("/deleteOffice/{id}")
	public ResponseEntity<Void> deleteOffice(@PathVariable long id) {
		officeService.deleteOfficeById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
