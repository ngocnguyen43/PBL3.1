package com.PBL3.services;

import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.UserSigninDTO;
import com.PBL3.utils.response.Message;

public interface IAuthService {
	Message Signin(UserSigninDTO user);
	Message Register(UserDTO user);
}
