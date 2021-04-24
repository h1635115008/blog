package cn.aftertomorrow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.common.request.dto.message.GuestMessageDTO;
import cn.aftertomorrow.common.transfer.FieldRule;
import cn.aftertomorrow.common.transfer.ValueParser;
import cn.aftertomorrow.common.util.JavaBeanUtils;
import cn.aftertomorrow.dao.domain.GuestMessageDO;
import cn.aftertomorrow.dao.mapper.GuestMessageMapper;
import cn.aftertomorrow.service.GuestMessageService;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.util.PojoUtil;

/**
 * 留言服务类实现类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Service
@Transactional
public class GuestMessageServiceImpl implements GuestMessageService {
    @Autowired
    private GuestMessageMapper guestMessageMapper;

    @Override
    public GuestMessageDTO addGuestMessage(GuestMessageDTO guestMessage) {
        GuestMessageDO guestMessageDO = JavaBeanUtils.copyPropertiesToObject(guestMessage, GuestMessageDO.class);
        this.guestMessageMapper.addGuestMessage(guestMessageDO);
        List<FieldRule> fieldRules = Lists.newArrayList(
                FieldRule.create("gmtCreate", "time", ValueParser.DATE_PARSER_TO_YMDHMS)
        );
        return JavaBeanUtils.copyPropertiesToObject(guestMessageMapper.findGuestMessageById(guestMessageDO.getId()), GuestMessageDTO.class, fieldRules);
    }

    @Override
    public List<GuestMessageDTO> listAll() {
        List<FieldRule> fieldRules = Lists.newArrayList(
                FieldRule.create("gmtCreate", "time")
        );
        List<GuestMessageDTO> guestMessageDTOList = JavaBeanUtils.copyPropertiesToList(this.guestMessageMapper.listAll(), GuestMessageDTO.class, fieldRules);
        // 辅助查找map
        Map<Integer, GuestMessageDTO> guestMessageDTOMap = new HashedMap(16);
        guestMessageDTOList.forEach(e -> {
            guestMessageDTOMap.put(e.getId(), e);
            GuestMessageDTO parentGuestMessage = guestMessageDTOMap.get(e.getParentId());
            if (ObjectUtil.isNotEmpty(parentGuestMessage)) {
                e.setParentUserName(parentGuestMessage.getName());
            }
            Integer rootNodeId = getRootNodeId(guestMessageDTOMap, e.getParentId());
            e.setRootNodeId(rootNodeId);
        });
        return guestMessageDTOList;
    }

    @Override
    public int deleteMessage(Integer[] ids) {
        return 0;
    }

    /**
     * 递归获取顶级节点
     *
     * @param guestMessageMap
     * @param parentId
     * @return
     */
    private Integer getRootNodeId(Map<Integer, GuestMessageDTO> guestMessageMap, Integer parentId) {
        GuestMessageDTO rootGuestMessage = guestMessageMap.get(parentId);
        if (ObjectUtil.isEmpty(rootGuestMessage)) {
            return 0;
        } else if (rootGuestMessage.getParentId() == 0) {
            return rootGuestMessage.getId();
        }
        return getRootNodeId(guestMessageMap, rootGuestMessage.getParentId());
    }
}
