package top.maluo.ms.us.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.maluo.ms.us.api.po.UserLoginInfo;
import top.maluo.ms.us.dao.UserRepository;
import top.maluo.ms.us.po.User;
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

    UserRepository repository;

    SessionUtils sessionUtils;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setSessionUtils(SessionUtils sessionUtils) {
        this.sessionUtils = sessionUtils;
    }

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
        User usUser = repository.findByUserNameAndPwd(userName, pwd);
        if (Objects.isNull(usUser)) {
            return new RestResponse(State.BIZ_FAIL, "密码不正确");
        }
        top.maluo.ms.us.api.po.User user = getApiUser(usUser);
        UserLoginInfo info = getUserLoginInfo(usUser, user);
        return new RestResponse(State.RETRIEVE_SUCCESS, JSON.toJSONString(info));
    }

    /**
     * 获取登录信息
     * @param usUser usUser
     * @param user usAPIUser
     * @return usAPIUserLoginInfo
     */
    private UserLoginInfo getUserLoginInfo(User usUser, top.maluo.ms.us.api.po.User user) {
        UserLoginInfo info = new UserLoginInfo();
        info.setUser(user);
        info.setSessionId(sessionUtils.getSessionId(usUser));
        return info;
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
        UserLoginInfo info = getUserLoginInfo(user, getApiUser(user));
        return new RestResponse(State.RETRIEVE_SUCCESS, JSON.toJSONString(info));
    }

    /**
     * 将usUser转换成usAPIUser
     * @param usUser usUser
     * @return usAPIUser
     */
    private top.maluo.ms.us.api.po.User getApiUser(User usUser) {
        top.maluo.ms.us.api.po.User user = new top.maluo.ms.us.api.po.User();
        BeanUtils.copyProperties(usUser, user);
        return user;
    }

    /**
     * 登出
     *
     * @param sessionId 要删除的sessionId
     * @return 返回登录页面
     */
    public RestResponse logout(String sessionId) {
        sessionUtils.removeSession(sessionId);
        JSONObject res = new JSONObject();
        res.put("msg", "ok");
        return new RestResponse(State.RETRIEVE_SUCCESS, res.toJSONString());
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

    /**
     * 获取在线用户
     * @return 当前在线用户
     */
    public RestResponse online() {
        return new RestResponse(State.RETRIEVE_SUCCESS, sessionUtils.getOnline());
    }

    /**
     * 执行清理在线任务
     * @return
     */
    public RestResponse runOnlineJob() {
        sessionUtils.initOnlineJob();
        return null;
    }


    /**
     * 校验sessionId
     * @param sessionId sessionId
     * @return 校验结果
     */
    public RestResponse checkSessionId(String sessionId) {
        boolean hasSessionId = sessionUtils.hasSessionId(sessionId);
        JSONObject res = new JSONObject();
        res.put("checkSessionId", hasSessionId);
        return new RestResponse(hasSessionId ? State.RETRIEVE_SUCCESS : State.CHECK_SESSION_FAIL, res.toJSONString());
    }
}
