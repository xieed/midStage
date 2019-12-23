package top.maluo.ms.us.api.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @className: User
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/16 16:51
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String id;
    private Long serialNo;
    private String userName;
    private String pwd;
    /**
     * 1:启用
     * 0:禁用
     */
    private Integer state;
}
