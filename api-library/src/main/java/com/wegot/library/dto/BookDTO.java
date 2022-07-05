package com.wegot.library.dto;

import javax.persistence.Column;

public class BookDTO {

	private Long id;
	
	private String isbn;
	
	private String title;
	
	private String author;
	
	private String editorial;
	
	private Long page_number;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Long getPage_number() {
		return page_number;
	}

	public void setPage_number(Long page_number) {
		this.page_number = page_number;
	}

	
	
}
