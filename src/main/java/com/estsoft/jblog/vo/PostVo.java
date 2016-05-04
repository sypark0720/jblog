package com.estsoft.jblog.vo;

public class PostVo {
	private long poser_id;
	private String title;
	private String content;
	private String regdate;
	private long category_id;
	
	public long getPoser_id() {
		return poser_id;
	}
	public void setPoser_id(long poser_id) {
		this.poser_id = poser_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	
	@Override
	public String toString() {
		return "PostVo [poser_id=" + poser_id + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate
				+ ", category_id=" + category_id + "]";
	}
}
