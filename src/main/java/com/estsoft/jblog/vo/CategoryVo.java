package com.estsoft.jblog.vo;

public class CategoryVo {
	private long category_id;
	private String category_name;
	private String description;
	private long postcount;
	private long blog_id;
	
	public long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getPostcount() {
		return postcount;
	}
	public void setPostcount(long postcount) {
		this.postcount = postcount;
	}
	public long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "CategoryVo [category_id=" + category_id + ", category_name="
				+ category_name + ", description=" + description
				+ ", postcount=" + postcount + ", blog_id=" + blog_id + "]";
	}
}
