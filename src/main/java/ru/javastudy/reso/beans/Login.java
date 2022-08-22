package ru.javastudy.reso.beans;

import ru.javastudy.reso.dao.LoginDAO;
import ru.javastudy.reso.util.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Login
 *
 * @author Psigfry
 * psigfry.corp@yandex.ru
 * created 07/08/2022 - 18:23
 */

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1l;

	private String user;
	private String pwd;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String validateUsernamePassword(){
		boolean valid = LoginDAO.validate(user, pwd, role);
		String isAdmin = LoginDAO.isAdmin(user, role);
		setRole(isAdmin);

		if(valid){
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			session.setAttribute("role", role);
			return "admin.xhtml";
		}else{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}


	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
