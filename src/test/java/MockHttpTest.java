import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:webapp/WEB-INF/config/applicationContext.xml"})
public class MockHttpTest {
    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Test
    public void responseTest() throws Exception {
        CustomDispatcherServlet servlet = new CustomDispatcherServlet();
        try {
            servlet.setRelativeLocations(getClass(), "test-spring-servlet.xml");
            servlet.init(new MockServletConfig("test-spring"));
            
            String reqName = "Guest";

            MockHttpServletRequest req = new MockHttpServletRequest("GET", "/main.do");
            req.addParameter("name", reqName);

            MockHttpServletResponse res = new MockHttpServletResponse();

            servlet.service(req, res);

            ModelAndView mv = servlet.getModelAndView();

            assertThat(mv, is(not(nullValue())));
            assertThat(mv.getViewName(), is("main/index"));
            assertThat(mv.getModel().get("greeting").toString(), is("Hello~" + reqName + "!!!!"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}