package com.example.catholicbible;

import java.io.Serializable;

public class Verse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int chapterId;
	private int num;
	private String content;

	public Verse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Verse(int id, int chapterId, int num, String content) {
		super();
		this.id = id;
		this.chapterId = chapterId;
		this.num = num;
		this.content = content;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	@Override
	public String toString() {
		return content;
	}
}
