package top.maluo.ms.us.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.maluo.ms.us.util.IdWorker;

/**
 * @className: IdWorkerConf
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/23 16:46
 * @version: v1.0
 */
@Configuration
public class IdWorkerConf {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
