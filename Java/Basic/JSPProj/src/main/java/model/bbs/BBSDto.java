package model.bbs;

import java.sql.Date;

public class BBSDto {//DTO(Data Tranfer Object):데이타를 전송하는 객체로 테이블의 레코드 하나를 저장할 수 있는 자료구조
	
	private String no;
	private String id;
	private String title;
	private String content;
	private String hitCount;
	private Date postDate;
	private String name;//SELECT시 이름 저장용, 아래 게터/세터 추가함
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//생성자]
	public BBSDto() {}
	public BBSDto(String no, String id, String title, String content, String hitCount, Date postDate) {
		
		this.no = no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.hitCount = hitCount;
		this.postDate = postDate;
	}
	//게터/세터]
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
