package kr.co.wishDream.document;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import kr.co.wishDream.domain.Author;
import kr.co.wishDream.domain.DailyListGridComment;
import kr.co.wishDream.domain.DailyListGridImage;
import kr.co.wishDream.request.PageAndSort;
import lombok.Data;

@Data
@Document(collection = "DailyLifeGrid")
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
	
	
	
}
