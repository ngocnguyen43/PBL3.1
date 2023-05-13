package com.PBL3.services.impl;

import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.UserSigninDTO;
import com.PBL3.models.User;
import com.PBL3.services.IAuthService;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.DecryptPassword;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.helpers.JWTGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class AuthService implements IAuthService {

    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message Login(UserSigninDTO dto) throws UnexpectedException, NotFoundException, InvalidCredentialsException {
        if (dto.getEmail() == null || dto.getPassword() == null)
            throw new InvalidCredentialsException(Response.INVALID_EMAIL_OR_PASSWORD);
        JWTGeneration JWT;
        HashMap<String, String> claims;
        try {
            claims = new HashMap<>();
            JWT = new JWTGeneration();
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException();
        }
        User user = iUserDAO.findByEmail(dto.getEmail());
        if (user == null) throw new NotFoundException(Response.USER_NOT_FOUND);
        String hashedPassword = user.getPassword();
        try {

            if (!DecryptPassword.Decrypt(dto.getPassword(), hashedPassword))
                throw new InvalidCredentialsException(Response.WRONG_PASSWORD);
            String userId = user.getId();
            claims.put("userId", userId);
            claims.put("role", user.getRole().getRoleCode());
            String role = user.getRole().getRoleName();
            String accessToken = JWT.generate(claims);

            Data data = new Data.Builder(accessToken).withRole(role).withId(userId).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.LOGIN_SUCCESS).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnexpectedException();
        }
    }

    @Override
    public Message Register(UserDTO dto, String type) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException {
        User isEmailExist = iUserDAO.findByEmail(dto.getEmail());
        if (isEmailExist != null)
            throw new DuplicateEntryException(Response.EMAIL_IN_USE);
        User isNationalIdExist = iUserDAO.findByNationalId(dto.getNationalId());
        if (isNationalIdExist != null)
            throw new DuplicateEntryException(Response.NATIONAL_ID_IN_USE);
        User domain = Helper.objectMapper(dto, User.class);
        String id = IDGeneration.generate();
//        String id = userDao.save(domainUser);
        domain.setId(id);
        if (type != null && type.equals("company")) {
            String companyId = IDGeneration.generate();
//            String taxtId = IDGeneration.generate(10);
            domain.setCompanyId(companyId);
//            domain.setTaxIndentity(taxtId);
        }
        try {
            iUserDAO.save(domain);
            UserSigninDTO loginDto = Helper.objectMapper(dto, UserSigninDTO.class);
            return this.Login(loginDto);
        } catch (Exception e) {
            if (e instanceof UnexpectedException)
                throw new UnexpectedException();
            if (e instanceof NotFoundException)
                throw new NotFoundException(Response.USER_NOT_FOUND);
            if (e instanceof InvalidCredentialsException)
                throw new InvalidCredentialsException(Response.WRONG_PASSWORD);
            else
                throw new CreateFailedException(Response.REGISTERED_FAILED);
        }

    }

    @Override
    public Message InspectorRegister(UserDTO dto) throws DuplicateEntryException, CreateFailedException {
        User isEmailExist = iUserDAO.findByEmail(dto.getEmail());
        if (isEmailExist != null)
            throw new DuplicateEntryException(Response.EMAIL_IN_USE);
        User isNationalIdExist = iUserDAO.findByNationalId(dto.getNationalId());
        if (isNationalIdExist != null)
            throw new DuplicateEntryException(Response.NATIONAL_ID_IN_USE);
        User domain = Helper.objectMapper(dto, User.class);
        String id = IDGeneration.generate();
        domain.setRoleId(2);
        domain.setId(id);
        try {
            iUserDAO.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }

}
