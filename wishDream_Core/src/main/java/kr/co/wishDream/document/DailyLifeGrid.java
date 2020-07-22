package kr.co.wishDream.document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import kr.co.wishDream.domain.DailyListGridComment;
import kr.co.wishDream.domain.DailyListGridImage;
import kr.co.wishDream.request.PageAndSort;

@Document(collection = "dailyLifeGrid")
public class DailyLifeGrid extends PageAndSort implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@MongoId
	private ObjectId id;
	private Long no;
	private String title;
	private String content;
	private Author author;
	private List<DailyListGridImage> images;
	private List<DailyListGridComment> comments;
	private Date creDate;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public List<DailyListGridImage> getImages() {
		return images;
	}
	public void setImages(List<DailyListGridImage> images) {
		this.images = images;
	}
	public List<DailyListGridComment> getComments() {
		return comments;
	}
	public void setComments(List<DailyListGridComment> comments) {
		this.comments = comments;
	}
	public Date getCreDate() {
		return creDate;
	}
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
	
	
	
	
}
