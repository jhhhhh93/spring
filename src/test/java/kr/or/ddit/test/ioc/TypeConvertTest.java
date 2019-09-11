package kr.or.ddit.test.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/spring/ioc/application-context-type-test.xml")
public class TypeConvertTest {

	private static final Logger logger = LoggerFactory.getLogger(TypeConvertTest.class);
	
	@Resource(name="user")
	private User user;
	
	@Test
	public void dateTypeInjectionTest() {
		/***Given***/
		
		/***When***/
		logger.debug("user : {}", user);

		/***Then***/
		assertNotNull(user);
	}

}
