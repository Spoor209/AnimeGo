package com.udemy.entities;
/**
 * 
 * @author adlenkhelladi
 *
 */

import java.io.Serializable;
import javax.persistence.*;

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
public class AnimeCharacter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idCharacter;
	
	private String characterName;
	
	private String category;
	
	private String strength;
	
	private String legend;
	
	@Lob
	private byte[]photo;
	
	//To share a character with the others:
	private boolean shared; 
	
	@Transient
	private Long idOwner;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user; 
	
	 @JsonIgnore
	 public User getUser() {
	 return user;
	    }
	
	

}
