package ru.javastudy.reso.filter;

import ru.javastudy.reso.util.SessionUtils;

import javax.annotation.security.RolesAllowed;
import javax.servlet.*;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthorizationFilter
 *
 * @author Psigfry
 * psigfry.corp@yandex.ru
 * created 07/08/2022 - 21:32
 */


@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();

			if (reqURI.indexOf("/index.xhtml") >= 0
					|| (reqURI.indexOf("/register.xhtml") >= 0)
					|| (reqURI.indexOf("/login.xhtml") >= 0)
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			}else{
				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
			}




		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void destroy() {

	}
}