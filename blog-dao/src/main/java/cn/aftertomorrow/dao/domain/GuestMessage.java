package cn.aftertomorrow.dao.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 留言
 *
 * @author huangming
 * @date 2019/09/26
 */
@Data
public class GuestMessage {
    private Integer id;
    private String name;
    private String email;
    private String words;
    private Date time;
    private List<MessageReply> messageReplys;
}
