package com.cinema.auth.services;


import com.cinema.auth.entities.MyUser;
import com.cinema.auth.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService {
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	public MyUser findById (Long id) {
		return this.myUserRepository.findById(id).orElse(null);
	}
	
	public MyUser save(MyUser user) {
		this.myUserRepository.save(user);
		return user;
	}

	public Optional<MyUser> findByUser(String user) {
		Optional<MyUser> userAux = this.myUserRepository.findByUsername(user);
		if(userAux.isEmpty()) {
			return Optional.empty();
		}
		System.out.println("UserAux: " + userAux.get().getUsername());
		return userAux;
	}

	public void deleteById(Long id) {
		this.myUserRepository.deleteById(id);
	}
}
