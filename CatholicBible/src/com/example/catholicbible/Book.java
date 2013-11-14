package com.example.catholicbible;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int numOfChapters;

	public int getNumOfChapters() {
		return numOfChapters;
	}

	public void setNumOfChapters(int numOfChapters) {
		this.numOfChapters = numOfChapters;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String name, int numOfChapters) {
		super();
		this.id = id;
		this.name = name;
		this.numOfChapters = numOfChapters;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
