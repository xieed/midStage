package top.maluo.ms.us.util;

import org.springframework.stereotype.Component;
import top.maluo.ms.us.po.User;

import javax.validation.constraints.NotNull;

/**
 * @className: SessionUtils
 * @Description: session工具类
 * @author: xieed
 * @date: 2019/12/18 12:21
 * @version: v1.0
 */
@Component
public class SessionUtils {

    /**
     * 获取sessionId
     * @param user 用户
     * @return sessionId
     */
    public String getSessionId(@NotNull User user) {
        return user.toString();
    }

}
