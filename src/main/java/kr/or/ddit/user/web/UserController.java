package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

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
		
		return "user/userList";
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
		
		return "user/userListOnlyHalf";
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC-21
	* 변경이력 :
	* @param page
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@RequestMapping("userPagingList")
	public String userPagingList(Page page, Model model) {
		
		model.addAttribute("pageVo", page);
		
		List<User> userList = (List<User>) userService.getUserPagingList(page).get("userList");
		int paginationSize = (int) userService.getUserPagingList(page).get("paginationSize");
	
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		
		return "user/userPagingList";
		
	}
	
	/**
	* Method : user
	* 작성자 : PC-21
	* 변경이력 :
	* @param model
	* @param userId
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	@RequestMapping("user")
	public String user(Model model, String userId) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		return "user/user";
	}
	
	@RequestMapping("userPicture")
	public void userPicture(String userId, HttpServletResponse response) throws IOException {
		
		User user = userService.getUser(userId);
		
		ServletOutputStream sos = response.getOutputStream();
		
		File picture = new File(user.getRealfilename());
		FileInputStream fis = new FileInputStream(picture);
		
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff,0,512)) != -1) {
			sos.write(buff, 0, len);
		}
		
		fis.close();
	}
	
	@RequestMapping(path = "userForm", method = RequestMethod.GET)
	public String userFormView() {
		return "user/userForm";
	}
	
	//사용자 등록 요청
	@RequestMapping(path = "userForm", method = RequestMethod.POST)
	public String userForm(User user, BindingResult result, @RequestPart("picture") MultipartFile picture) {
		
		new UserValidator().validate(user, result);
		
		if(result.hasErrors()) {
			return "user/userForm";
		}else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
			
			if(picture.getSize() > 0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOrginalFileName());
					user.setRealfilename(fileInfo.getFile().getPath());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			int insertCnt = userService.insertUser(user);
			if(insertCnt == 1) {
				return "redirect:/user/user?userId=" + user.getUserId();
			}else {
				return "user/userForm";
			}
		}
	}
	
	@RequestMapping(path = "userUpdate", method = RequestMethod.GET)
	public String userUpdateView(String userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "user/userUpdate";
	}
	
	@RequestMapping(path = "userUpdate", method = RequestMethod.POST)
	public String userUpdate(User user, @RequestPart("picture") MultipartFile picture) {
		
		FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
		
		if(picture.getSize() > 0) {
			try {
				picture.transferTo(fileInfo.getFile());
				user.setFilename(fileInfo.getOrginalFileName());
				user.setRealfilename(fileInfo.getFile().getPath());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = userService.updateUser(user);
		if(updateCnt == 1) {
			return "redirect:/user/user?userId=" + user.getUserId();
		}else {
			return "user/userUpdate";
		}
		
	}
	
	@RequestMapping("userPagingListAjax")
	public String userPagingListAjax(Page page, Model model) {
		
		model.addAttribute("pageVo", page);
		
		List<User> userList = (List<User>) userService.getUserPagingList(page).get("userList");
		int paginationSize = (int) userService.getUserPagingList(page).get("paginationSize");
	
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		
		return "jsonView";
	}
	
	@RequestMapping(path = "userPagingListAjaxView")
	public String userPagingListAjaxView() {
		return "user/userPagingListAjaxView";
	}
	
	/**
	* Method : userPagingListHtmlAjax
	* 작성자 : PC-21
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트의 결과를 html로 생성한다.(jsp)
	*/
	@RequestMapping("userPagingListHtmlAjax")
	public String userPagingListHtmlAjax(@RequestParam(defaultValue = "1") int page,
										 @RequestParam(defaultValue = "10") int pagesize,
										 Model model) {
		Page pageVo = new Page(page, pagesize);
		Map<String, Object> resultMap = userService.getUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageVo", pageVo);
		
		return "user/userPagingListHtmlAjax";
	}
	
	@RequestMapping(path = "userPagingListAjaxRequestBody", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userPagingListAjaxRequestBody(@RequestBody Page page, Model model) {
		Map<String, Object> resultMap = userService.getUserPagingList(page);
		resultMap.put("pageVo", page);
		
		return resultMap;
		
	}
}
