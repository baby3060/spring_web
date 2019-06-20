import java.sql.Connection;
import java.util.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:webapp/WEB-INF/config/applicationContext.xml"})
public class DBConnectionTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void connectionHitTest() throws Exception {
        List<String> beanList = new ArrayList<String>(Arrays.asList(applicationContext.getBeanDefinitionNames()));
        
        System.out.println(beanList);

        assertTrue(beanList.contains("targetDataSource"));
        assertTrue(beanList.contains("dataSource"));

        DataSource dataSource = applicationContext.getBean("targetDataSource", DataSource.class);
        
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } finally {
            try { conn.close(); } catch(Exception e){}
        }        
    }
}