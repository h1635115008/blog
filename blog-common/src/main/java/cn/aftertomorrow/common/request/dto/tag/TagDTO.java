package cn.aftertomorrow.common.request.dto.tag;

import cn.aftertomorrow.common.request.dto.BaseDTO;
import lombok.Data;

/**
 * @author huangming
 * @className TagDTO
 * @description
 * @date 2021/4/15
 */
@Data
public class TagDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String kind;
    private String unicode;
}
