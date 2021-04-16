package cn.aftertomorrow.common.response.vo.file;

import cn.aftertomorrow.common.response.vo.BaseVO;
import lombok.Builder;
import lombok.Data;

/**
 * 图片VO
 *
 * @author huangming
 * @date 2021/04/13
 */
@Data
public class ImageVO extends BaseVO {
    private String file_path;
    private Boolean success;
    private String msg;
}
