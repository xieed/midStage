package top.maluo.util.rest;

/**
 * @className: State
 * @Description: 接口响应状态
 * @author: xieed
 * @date: 2019/12/18 12:09
 * @version: v1.0
 */
public class State {

    /**
     * 更新成功
     */
    public static Integer UPDATE_SUCCESS = 5;

    /**
     * 读取成功
     */
    public static Integer RETRIEVE_SUCCESS = 4;

    /**
     * 删除成功
     */
    public static Integer DELETE_SUCCESS = 3;

    /**
     * 新增成功
     */
    public static Integer CREATE_SUCCESS = 2;

    /**
     * 推送
     */
    public static Integer PUSH = 1;

    /**
     * 成功
     */
    public static Integer SUCCESS = 0;

    /**
     * 系统级异常
     */
    public static Integer FAIL = -1;

    /**
     * 业务异常
     */
    public static Integer BIZ_FAIL = -2;

}
