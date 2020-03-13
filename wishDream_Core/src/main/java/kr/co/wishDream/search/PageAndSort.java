package kr.co.wishDream.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageAndSort {
	
	static final Integer FIRST_PAGE_NUMBER = 0;
	static final Integer DEFAILT_PAGE_SIZE = 500;
	
	public PageAndSort() {
		PageRequest.of(FIRST_PAGE_NUMBER, DEFAILT_PAGE_SIZE, Sort.by());
	}
	
	

}
