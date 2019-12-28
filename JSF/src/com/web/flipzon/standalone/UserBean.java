package com.web.flipzon.standalone;


import javax.faces.bean.ManagedBean;

@ManagedBean(name = "userBean")
public class UserBean {
	private static final long serialVersionUID = 1L;
	
	public UserBean(){
		
	}
	public boolean authenticate(String userId, String password){
		return true;
	}
	
}
