package com.arthur.catholicbible;

import java.io.Serializable;

public class Verse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int chapterId;
	private int chapterIndex;
	private int index;
	private String content;

	public Verse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Verse(int id, String content, int index, int chapterId, int chapterIndex) {
		super();
		this.id = id;
		this.chapterId = chapterId;
		this.chapterIndex = chapterIndex;
		this.index = index;
		this.content = content;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
	
	public int getChapterIndex() {
		return chapterIndex;
	}
	
	public void setChapterIndex(int chapterIndex) {
		this.chapterIndex = chapterIndex;
	}

	@Override
	public String toString() {
		return index == 0 ? content : chapterIndex + ":" + index + " " + content;
	}
}
