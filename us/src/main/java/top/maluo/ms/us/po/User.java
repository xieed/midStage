package top.maluo.ms.us.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

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

    @Id
    private String id;
    @NonNull
    private Long serialNo;
    @NonNull
    private String userName;
    @NonNull
    private String pwd;
    /**
     * 1:启用
     * 0:禁用
     */
    private Integer state;
}
