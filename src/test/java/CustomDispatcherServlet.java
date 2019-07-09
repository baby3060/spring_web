import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDispatcherServlet extends DispatcherServlet {
    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Class<?>[] classes;
    private String[] locations;

    private ModelAndView modelAndView;

    public CustomDispatcherServlet() {}

    public CustomDispatcherServlet(String[] locations) {
        this.locations = locations;
    }

    public CustomDispatcherServlet(Class<?> ...classes) {
        this.classes = classes;
    }

    public CustomDispatcherServlet(String[] locations, Class<?> ...classes) {
        this.locations = locations;
        this.classes = classes;
    }

    public void setLocations(String ...locations) {
        this.locations = locations;
    }
    
    public void setClasses(Class<?> ...classes) {
        this.classes = classes;
    }

    // 입력받은 클래스의 상대 위치에 있는 설정 파일을 해당 Dispatcher의 설정 파일로 설정하기 위함
    public void setRelativeLocations(Class<?> clazz, final String ...relativeLocations) {
        String[] locations = new String[relativeLocations.length];
        String currentPath = ClassUtils.classPackageAsResourcePath(clazz) + "/";

        for(int i = 0; i < relativeLocations.length; i++) {
            locations[i] = currentPath + relativeLocations[i];
        }
        this.setLocations(locations);
    }

    // DispatcherServlet의 Service 호출(FrameworkServlet의 service 호출 -> HttpServlet의 service 호출)
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        this.modelAndView = null;
        super.service(req, res);
    }

    // FrameworkServlet의 createWebApplicationContext(ApplicationContext parent) 오버라이딩
    protected WebApplicationContext createWebApplicationContext(ApplicationContext parent) {
        // 웹 앱 시작 시 ConfigurableWebApplicationContext 인터페이스를 통해 제공되는 configLocations 속성 제공
        // 다른 Bean 정의 형식을 사용하기 위해 상속받을 수 있는 WebContext
        AbstractRefreshableWebApplicationContext wac = new AbstractRefreshableWebApplicationContext() {
            @Override
            protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
                if( locations != null ) {
                    XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(beanFactory);
                    xmlReader.loadBeanDefinitions(locations);
                }
                
                if( classes != null ) {
                    AnnotatedBeanDefinitionReader annoReader = new AnnotatedBeanDefinitionReader(beanFactory);
                    annoReader.register(classes);
                }
            }
        };
        
        wac.setServletContext(getServletContext());
        wac.setServletConfig(getServletConfig());
        wac.refresh();

        return wac;
    }

    protected void render(ModelAndView mv, HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 테스트에서 반환받기 위해 속성값을 옮겨옴
        this.modelAndView = mv;
        // Dispatcher 서블릿에서는 주어진 ModelAndView를 렌더링함. 요청 마지막 단계에서 수행되는 메소드
        super.render(mv, req, res);
    }

    public ModelAndView getModelAndView() {
        return this.modelAndView;
    }
}