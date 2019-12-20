package top.maluo.ms.us.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.maluo.ms.us.dao.UserRepository;
import top.maluo.ms.us.po.User;
import top.maluo.ms.us.po.UserLoginInfo;
import top.maluo.ms.us.util.SessionUtils;
import top.maluo.ms.util.rest.RestResponse;
import top.maluo.ms.util.rest.State;

import java.util.Objects;

/**
 * @className: UserService
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/16 17:34
 * @version: v1.0
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    SessionUtils sessionUtils;

    /**
     * 登录
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return 返回登录信息
     */
    public RestResponse login(String userName, String pwd) {
        // 用户名找不到则返回用户名不存在
        if (userNameNotExist(userName)) {
            return new RestResponse(State.BIZ_FAIL, "用户名不存在");
        }

        // 密码不对则返回密码不正确
        User user = repository.findByUserNameAndPwd(userName, pwd);
        if (Objects.isNull(user)) {
            return new RestResponse(State.BIZ_FAIL, "密码不正确");
        }

        UserLoginInfo info = new UserLoginInfo();
        info.setUser(user);
        info.setSessionId(sessionUtils.getSessionId(user));
        return new RestResponse(State.RETRIEVE_SUCCESS, JSON.toJSONString(info));
    }

    /**
     * 注册
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return 返回注册信息
     */
    public RestResponse register(String userName, String pwd) {
        // 用户名找不到则返回用户名不存在
        if (!userNameNotExist(userName)) {
            return new RestResponse(State.BIZ_FAIL, "用户名已存在");
        }

        // 保存并登陆
        User user = new User();
        user.setUserName(userName);
        user.setPwd(pwd);
        repository.insert(user);
        UserLoginInfo info = new UserLoginInfo();
        info.setUser(user);
        info.setSessionId(sessionUtils.getSessionId(user));
        return new RestResponse(State.RETRIEVE_SUCCESS, JSON.toJSONString(info));
    }

    /**
     * 用户名是否存在
     * @param userName 用户名
     * @return 是否存在
     */
    private boolean userNameNotExist(String userName) {
        User userNameCheck = repository.findByUserName(userName);
        return Objects.isNull(userNameCheck);
    }

}
