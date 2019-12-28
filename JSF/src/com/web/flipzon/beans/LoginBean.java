package com.web.flipzon.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import com.web.flipzon.jsf.ContextHelper;
import com.web.flipzon.standalone.UserBean;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String password;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	
	public LoginBean() {
		// TODO Auto-generated constructor stub
		System.out.println("In Constructor");
	}
	
	public String doLogin() {
		System.out.println("In do Login");
		boolean authenticate = userBean.authenticate(userId, password);
		if(authenticate){
			ContextHelper.getSession().setAttribute("userId", userId);
			return "goHome";
		}else{
			ContextHelper.getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Incorrect username or password.",
							"Incorrect username or password."));
			return "";
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
