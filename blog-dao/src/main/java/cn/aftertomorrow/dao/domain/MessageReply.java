package cn.aftertomorrow.dao.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MessageReply {
	private Integer id;
	private Integer guestmessage_id;
	private String name;
	private String email;
	private Date time;
	private String words;
}
