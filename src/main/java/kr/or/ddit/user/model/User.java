package kr.or.ddit.user.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;

public class User {
	private String userNm;		// 사용자이름
	private String userId;			// 사용자 아이디
	
	@NotNull
	private String pass;			// 사용자 비밀번호
	private String alias       ;
	private String addr1       ;
	private String addr2       ;
	private String zipcode     ;
	private String filename    ;
	private String realfilename;
	private String realfilename2;
	private int age;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reg_dt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userNM) {
		this.userNm = userNM;
	}
	
	public User(String userNM, String userId, String pass, Date reg_dt, String alias, String addr1, String addr2,
			String zipcode) {
		this.userNm = userNM;
		this.userId = userId;
		this.pass = pass;
		this.reg_dt = reg_dt;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
	}

	public User(String userNM, String userId, String pass, Date reg_dt, String alias, String addr1, String addr2,
			String zipcode, String filename, String realfilename) {
		super();
		this.userNm = userNM;
		this.userId = userId;
		this.pass = pass;
		this.reg_dt = reg_dt;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.filename = filename;
		this.realfilename = realfilename;
	}

	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRealfilename() {
		return realfilename;
	}

	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}

	public String getRealfilename2() {
		return realfilename2;
	}

	public void setRealfilename2(String realfilename2) {
		this.realfilename2 = realfilename2;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	public boolean checkLoginValidate(String userId, String pass) {
		// 암호화 문장끼리 비교
		if(userId.equals(this.userId) && KISA_SHA256.encrypt(pass).equals(this.pass)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [userNM=" + userNm + ", userId=" + userId + ", pass=" + pass + ", alias=" + alias + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", filename=" + filename + ", realfilename="
				+ realfilename + ", realfilename2=" + realfilename2 + ", reg_dt=" + reg_dt + "]";
	}
	
	
}
