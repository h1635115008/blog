package cn.aftertomorrow.dao.mapper;

import java.util.List;

import cn.aftertomorrow.dao.domain.TagDO;

/**
 * 标签mapper
 *
 * @author huangming
 * @date 2019/09/26
 */
public interface TagMapper {
    /**
     * 获取所有文章标签
     *
     * @return
     */
    List<TagDO> listAll();
}
