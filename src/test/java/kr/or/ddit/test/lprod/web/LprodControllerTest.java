package kr.or.ddit.test.lprod.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;

public class LprodControllerTest extends WebTestConfig{
	
	@Test
	public void lprodListTest() throws Exception {
		mockMvc.perform(get("/lprodList"))
		.andExpect(model().attributeExists("lprodList"))
		.andExpect(view().name("/lprod/lprodList"));
	}

	@Test
	public void lprodPagingListTest() throws Exception {
		mockMvc.perform(get("/lprodPagingList"))
		.andExpect(model().attributeExists("lprodPagingList"))
		.andExpect(model().attributeExists("paginationSize"))
		.andExpect(view().name("/lprod/lprodPagingList"));
	}

}
