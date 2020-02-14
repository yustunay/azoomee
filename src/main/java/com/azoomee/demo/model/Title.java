package com.azoomee.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "titles")
public class Title implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "title_id")
	private long id;

	private String titleCode;	
	private String title;
	private LocalDate fromDate;
	private LocalDate toDate;
	
	public Title() {}

	public Title(String titleCode, String title, LocalDate fromDate, LocalDate toDate) {
		this.titleCode = titleCode;
		this.title = title;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	@Override
	public String toString() {
		return "Title [id=" + id + ", titleCode=" + titleCode + ", title=" + title + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}

}
