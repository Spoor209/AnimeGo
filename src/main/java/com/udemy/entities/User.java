package com.udemy.entities;
/**
 * 
 * @author adlenkhelladi
 *
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idUser;
	
	private String firstName;
	
	private String lastName;
	
	private String mail;
	
	private String password;
	
	//indicate big photo format:
	@Lob
	private byte[] photo;
	
	
	@OneToMany(mappedBy="user")
	private List<AnimeCharacter> characters;
	
	@JsonIgnore
    public List<AnimeCharacter> getCharacters() {
        return characters;
    }
	
	
	

}
