package com.cjhrxS.ua.sec.ServiceImpl;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cjhrxS.ua.sec.Entity.LogEntity;
import com.cjhrxS.ua.sec.Repository.LogRepository;

import static java.util.Collections.emptyList;

@Service
public class LogServiceImpl implements UserDetailsService{
	
	private LogRepository logRepository;
	
	public LogServiceImpl(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	LogEntity logEntity = logRepository.findByUserName(username);
		if(logEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(logEntity.getUserName(), logEntity.getPassword(), emptyList());
	}
	
	

}
