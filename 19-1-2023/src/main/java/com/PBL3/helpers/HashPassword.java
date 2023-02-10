package com.PBL3.helpers;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashPassword {
	
	public static String HashPW(String password) {
		String Salt = BCrypt.gensalt("$2b",10);
		  String Newpassword = BCrypt.hashpw(password, Salt);
		return Newpassword;
	}
}
