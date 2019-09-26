package cn.aftertomorrow.po;

import java.util.Date;

public class MessageReply {
	private Integer id;
	private Integer guestmessage_id;
	private String name;
	private String email;
	private Date time;
	private String words;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGuestmessage_id() {
		return guestmessage_id;
	}

	public void setGuestmessage_id(Integer guestmessage_id) {
		this.guestmessage_id = guestmessage_id;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "MessageReply [id=" + id + ", guestmessage_id=" + guestmessage_id + ", name=" + name + ", email=" + email
				+ ", time=" + time + ", words=" + words + "]";
	}

}
