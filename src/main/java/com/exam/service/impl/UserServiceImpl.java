package com.exam.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	//creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		 User local = this.userRepository.findByUsername(user.getUsername());
		 
		 if(local!=null)
		 {
			 System.out.println("User is Already There");
			 throw new Exception("User is present !!");
		 }
		 else
		 {
			 for(UserRole ur:userRoles) {
				 this.roleRepository.save(ur.getRole());
			 }
			 
			 user.getUserRoles().addAll(userRoles);
			 local = this.userRepository.save(user);
		 }
		 
		return local;
	}
	
	@Override
	//Getting User by username 
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}
	
}
