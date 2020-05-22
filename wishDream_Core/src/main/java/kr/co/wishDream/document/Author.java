package kr.co.wishDream.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Author {
	private String username;
	private String email;
}