package cn.aftertomorrow.service;

import java.util.List;
import java.util.Map;

import cn.aftertomorrow.dao.domain.Tag;

public interface TagService {
     List<Tag> listAll();

     Map<String, List<Tag>> getTagCollectionByKind();
}
