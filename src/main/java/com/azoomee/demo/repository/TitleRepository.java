package com.azoomee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azoomee.demo.model.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
	Title findByTitleCode(String titleCode);
}
