package com.PBL3.service;

import com.PBL3.controller.SigninMessage;
import com.PBL3.model.UserSignin;

public interface ISigninService {
	SigninMessage Signin(UserSignin user);
}
