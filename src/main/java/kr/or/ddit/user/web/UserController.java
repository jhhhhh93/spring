package kr.or.ddit.user.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("user/")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private IUserService userService;

	/**
	* Method : userView
	* 작성자 : PC-21
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 상세화면 요청
	*/
	//사용자가 localhost:8081/user/view.do url을 요청할 경우 메서드 실행
	@RequestMapping("view.do")
	public String userView() {
		logger.debug("userController.userView()");
		return "user/view";
		
		// prefix + viewName + suffix
		// WEB-INF/views/user/view.jsp
	}
	
	/**
	* Method : userList
	* 작성자 : PC-21
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@RequestMapping("userList")
	public String userList(Model model) {
		List<User> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		
		return "/user/userList";
	}
	
	/**
	* Method : userListOnlyHalf
	* 작성자 : PC-21
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 리스트(50) 조회
	*/
	@RequestMapping("userListOnlyHalf")
	public String userListOnlyHalf(Model model) {
		List<User> userListOnlyHalf = userService.getUserListOnlyHalf();
		model.addAttribute("userListOnlyHalf", userListOnlyHalf);
		
		return "/user/userListOnlyHalf";
	}
	
	@RequestMapping("userPagingList")
	public String userPagingList(Page page, Model model) {
		
		model.addAttribute("pageVo", page);
		
		List<User> userList = (List<User>) userService.getUserPagingList(page).get("userList");
		int paginationSize = (int) userService.getUserPagingList(page).get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		
		return "/user/userPagingList";
		
	}
	
	
}
