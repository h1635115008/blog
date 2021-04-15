package cn.aftertomorrow.dao.mapper;

import cn.aftertomorrow.dao.domain.GuestMessage;

import java.util.List;

/**
 * 留言mapper
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface GuestMessageMapper {
    /**
     * 新增留言
     *
     * @param guestMessage
     * @return
     */
    int addGuestMessage(GuestMessage guestMessage);

    /**
     * 获取所有留言
     *
     * @return
     */
    List<GuestMessage> listAll();
}
