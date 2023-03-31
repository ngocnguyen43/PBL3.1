package com.PBL3.services.impl;

import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.UserDTO;
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

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class AuthService implements IAuthService {

    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message Login(String email, String password) throws UnexpectedException, NotFoundException, InvalidCredentialsException {
        JWTGeneration JWT;
        HashMap<String, String> claims;
        try {
            claims = new HashMap<>();
            JWT = new JWTGeneration();
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException();
        }
        User user = iUserDAO.findByEmail(email);
        if (user == null) throw new NotFoundException("User Not Found!");
        String hashedPassword = user.getPassword();
        if (!DecryptPassword.Decrypt(password, hashedPassword))
            throw new InvalidCredentialsException("Wrong password");
        String userId = user.getId();
        claims.put("userId", userId);
        claims.put("role", user.getRole().getRoleCode());
        String role = user.getRole().getRoleName();
        String accessToken = JWT.generate(claims);

        Data data = new Data.Builder(accessToken).withRole(role).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Login Success!").build();
        return new Message.Builder(meta).withData(data).build();
//        try {
//            User user = Helper.objectMapper(tempUser, User.class);
//            return authRepository.loginUser(user);
//        } catch (InvalidCredentialsException | NotFoundException e) {
//            Meta meta = new Meta.Builder(e.getStatusCode()).withError(e.getMessage()).build();
//            return new Message.Builder(meta).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Meta meta = new Meta.Builder(500).withError("Login Error").build();
//            return new Message.Builder(meta).build();
//        }
//
    }

    @Override
    public Message Register(UserDTO dto, String type) throws DuplicateEntryException, CreateFailedException, UnexpectedException, NotFoundException, InvalidCredentialsException {
        User isEmailExist = iUserDAO.findByEmail(dto.getEmail());
        if (isEmailExist != null)
            throw new DuplicateEntryException("Email is already used!");
        User isNationalIdExist = iUserDAO.findByNationalId(dto.getNationalId());
        if (isNationalIdExist != null)
            throw new DuplicateEntryException("NationalId is already used");
        User domain = Helper.objectMapper(dto, User.class);
        String id = IDGeneration.generate();
//        String id = userDao.save(domainUser);
        domain.setId(id);
        if (type != null && type.equals("company")) {
            String companyId = IDGeneration.generate();
            String taxtId = IDGeneration.generate(10);
            domain.setCompanyId(companyId);
            domain.setTaxIndentity(taxtId);
        }
        try {
            iUserDAO.save(domain);
            return this.Login(domain.getEmail(), domain.getPassword());
        } catch (Exception e) {
            if (e instanceof UnexpectedException)
                throw new UnexpectedException();
            if (e instanceof NotFoundException)
                throw new NotFoundException("User Not Found");
            if (e instanceof InvalidCredentialsException)
                throw new InvalidCredentialsException("Wrong Password!");
            else
                throw new CreateFailedException("User not found!");
        }

    }

    @Override
    public Message InspectorRegister(UserDTO dto) throws DuplicateEntryException, CreateFailedException {
        User isEmailExist = iUserDAO.findByEmail(dto.getEmail());
        if (isEmailExist != null)
            throw new DuplicateEntryException("Email is already used!");
        User isNationalIdExist = iUserDAO.findByNationalId(dto.getNationalId());
        if (isNationalIdExist != null)
            throw new DuplicateEntryException("NationalId is already used");
        User domain = Helper.objectMapper(dto, User.class);
        String id = IDGeneration.generate();
        domain.setRoleId(2);
        domain.setId(id);
        try {
            iUserDAO.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Successfully").build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException("Create Failed!");
        }
    }

}
