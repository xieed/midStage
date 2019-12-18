package top.maluo.midstage;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: NacosConfigAction
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/12 15:28
 * @version: v1.0
 */
//打开动态刷新功能
@RefreshScope
@RestController
public class NacosConfigAction {

    @Value("${user.name}")
    String userName;


    @RequestMapping("/getConf")
    public JSONObject getConf() {
        JSONObject res = new JSONObject();
        res.put("userName", userName);
        return res;
    }
}
