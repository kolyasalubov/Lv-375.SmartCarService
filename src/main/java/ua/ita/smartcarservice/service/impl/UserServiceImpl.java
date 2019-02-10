package ua.ita.smartcarservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createUser(UserEntity userEntity) {
	
		userRepository.save(userEntity);
		
	}

	@Override
	public List<UserEntity> findAll() {
		
		List<UserEntity> users = userRepository.findAll();
		
		return users;
	}

	@Override
	public UserEntity findById(Long id) {
		UserEntity user = userRepository.findById(id).get();
		return user;
	}

	@Override
	public void deleteById(Long id, UserEntity userEntity) {
		
	     userEntity.setId(id);
	     userRepository.delete(userEntity);
		
	}

	@Override
	public void deleteById(Long id) {

		userRepository.deleteById(id);

	}

	@Override
	public void updateUserById(Long id, UserEntity userEntity) {
		
		userEntity.setId(id);
		userRepository.save(userEntity);
		
	}


}
