package kr.co.wishDream.domain;

import java.util.Date;

public class NoticeMessage {

	private String title;
	private String message;
	private Long fromMember;
	private Long toMember;
	private String fromMemberName;
	private String toMemberName;
	private Long noticeId;
	private Integer noticeType;
	private Date sendDate;
	
	public NoticeMessage() {}
	
	public NoticeMessage(String title, String message, Long fromMember, Long toMember, String fromMemberName,
			String toMemberName, Long noticeId, Integer noticeType, Date sendDate) {
		super();
		this.title = title;
		this.message = message;
		this.fromMember = fromMember;
		this.toMember = toMember;
		this.fromMemberName = fromMemberName;
		this.toMemberName = toMemberName;
		this.noticeId = noticeId;
		this.noticeType = noticeType;
		this.sendDate = sendDate;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getFromMember() {
		return fromMember;
	}
	public void setFromMember(Long fromMember) {
		this.fromMember = fromMember;
	}
	public Long getToMember() {
		return toMember;
	}
	public void setToMember(Long toMember) {
		this.toMember = toMember;
	}
	public String getFromMemberName() {
		return fromMemberName;
	}
	public void setFromMemberName(String fromMemberName) {
		this.fromMemberName = fromMemberName;
	}
	public String getToMemberName() {
		return toMemberName;
	}
	public void setToMemberName(String toMemberName) {
		this.toMemberName = toMemberName;
	}
	public Long getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
	
}
