package com.udemy.repositeries;
/**
 * 
 * @author adlenkhelladi
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.entities.User;

public interface IUser extends JpaRepository<User, Long>{
	
	User findByMailAndPassword (String mail, String password);

}
