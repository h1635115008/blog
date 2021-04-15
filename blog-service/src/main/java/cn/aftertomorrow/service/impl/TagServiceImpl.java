package cn.aftertomorrow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.dao.domain.Tag;
import cn.aftertomorrow.dao.mapper.TagMapper;
import cn.aftertomorrow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> listAll() {
        return tagMapper.listAll();
    }

    @Override
    public Map<String, List<Tag>> getTagCollectionByKind() {
        Map<String, List<Tag>> tagCollection = new HashMap<>();
        List<Tag> tags = tagMapper.listAll();
        tags.forEach(e -> {
            List<Tag> tagList;
            if (null == tagCollection.get(e.getName())) {
                tagList = new ArrayList<>();
                tagList.add(e);
                tagCollection.put(e.getName(), tagList);
            } else {
                tagList = tagCollection.get(e.getName());
                tagList.add(e);
            }
        });
        return tagCollection;
    }

}
