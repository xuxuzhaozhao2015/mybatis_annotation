package top.xuxuzhaozhao.domain;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.xuxuzhaozhao.dao.IAccountDao;
import top.xuxuzhaozhao.dao.IUserDao;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream inputStream;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void Init() throws Exception{
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        session = factory.openSession(true);

        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void Destory() throws Exception{
        session.close();
        inputStream.close();
    }

    @Test
    public void TestFindAll(){
        List<Account> accounts = accountDao.findAll();

        for(Account account:accounts){
            System.out.println(account);
        }
    }
}
