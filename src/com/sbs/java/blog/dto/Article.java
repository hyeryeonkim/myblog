package com.sbs.java.blog.dto;

import java.util.Map;

public class Article extends dto {
	private String updateDate;
	private int cateItemId;
	private String title;
	private String body;
	
	public Article() {
		
	}
	
	//row들의 값들을 형변환 해주는 이유는 Map은 Object 타입을 리턴하기 때문!!
	//필요한, 맞는 타입으로 꼭 형변환을 해주어야 한다! 
	public Article(Map<String, Object> row) {
		super(row);
		this.updateDate = (String)row.get("updateDate");
		this.cateItemId = (int)row.get("cateItemId");
		this.title = (String)row.get("title");
		this.body = (String)row.get("body");
	}
	
	
	
	@Override
	public String toString() {
		return "Article [updateDate=" + updateDate + ", cateItemId=" + cateItemId + ", title=" + title + ", body="
				+ body + ", getId()=" + getId() + ", getRegDate()=" + getRegDate() + "]";
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getCateItemId() {
		return cateItemId;
	}

	public void setCateItemId(int cateItemId) {
		this.cateItemId = cateItemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
