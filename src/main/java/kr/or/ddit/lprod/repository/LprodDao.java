package kr.or.ddit.lprod.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVO;

@Repository
public class LprodDao implements ILprodDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<LprodVO> getAll() {
		return sqlSession.selectList("lprod.getAll");
	}

	@Override
	public List<LprodVO> getProd(String lprod_gu) {
		return sqlSession.selectList("lprod.getProd", lprod_gu);
	}

	@Override
	public List<LprodVO> getLprodPagingList(Page page) {
		return sqlSession.selectList("lprod.getLprodPagingList", page);
	}

	@Override
	public int getProdTotalCnt() {
		return sqlSession.selectOne("lprod.getProdTotalCnt");
	}
}
