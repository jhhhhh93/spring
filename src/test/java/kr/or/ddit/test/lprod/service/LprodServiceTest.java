package kr.or.ddit.test.lprod.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;

public class LprodServiceTest extends RootTestConfig{
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	/**
	* Method : getAllTest
	* 작성자 : PC-21
	* 변경이력 :
	* Method 설명 : getAll() 메서드 테스트
	*/
	@Test
	public void getAllTest(){
		/***Given***/
		
		/***When***/
		List<LprodVO> lprodList = lprodService.getAll();
		
		/***Then***/
		assertEquals(12, lprodList.size());
	}
	
	@Test
	public void getProdTest() {
		/***Given***/
		String lprod_gu = "P101";
		
		/***When***/
		List<LprodVO> lprodList = lprodService.getProd(lprod_gu);
		
		/***Then***/
		assertEquals(6, lprodList.size());
	}
	
	@Test
	public void getLprodPagingListTest() {
		/*** Given ***/
		Page page = new Page();
		page.setPage(3);
		page.setPageSize(5);

		/*** When ***/
		Map<String, Object> map = lprodService.getLprodPagingList(page);
		List<LprodVO> prodPagingList = (List<LprodVO>) map.get("prodPagingList");
		int paginationSize = (int) map.get("paginationSize");
		
		/*** Then ***/
		assertEquals(2, prodPagingList.size());
		assertEquals(3, paginationSize);
		
	}
	
}
