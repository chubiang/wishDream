package kr.co.wishDream.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("image")
public class DailyListGridImage {

	private String img;
}
