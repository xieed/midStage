package top.maluo.ms.us;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.maluo.ms.us.service.UserService;

@SpringBootTest
class UsApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    void loginTest() {
        System.out.println(userService.login("",""));
        System.out.println(userService.login(null,null));
        System.out.println(userService.login("admin","admin"));
    }

}
