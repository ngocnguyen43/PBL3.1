package com.PBL3.ultils.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTVerify {
	// private static Logger logger = LoggerFactory.getLogger(JWTVerify.class);

	// private static
	private String token;

	public JWTVerify(String token) {
		this.token = token;
	}

	public DecodedJWT verifyingJWT() {
		DecodedJWT decodedJWT = JWT.decode(token);
		return decodedJWT;
	}
}
