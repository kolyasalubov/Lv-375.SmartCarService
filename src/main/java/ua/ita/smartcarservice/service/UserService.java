package ua.ita.smartcarservice.service;

import java.util.List;

import ua.ita.smartcarservice.entity.UserEntity;

public interface UserService {

	 void createUser(UserEntity userEntity);
		
		List<UserEntity> findAll();
		
		UserEntity findById(Long id);
		
		void deleteById(Long id, UserEntity userEntity);
		
		void updateUserById(Long id, UserEntity userEntity);
}
