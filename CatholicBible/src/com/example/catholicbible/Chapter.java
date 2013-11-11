package com.example.catholicbible;

import java.io.Serializable;

public class Chapter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int bookId;
	private int index;
	private String name;

	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chapter(int id, int index, String name, int bookId) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.index = index;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
