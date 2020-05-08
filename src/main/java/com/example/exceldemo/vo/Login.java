package com.example.exceldemo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;
/**
 * table name:  login
 * author name: baishuai
 * create time: 2020-03-06 17:28:40
 */ 
public class Login extends BaseRowModel {

	@ExcelProperty(value="用户编号",index = 0)
	private Long id;

	@ExcelProperty(value="用户昵称",index = 1)
	private String nickname;

	@ExcelProperty(value="用户密码",index = 2)
	private String password;

	@ExcelProperty(value="盐值",index = 3)
	private String salt;

	@ExcelProperty(value="用户头像",index = 4)
	private String head;

	@ExcelProperty(value="注册时间",index = 5)
	private Date registerDate;

	@ExcelProperty(value="最后登录时间",index = 6)
	private Date lastLoginDate;

	@ExcelProperty(value="登录次数",index = 7)
	private Integer loginCount;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	@Override
	public String toString() {
		return "Login{" +
				"id=" + id +
				", nickname='" + nickname + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", head='" + head + '\'' +
				", registerDate=" + registerDate +
				", lastLoginDate=" + lastLoginDate +
				", loginCount=" + loginCount +
				'}';
	}
}

