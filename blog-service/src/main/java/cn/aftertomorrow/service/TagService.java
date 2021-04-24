package cn.aftertomorrow.service;

import java.util.List;
import java.util.Map;

import cn.aftertomorrow.common.request.dto.tag.TagDTO;

/**
 * 标签服务类接口
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface TagService {
    /**
     * 获取所有标签
     *
     * @return
     */
    List<TagDTO> listAll();

    /**
     * 通过种类获取标签
     *
     * @return
     */
    Map<String, List<TagDTO>> getTagCollectionByKind();
}
