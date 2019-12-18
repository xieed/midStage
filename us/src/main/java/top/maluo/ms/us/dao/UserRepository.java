package top.maluo.ms.us.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.maluo.ms.us.po.User;

/**
 * @className: UserRepository
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/16 17:33
 * @version: v1.0
 */
public interface UserRepository extends MongoRepository<User, Long>, UserCustomRepository {
    /**
     * 通过用户名和密码找到用户
     * @param userName 用户名
     * @param pwd 密码
     * @return 用户实例
     */
    User findByUserNameAndPwd(String userName, String pwd);

    /**
     * 通过用户名找到用户
     * @param userName 用户名
     * @return 用户实例
     */
    User findByUserName(String userName);
}
