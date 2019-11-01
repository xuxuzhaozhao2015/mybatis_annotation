package top.xuxuzhaozhao.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.xuxuzhaozhao.domain.Account;

import java.util.List;

@CacheNamespace(blocking = true)// 开启二级缓存
public interface IAccountDao {

    /**
     * 一对一，立即加载
     *
     * @return
     */
    @Select("select * from account;")
    @Results(id = "accountUserMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(column = "uid", property = "user", one = @One(
                    select = "top.xuxuzhaozhao.dao.IUserDao.findUserById",
                    fetchType = FetchType.EAGER
            ))
    })
    List<Account> findAll();

    @Select("select * from account where uid = #{uid};")
    List<Account> findAccountsByUid(Integer uid);
}
