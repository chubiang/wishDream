package kr.co.wishDream.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeMessage {

	private String title;
	private String message;
	private Integer fromMember;
	private Integer toMember;
	private String fromMemberName;
	private String toMemberName;
	private Integer noticeId;
	private Integer noticeType;
	private Date sendDate;
	
	
}
