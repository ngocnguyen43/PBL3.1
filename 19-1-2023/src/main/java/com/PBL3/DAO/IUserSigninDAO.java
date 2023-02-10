package com.PBL3.DAO;

import com.PBL3.model.UserSignin;

public interface IUserSigninDAO {
	UserSignin findOne(String email);
}
