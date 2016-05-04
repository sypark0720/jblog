package com.estsoft.jblog.vo;

public class BlogVo {
	private long blog_id;
	private String title;
	private String logo;
	private long user_id;
	
	public long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "BlogVo [blog_id=" + blog_id + ", title=" + title + ", logo="
				+ logo + ", user_id=" + user_id + "]";
	}	
	
}
