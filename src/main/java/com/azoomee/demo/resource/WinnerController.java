package com.azoomee.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.azoomee.demo.model.Winner;
import com.azoomee.demo.service.WinnerService;

@Controller
public class WinnerController {
	
	@Autowired
	private WinnerService winnerService;

	@GetMapping(path = "/getWinner/{monthYear}")
	public @ResponseBody Winner getWinnerByMonthYear(@PathVariable String monthYear) {
		return winnerService.getWinnerByMonthYear(monthYear);
	}
	
	@GetMapping(path = "/getAllWinners")
	public @ResponseBody List<Winner> getAllWinners() {
		return winnerService.getAllWinners();
	}
}
