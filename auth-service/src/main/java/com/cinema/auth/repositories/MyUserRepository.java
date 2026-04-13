package com.cinema.auth.repositories;


import com.cinema.auth.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long>{
	
		public Optional<MyUser> findByUsername(String username);
}
