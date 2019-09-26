package cn.aftertomorrow.po;

import java.util.Date;
import java.util.List;

public class GuestMessage {
	private Integer id;
	private String name;
	private String email;
	private String words;
	private Date time;
	private List<MessageReply> messageReplys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<MessageReply> getMessageReplys() {
		return messageReplys;
	}

	public void setMessageReplys(List<MessageReply> messageReplys) {
		this.messageReplys = messageReplys;
	}

	@Override
	public String toString() {
		return "GuestMessage [id=" + id + ", name=" + name + ", email=" + email + ", words=" + words + ", time=" + time
				+ ", messageReplys=" + messageReplys + "]";
	}

}
