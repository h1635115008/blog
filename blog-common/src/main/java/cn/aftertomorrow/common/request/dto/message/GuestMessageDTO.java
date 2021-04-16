package cn.aftertomorrow.common.request.dto.message;

import cn.aftertomorrow.common.request.dto.BaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author huangming
 * @className GuestMessageDTO
 * @description
 * @date 2021/4/15
 */
@Data
public class GuestMessageDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String email;
    private String words;
    private Date time;
}
