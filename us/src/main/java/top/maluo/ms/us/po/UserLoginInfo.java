package top.maluo.ms.us.po;

import lombok.Data;

/**
 * @className: UserLoginfo
 * @Description: 用户登录信息
 * @author: xieed
 * @date: 2019/12/18 12:18
 * @version: v1.0
 */
@Data
public class UserLoginInfo {
    private User user;
    private String sessionId;
}
