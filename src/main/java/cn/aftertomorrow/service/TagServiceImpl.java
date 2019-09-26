package cn.aftertomorrow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aftertomorrow.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.aftertomorrow.dao.TagDao;
import cn.aftertomorrow.po.Tag;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> listAll() {
        // TODO Auto-generated method stub
        return tagDao.listAll();
    }

    @Override
    public Map<String, List<Tag>> getTagOrderByKinds() {
        Map<String, List<Tag>> tagOrderByKinds = new HashMap<>();
        List<Tag> tags = listAll();
        for (Tag tag : tags) {
            String kind = String.valueOf(tag.getKind());
            Util.order(tagOrderByKinds, kind, new ArrayList(), tag);
        }
        return tagOrderByKinds;
    }

}
