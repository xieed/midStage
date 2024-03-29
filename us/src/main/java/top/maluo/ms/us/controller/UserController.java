package top.maluo.ms.us.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.maluo.ms.us.api.po.User;
import top.maluo.ms.us.service.UserService;
import top.maluo.ms.util.rest.RestResponse;

/**
 * @className: UserController
 * @Description: 用户
 * @author: xieed
 * @date: 2019/12/16 17:45
 * @version: v1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/login/{userName}/{pwd}")
    public RestResponse login(@PathVariable String userName, @PathVariable String pwd) {
        return service.login(userName, pwd);
    }

    @PostMapping(value = "/login")
    public RestResponse loginPost(@RequestBody User user) {
        return service.login(user.getUserName(), user.getPwd());
    }

    @PostMapping(value = "/register")
    public RestResponse register(@RequestBody User user) {
        return service.register(user.getUserName(), user.getPwd());
    }

    @PostMapping(value = "/logout")
    public RestResponse logout(@RequestBody String sessionId) {
        return service.logout(sessionId);
    }

    @PostMapping(value = "/checkSessionId")
    public RestResponse checkSessionId(@RequestBody String sessionId) {
        return service.checkSessionId(sessionId);
    }

    @GetMapping("/online")
    public RestResponse onLine() {
        return service.online();
    }

    @GetMapping("/runOnlineJob")
    public RestResponse runOnlineJob() {
        return service.runOnlineJob();
    }

}
