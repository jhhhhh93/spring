package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.repository.ILprodDao;

@Service
public class LprodService implements ILprodService {
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	@Override
	public List<LprodVO> getAll() {
		return lprodDao.getAll();
	}
	@Override
	public List<LprodVO> getProd(String lprod_gu) {
		return lprodDao.getProd(lprod_gu);
 	}
	@Override
	public Map<String, Object> getLprodPagingList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<LprodVO> lprodPagingList = lprodDao.getLprodPagingList(page);
		int totalCnt = lprodDao.getProdTotalCnt();
		
		map.put("lprodPagingList", lprodPagingList);
		map.put("paginationSize", (int) Math.ceil((double)totalCnt/page.getPageSize()));
		return map;
	}
	


}
