package cn.aftertomorrow.service;

import java.util.List;
import java.util.Map;

import cn.aftertomorrow.po.Tag;

public interface TagService {
    public List<Tag> listAll();

    public Map<String, List<Tag>> getTagOrderByKinds();
}
