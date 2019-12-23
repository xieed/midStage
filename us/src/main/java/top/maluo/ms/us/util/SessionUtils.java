package top.maluo.ms.us.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ConcurrentReferenceHashMap;
import top.maluo.ms.us.po.User;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/**
 * @className: SessionUtils
 * @Description: session工具类
 * @author: xieed
 * @date: 2019/12/18 12:21
 * @version: v1.0
 */
@Component
@Slf4j
@EnableAsync
public class SessionUtils {

    /**
     * 最大无操作保持在线时长(毫秒)
     */
    @Value("${ms.us.max_online_time_ms}")
    private int MAX_ALIVE;

    /**
     * 在内存不足时 将在线状态清空
     */
    private static Map<String, Integer> online = new ConcurrentReferenceHashMap<>(16,
            ConcurrentReferenceHashMap.ReferenceType.WEAK);

    /**
     * 获取sessionId
     *
     * @param user 用户
     * @return sessionId
     */
    public String getSessionId(@NotNull User user) {
        online.put(user.toString(), MAX_ALIVE);
        return user.toString();
    }

    /**
     * 删除sessionId
     *
     * @param sessionId sessionId
     */
    public void removeSession(String sessionId) {
        online.remove(sessionId);
    }

    /**
     * 获取在线时间
     *
     * @return 返回所有在线用户
     */
    public String getOnline() {
        return online.toString();
    }

    /**
     * 更新用户断线时间
     */
    public void update(String sessionId, Integer time) {
        online.putIfAbsent(sessionId, time);
    }

    public void initOnlineJob() {
        new SessionJob().onlineJob();
    }

    public boolean hasSessionId(String sessionId) {
        return online.containsKey(sessionId);
    }

    @Service
    class SessionJob {
        @Async(value = "onlineJob")
        public void onlineJob() {
            log.info("onlineJob init.. #{}", getOnline());
            while (true) {
                try {
                    Set<String> keys = online.keySet();
                    for (String key : keys) {
                        Integer v = online.get(key);
                        if (v > 0) {
                            online.put(key, v - 1000);
                        } else {
                            online.remove(key);
                        }
                    }
                    log.info("onlineJob online#{}", online);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("onlineJob error", e);
                }
            }
        }
    }

}
