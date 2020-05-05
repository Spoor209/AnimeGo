package com.udemy.controllers;
/**
 * 
 * @author adlenkhelladi
 *
 */

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.entities.AnimeCharacter;
import com.udemy.entities.User;
import com.udemy.repositeries.IAnimeCharacter;
import com.udemy.repositeries.IUser;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/v1/photos")
public class PhotoController {
	
	@Autowired
	private IAnimeCharacter characterRepositery;
	
	@Autowired
	private IUser userRepositery;
	
	@GetMapping("/character/{idCharacter}")
	public ResponseEntity<?> photoCharacter(@PathVariable Long idCharacter){
		if (idCharacter==null) {
			return ResponseEntity.badRequest().body("Can't get character's photo with a null id");
		}
		AnimeCharacter character = characterRepositery.getOne(idCharacter);
		if(character==null) {
			return ResponseEntity.notFound().build();
		}
		if(character.getPhoto()==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_GIF)
				.contentType(MediaType.IMAGE_JPEG)
				.contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(new ByteArrayInputStream(character.getPhoto())));
		
	}
	
	@GetMapping("/user/{idUser}")
	public ResponseEntity<?> photoUser(@PathVariable Long idUser){
		if (idUser==null) {
			return ResponseEntity.badRequest().body("Can't get user's photo with a null id");
		}
		User user = userRepositery.getOne(idUser);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		if(user.getPhoto()==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_GIF)
				.contentType(MediaType.IMAGE_JPEG)
				.contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(new ByteArrayInputStream(user.getPhoto())));
		
	}

}
