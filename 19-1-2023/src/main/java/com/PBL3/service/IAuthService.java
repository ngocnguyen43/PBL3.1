package com.PBL3.service;

import com.PBL3.DTO.UserSigninDTO;
import com.PBL3.helpers.message.SigninMessage;

public interface IAuthService {
	SigninMessage Signin(UserSigninDTO user);
}
