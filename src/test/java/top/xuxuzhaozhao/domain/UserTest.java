package top.xuxuzhaozhao.domain;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.xuxuzhaozhao.dao.IUserDao;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {
    private InputStream inputStream;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void Init() throws Exception{
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        session = factory.openSession(true);

        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void Destory() throws Exception{
        session.close();
        inputStream.close();
    }

    @Test
    public void TestFindAll(){
        List<User> users = userDao.findAll();

        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void TestFindUserById(){
        User user = userDao.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void TestInsertUser(){
        User user = new User();
        user.setSex("f");
        user.setUsername("xuxuzhaozhao");
        user.setAddress("四川成都");
        user.setBirthday(new Date());

        userDao.insertUser(user);
    }

    @Test
    public void TestUpdateUser(){
        User user = userDao.findUserById(1);
        user.setUsername("zhaozhaoxuxu");

        userDao.updateUser(user);
    }

    @Test
    public void TestDeleteUser(){
        userDao.deleteUser(13);
    }

    @Test
    public void TestFindUserByUserName(){
//        List<User> users = userDao.findUserByUserName("%xu%");
        List<User> users = userDao.findUserByUserName("xu");
        for(User user:users){
            System.out.println(user);
        }
    }
}
