package top.maluo.ms.us.util.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: RestResponse
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/17 14:00
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {

    public RestResponse(Integer state) {
        this.state = state;
    }

    /**
     * 操作是否成功
     */
    private Integer state;

    /**
     * 返回的数据
     */
    private String data;
}
