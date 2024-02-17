package com.virtusa.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	@NotNull()
	@Size(min = 3, max = 20,message = "Name Should be 3 to 20 characters long")
	private String name;
	
	@Email
	private String email;
	
	@Size(min = 6, message = "Password Should be 6 characters long")
	private String password;

}
