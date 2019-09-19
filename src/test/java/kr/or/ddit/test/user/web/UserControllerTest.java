package kr.or.ddit.test.user.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.test.WebTestConfig;

public class UserControllerTest extends WebTestConfig{

	/**
	* Method : userListTest
	* 작성자 : PC-21
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/userList"))
			.andExpect(model().attributeExists("userList"))
			.andExpect(view().name("user/userList"));

		/***Then***/
	}
	
	@Test
	public void userListOnlyHalf() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/userListOnlyHalf"))
			.andExpect(model().attributeExists("userListOnlyHalf"))
			.andExpect(view().name("user/userListOnlyHalf"));

		/***Then***/
	}
	
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/userPagingList"))
			.andExpect(model().attributeExists("userList"))
			.andExpect(model().attributeExists("paginationSize"))
			.andExpect(view().name("user/userPagingList"));

		/***Then***/
	}
	
	@Test
	public void userTest() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/user").param("userId", "brown"))
			.andExpect(model().attributeExists("user"))
			.andExpect(view().name("user/user"));

		/***Then***/
	}
	
	@Test
	public void userFormViewTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("user/userForm", mav.getViewName());
	}
	
	@Test
	public void userForm() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("user/userForm", mav.getViewName());
	}
	
	//사용자 등록 요청 테스트
	@Test
	public void userFormTest() throws Exception {
		
		File f = new File("src/test/resources/kr/or/ddit/test/brown.png");
		FileInputStream fis = new FileInputStream(f);
		
		MockMultipartFile file = new MockMultipartFile("picture", "brown.png", "", fis); 
		
		mockMvc.perform(fileUpload("/user/userForm")
				.file(file)
				.param("userId", "brownTest4")
				.param("userNm", "브라운테스트")
				.param("alias", "brownTest")
				.param("reg_dt", "2019-09-17")
				.param("addr1", "대전광역시 중구 선화로 76")
				.param("addr2", "영민빌딩 2층")
				.param("zipcode", "34940")
				.param("pass", "brownTest1234"))
		.andExpect(status().is(302));
		}
	
	@Test
	public void userUpdateViewTest() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/userUpdate").param("userId", "brown"))
			.andExpect(model().attributeExists("user"))
			.andExpect(view().name("user/userUpdate"));

		/***Then***/
	}
	
}