package cn.aftertomorrow.dao.mapper;

import cn.aftertomorrow.dao.domain.GuestMessageDO;

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
     * @param guestMessageDO
     * @return
     */
    int addGuestMessage(GuestMessageDO guestMessageDO);

    /**
     * 获取所有留言
     *
     * @return
     */
    List<GuestMessageDO> listAll();

    /**
     * 通过id获取评论
     *
     * @param id
     * @return
     */
    GuestMessageDO findGuestMessageById(Integer id);
}
