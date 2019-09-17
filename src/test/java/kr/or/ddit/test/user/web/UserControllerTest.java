package kr.or.ddit.test.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
			.andExpect(view().name("/user/userList"));

		/***Then***/
	}
	
	@Test
	public void userListOnlyHalf() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/userListOnlyHalf"))
			.andExpect(model().attributeExists("userListOnlyHalf"))
			.andExpect(view().name("/user/userListOnlyHalf"));

		/***Then***/
	}
	
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/user/userPagingList"))
			.andExpect(model().attributeExists("userList"))
			.andExpect(model().attributeExists("paginationSize"))
			.andExpect(view().name("/user/userPagingList"));

		/***Then***/
	}

}
