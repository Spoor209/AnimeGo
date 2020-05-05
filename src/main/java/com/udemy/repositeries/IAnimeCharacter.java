package com.udemy.repositeries;
/**
 * 
 * @author adlenkhelladi
 *
 */

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.udemy.entities.AnimeCharacter;
import com.udemy.entities.User;


// Pour avoir accès aux method de JPA
public interface IAnimeCharacter extends JpaRepository<AnimeCharacter, Long> {
	
	List<AnimeCharacter> findByUserOrShared(User user, boolean shared);
	
	List<AnimeCharacter> findByUser(User user);
	
	List<AnimeCharacter> findBySharedAndUserNotLike(boolean shared, User user);

}
