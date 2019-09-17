package kr.or.ddit.lprod.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;

@Controller
public class LprodController {
	
	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	@RequestMapping("lprodList")
	public String lprodList(Model model) {
		List<LprodVO> lprodList = lprodService.getAll();
		
		model.addAttribute("lprodList", lprodList);
		return "/lprod/lprodList";
	}
	
	@RequestMapping("lprodPagingList")
	public String lprodPagingList(Page page, Model model) {
		page.setPageSize(5);
		
		model.addAttribute("pageVo", page);
		
		Map<String, Object> map = lprodService.getLprodPagingList(page);
		List<LprodVO> lprodList = (List<LprodVO>) map.get("lprodPagingList");
		int paginationSize = (int) map.get("paginationSize");
		
		model.addAllAttributes(map);
		
		return "/lprod/lprodPagingList";
	}
}
