package com.azoomee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azoomee.demo.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>{

}
