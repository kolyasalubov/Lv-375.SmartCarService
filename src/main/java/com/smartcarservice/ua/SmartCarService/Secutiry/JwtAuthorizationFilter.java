package com.smartcarservice.ua.SmartCarService.Secutiry;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import static com.smartcarservice.ua.SmartCarService.Secutiry.SecurityConstant.HEADER_STRING;
import static com.smartcarservice.ua.SmartCarService.Secutiry.SecurityConstant.TOKEN_PREFIX;
import static com.smartcarservice.ua.SmartCarService.Secutiry.SecurityConstant.SECRET;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		
		super(authenticationManager);
	
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(HEADER_STRING);
		
		if(header == null) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
	}
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		if(token != null) {
			
			String logEntity = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
					.build()
					.verify(token.replace(TOKEN_PREFIX, ""))
					.getSubject();
			
			if(logEntity != null) {
				return new UsernamePasswordAuthenticationToken(logEntity, null, new ArrayList<>());
			}
			
			return null;
			
		}
		return null;
		
		
	}
	
	
	
	

}
