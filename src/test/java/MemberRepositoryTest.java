import java.sql.Connection;
import java.util.*;

import javax.sql.DataSource;

import com.mvc.entity.Level;
import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;

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
public class MemberRepositoryTest {
   
    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void setup() {
        memberRepository.deleteAll();
    }

    @Test
    public void deleteAllTest() throws Exception {
        long count = memberRepository.countAll();

        assertThat(count, is(0L));
    }

}