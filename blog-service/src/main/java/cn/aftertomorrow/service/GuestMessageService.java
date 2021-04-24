package cn.aftertomorrow.service;

import java.util.List;

import cn.aftertomorrow.common.request.dto.message.GuestMessageDTO;
import cn.aftertomorrow.dao.domain.GuestMessageDO;

/**
 * 留言服务类接口
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface GuestMessageService {
    /**
     * 添加留言
     *
     * @param guestMessage
     * @return
     */
    GuestMessageDTO addGuestMessage(GuestMessageDTO guestMessage);

    /**
     * 获取所有留言
     *
     * @return
     */
    List<GuestMessageDTO> listAll();

    /**
     * 删除留言
     *
     * @param ids
     * @return
     */
    int deleteMessage(Integer[] ids);
}
