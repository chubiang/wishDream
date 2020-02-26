package kr.co.wishDream.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DailyLifeGrid {

	private Long no;
	private String title;
	private List<DailyListGridImage> image;
	
}
