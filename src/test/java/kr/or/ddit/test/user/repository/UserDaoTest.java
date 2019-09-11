package kr.or.ddit.test.user.repository;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:kr/or/ddit/config/spring/context-root.xml", 
		"classpath:kr/or/ddit/config/spring/context-datasource.xml",
		"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class UserDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	// userDao를 테스트하기 위해 필요한 것
	// db 연결, 트랜잭션, dao
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Test
	public void getUserListTest() {
		/***Given***/
		
		/***When***/
		List<User> userList = userDao.getUserList();
		logger.debug("userList : {} ", userList);
		
		/***Then***/
		assertTrue(userList.size() > 105);
	}
	
	/**
	    * 
	   * Method : getUserTest
	   * 작성자 : PC-08
	   * 변경이력 :
	   * Method 설명 : 사용자 정보 조회 테스트
	    */
	   @Test
	   public void getUserTest() {
	      /***Given***/
	      String userId = "brown";

	      /***When***/
	      User userVo = userDao.getUser(userId);
	      String pass = KISA_SHA256.encrypt("brown1234");
	      
	      /***Then***/
	      assertEquals("정보수정", userVo.getUserNm());
	      assertEquals(pass, userVo.getPass());
	   }
	   
	   /**
	    * 
	   * Method : getUserListHalf
	   * 작성자 : PC-08
	   * 변경이력 :
	   * Method 설명 : getUserListHalf 테스트
	    */
	   @Test
	   public void getUserListOnlyHalfTest() {
	      /***Given***/
	      
	      /***When***/
	      List<User> userListHalf = userDao.getUserListOnlyHalf();
	      
	      /***Then***/
	      assertEquals(50, userListHalf.size());
	   }
	   
	   /**
		* Method : getUserPagingListTest
		* 작성자 : PC-21
		* 변경이력 :
		* Method 설명 : 사용자 페이징 리스트 조회 테스트
		*/
		@Test
		public void getUserPagingListTest() {
			/*** Given ***/
			Page page = new Page();
			page.setPage(3);
			page.setPageSize(10);

			/*** When ***/
			List<User> userList = userDao.getUserPagingList(page);
			
			/*** Then ***/
			assertEquals(10, userList.size());
			logger.debug(userList.get(0).getUserId());
			assertEquals("xuserid18", userList.get(0).getUserId());
			
		}
		
		@Test
		public void getUserTotalCntTest() {
			/***Given***/
			
			/***When***/
			int totalCnt = userDao.getUserTotalCnt();
			
			/***Then***/
			assertTrue(totalCnt > 105);
		}
		
		@Test
		public void insertUserTest() throws ParseException {
			/***Given***/
			User user = new User();
			String userId = "browntest";
			
			user.setUserId(userId);
			user.setUserNm("브라운테스트");
			user.setPass("brownTest1234");
			user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
			user.setAlias("곰테스트");
			user.setAddr1("대전광역시 중구 중앙로 76");
			user.setAddr2("영민빌딩 2층 DDIT");
			user.setZipcode("34940");
			user.setFilename("sally");
			user.setRealfilename("testfilename");
			
			/***When***/
			int deleteCnt = userDao.deleteUser(userId);
			int insertCnt = userDao.insertUser(user);
			
			/***Then***/
			assertEquals(1, deleteCnt);
			assertEquals(1, insertCnt);
		}
		
		@Test
		public void updateUser() throws ParseException {
			/***Given***/
			User user = new User();
			
			user.setUserId("brown");
			user.setUserNm("정보수정");
			user.setPass("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44");
			user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
			user.setAlias("곰테스트");
			user.setAddr1("대전광역시 중구 중앙로 76");
			user.setAddr2("영민빌딩 2층 DDIT");
			user.setZipcode("34940");
			user.setFilename("sally");
			user.setRealfilename("testfilename");
			
			/***When***/
			user.setRealfilename(null);
			int updateCnt = userDao.updateUser(user);

			/***Then***/
			assertEquals(1, updateCnt);
		}

}
