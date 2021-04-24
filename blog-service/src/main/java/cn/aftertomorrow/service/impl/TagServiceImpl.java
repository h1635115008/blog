package cn.aftertomorrow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.common.request.dto.tag.TagDTO;
import cn.aftertomorrow.common.util.JavaBeanUtils;
import cn.aftertomorrow.dao.domain.TagDO;
import cn.aftertomorrow.dao.mapper.TagMapper;
import cn.aftertomorrow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标签服务类实现类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagDTO> listAll() {
        return JavaBeanUtils.copyPropertiesToList(tagMapper.listAll(), TagDTO.class);
    }

    @Override
    public Map<String, List<TagDTO>> getTagCollectionByKind() {
        Map<String, List<TagDTO>> tagCollection = new HashMap<>();
        List<TagDO> tags = tagMapper.listAll();
        tags.forEach(e -> {
            List<TagDTO> tagList;
            if (null == tagCollection.get(e.getName())) {
                tagList = new ArrayList<>();
                tagList.add(JavaBeanUtils.copyPropertiesToObject(e, TagDTO.class));
                tagCollection.put(e.getName(), tagList);
            } else {
                tagList = tagCollection.get(e.getName());
                tagList.add(JavaBeanUtils.copyPropertiesToObject(e, TagDTO.class));
            }
        });
        return tagCollection;
    }

}
