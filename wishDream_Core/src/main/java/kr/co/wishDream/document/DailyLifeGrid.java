package kr.co.wishDream.document;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import kr.co.wishDream.domain.Author;
import kr.co.wishDream.domain.DailyListGridComment;
import kr.co.wishDream.domain.DailyListGridImage;
import lombok.Data;

@Data
@Document(collection = "DailyLifeGrid")
public class DailyLifeGrid implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private Long no;
	private String title;
	private String content;
	private Author author;
	private List<DailyListGridImage> images;
	private List<DailyListGridComment> comments;
	
}
