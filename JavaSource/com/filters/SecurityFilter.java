package com.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.JwtUtil;

import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	
	  @Override
	   public void init(FilterConfig filterConfig) throws ServletException {
	        
	    }
	  
	  @Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;

	        String requestURI = httpRequest.getRequestURI();
	        HttpSession session = httpRequest.getSession(false);

	    
	        if (requiresAuthentication(requestURI)) {
	         
	            if (session != null && session.getAttribute("token") != null) {
	               
	                String token = (String) session.getAttribute("token");
	                if (JwtUtil.validateToken(token)) {
	                   
	                    chain.doFilter(request, response);
	                    return;
	                }
	            }
	            
	        	   httpResponse.sendRedirect(httpRequest.getContextPath() + "/accesoNoAutorizado.jsf");
		            return;
	           
	           
	        }

	        chain.doFilter(request, response);
	    }
	  	private boolean requiresAuthentication(String requestURI) {
		
		    return 	!requestURI.equals("/PINFRA/")
		    		&&	!requestURI.contains("/login.jsf")
		            && !requestURI.contains("/altaPersona.jsf")
		            && !requestURI.contains("/altaEstudiante.jsf")
		            && !requestURI.contains("/assets/menu2.jpg")
		            && !requestURI.contains("/assets/img_modeloUTEC.jpg")
		            && !requestURI.contains("/assets/Signin.jpg")
		            && !requestURI.contains("/accesoNoAutorizado.jsf")
		            && !requestURI.contains("/accesoNoAutorizado.xhtml")
		            && !requestURI.contains("/assets/cetu1.png")
		            && !requestURI.contains("/login.xhtml");
		           
		    	
		   
		}


} 
