package kr.or.ddit.lprod.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVO;

public interface ILprodDao {
	public List<LprodVO> getAll();
	
	public List<LprodVO>getProd(String lprod_gu);
	
	public List<LprodVO> getLprodPagingList(Page page);
	
	public int getProdTotalCnt();
}
