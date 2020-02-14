package com.azoomee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azoomee.demo.model.Winner;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long>{
	//@Query("SELECT w FROM Winner w WHERE w.monthYear = ?1")
	Winner findByMonthYear(String monthYear);
}
