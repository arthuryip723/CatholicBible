package com.example.catholicbible;

import java.io.Serializable;

public class Chapter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int bookId;
	private int num;
	private String name;

	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chapter(int id, int bookId, int num, String name) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.num = num;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
