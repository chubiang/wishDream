package kr.co.wishDream.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuType {

	Integer id;
	
	Integer menuType;
	
	String name;
	
	String authority;
}
