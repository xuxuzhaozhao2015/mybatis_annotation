package top.xuxuzhaozhao.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.xuxuzhaozhao.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 使用了注解，就不能使用xml配置
     * 当数据库字段与实体不相同即可使用Results来解决对应,起id可以让其他方法引用
     *
     * @return
     */
    @Select("select * from user;")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "id",property = "accounts",many = @Many(
                    select = "top.xuxuzhaozhao.dao.IAccountDao.findAccountsByUid",
                    fetchType = FetchType.LAZY
            ))
    })
    List<User> findAll();

    /**
     * 这里ResultMap就是引用了上面那个
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id};")
    @ResultMap(value = {"userMap"})
    User findUserById(Integer id);

    @Insert("insert into user(username,sex,address,birthday)values(#{username},#{sex},#{address},#{birthday})")
    Integer insertUser(User user);

    @Update("update user set username=#{username},address=#{address},birthday=#{birthday},sex=#{sex} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    //    @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    List<User> findUserByUserName(String username);
}
