package kr.co.wishDream.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "prototype")
public class HandleLifeCycleoOfBeans implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(HandleLifeCycleoOfBeans.class);
	
	public HandleLifeCycleoOfBeans() {
		LOG.info("Create HandleLifeCycleoOfBeans Constructor");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("");
	}

	@PostConstruct
    public void postConstruct() {
        LOG.info("PostConstruct");
    }
 
    public void init() {
        LOG.info("init-method");
    }
	
}
