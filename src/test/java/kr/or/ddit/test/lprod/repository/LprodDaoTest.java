package kr.or.ddit.test.lprod.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.repository.ILprodDao;

public class LprodDaoTest extends RootTestConfig{
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
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
		List<LprodVO> lprodList = lprodDao.getAll();
		
		/***Then***/
		assertEquals(12, lprodList.size());
	}
	
	@Test
	public void getProdTest() {
		/***Given***/
		String lprod_gu = "P101";
		
		/***When***/
		List<LprodVO> lprodList = lprodDao.getProd(lprod_gu);
		
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
		List<LprodVO> prodPagingList = lprodDao.getLprodPagingList(page);
		
		/*** Then ***/
		assertEquals(2, prodPagingList.size());
	}
	
	@Test
	public void getProdTotalCntTest() {
		/***Given***/
		
		/***When***/
		int totalCnt = lprodDao.getProdTotalCnt();

		/***Then***/
		assertEquals(12, totalCnt);
	}
	
}
