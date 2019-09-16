package kr.or.ddit.hello.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/config/spring/servlet-context.xml")	// controller scan : servlet-context.xml
@WebAppConfiguration	//스프링 컨테이너를 구성할 web기반 application context로 구성
public class HelloControllerTest {

	//controller를 테스트 하기 위해 필요한 것 2가지
	//applicationContext : 스프링 컨테이너
	//MockMvc : dispatcherServlet (applicationContext객체를 통해 생성)
	
	//주입하려고하는 필드의 타입과 일치할 경우 이름과 관계없이 주입
	//만약에 주입하려고 하는 필드의 타입과 스프링 빈 중에 
	@Autowired	
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void helloTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hello/hello.do").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String userId = (String) mav.getModelMap().get("userId");
		
		//controller viewName(String)을 리턴 하지만,
		//스프링 프레임워크 내부에서는 viewName을 ModelAndView객체로 변환해서 사용
		//또한 컨트롤러 메서드에서는 viewName뿐만 아니라 ModelAndView 객체, View객체 리턴하는게 가능
		//리턴타입이 void인 경우도 존재(response객체를 통해 개발자가 직접 응답을 생성하는 것이 가능)
		
		/***Then***/
		assertEquals("hello/hello", mav.getViewName());
		assertEquals("hello, world", mav.getModel().get("msg"));
		assertEquals("brown_helloControll", userId);
		
	}

}