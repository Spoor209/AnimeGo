package com.udemy.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.entities.AnimeCharacter;
import com.udemy.entities.User;
import com.udemy.repositeries.IAnimeCharacter;
import com.udemy.repositeries.IUser;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/v1/characters")
/**
 * 
 * @author adlenkhelladi
 *
 */
public class CharacterController {
	
	@Autowired
	private IAnimeCharacter characterRepositery;
	
	@Autowired
	private IUser userRepositery;
	
	
	
	 // Show all the characters:
	 @GetMapping("/")
	    public ResponseEntity<?> findAll() {
		 
	        return ResponseEntity.ok(characterRepositery.findAll());
	    }
	 
	 @GetMapping("/all/{idUser}")
	   public ResponseEntity<?> findAllUserCharacters(@PathVariable Long idUser){
		 if(idUser==null) {
			 return ResponseEntity.badRequest().body("Cannot find the anime with no User");
		 }
		 User user = userRepositery.getOne(idUser);
		 if(user==null) {
			 return ResponseEntity.notFound().build();
		 }
		 List<AnimeCharacter> userChars = characterRepositery.findByUser(user);
		 List<AnimeCharacter> sharedChars = characterRepositery.findBySharedAndUserNotLike(true, user);
		 
		 userChars.forEach(character -> character.setIdOwner(idUser));
	     sharedChars.forEach(character -> character.setIdOwner(-1L));
	     
	     userChars.addAll(sharedChars);
	     
		 return ResponseEntity.ok(userChars);
		   
	   }
	 
	 
	 // Find a character by ID:

	 @GetMapping("/{idCharacter}")
	 public ResponseEntity<?> findCharacterById(@PathVariable(name="idCharacter") Long idCharacter){
	  if(idCharacter==null) {
		  
		 return ResponseEntity.badRequest().body("Can't find the anime with null ID");
		 }
	 AnimeCharacter character = characterRepositery.getOne(idCharacter);
	  if(character==null) {
		  
		 return ResponseEntity.notFound().build();
			
		}
		 return ResponseEntity.ok(character);
			 
	 }
	 
	 @PostMapping("/")
	 public ResponseEntity<?> createCharacter(@RequestBody AnimeCharacter character){
		if(character==null) {
			return ResponseEntity.badRequest().body("Can't create the character with empty field");
		}
		 
		 
		return ResponseEntity.ok(characterRepositery.save(character));
		 
	 }
	 
	 
	 
	 @DeleteMapping("/{idCharacter}")
	    public ResponseEntity<?> deleteCharacter(@PathVariable(name = "idCharacter") Long idCharacter) {
	        if (idCharacter == null) {
	            return ResponseEntity.badRequest().body("Cannot remove character with null ID");
	        }
	        AnimeCharacter character = characterRepositery.getOne(idCharacter);
	        if (character == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        characterRepositery.delete(character);
	        return ResponseEntity.ok("Character removed with success");
	    }
	 
	 //To share a character with another one 
	 
	 @GetMapping("/share/{idCharacter}/{isShared}")
	    public ResponseEntity<?> shareCharacter(@PathVariable(name = "idCharacter") Long idCharacter, @PathVariable(name = "isShared") boolean isShared) {
	        if (idCharacter == null) {
	            return ResponseEntity.badRequest().body("Cannot share character with null ID");
	        }
	        AnimeCharacter character = characterRepositery.getOne(idCharacter);
	        if (character == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        character.setShared(!isShared);
	        return ResponseEntity.ok(characterRepositery.save(character));
	    }
	 
	 
	 
	 
	 
}






















