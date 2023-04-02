package com.PBL3.services.impl;

import com.PBL3.daos.*;
import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.models.*;
import com.PBL3.services.IUserService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.InvalidPropertiesException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.HashPassword;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    @Inject
    private IUserDAO userDao;
    @Inject
    private IBusinessDAO businessDAO;
    @Inject
    private IProductDAO productDAO;
    @Inject
    private IProductCertificateDAO productCertificateDAO;
    @Inject
    private ICertificateDAO iCertificateDAO;
    @Inject
    private IKindOfProductDAO kindOfProductDAO;

    @Override
    public Message findAll(String role) throws NotFoundException, InvalidPropertiesException {
        // TODO Auto-generated method stub
        String[] roles = {"ADMIN", "MOD"};
        if (!ArrayUtils.contains(roles, role)) throw new InvalidPropertiesException("Invalid Role");
        List<User> users = userDao.findAll(role);
        for (User user : users) {
            Business business = businessDAO.findOneById(user.getBusinessId());
            user.setBusiness(business);
            List<ProductModel> products = productDAO.findAllByUserId(user.getId());
            for (ProductModel product : products) {
                List<ProductCertificatesModel> productCertificates = productCertificateDAO.findAllById(product.getId());
                KindOfProductModel kindOfProductModel = kindOfProductDAO.findOne(product.getKindof());
                product.setKindOfProductModel(kindOfProductModel);
                List<Certificate> certificates = new ArrayList<>();
                for (ProductCertificatesModel productCertificate : productCertificates) {
                    Certificate certificate = iCertificateDAO.findOne(productCertificate.getCertificateId());
                    certificates.add(certificate);
                }
                product.setCertificate(certificates);
            }
            user.setProductModel(products);
        }
        if (users == null) throw new NotFoundException("Not Found Users");
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
        Data data = new Data.Builder(null).withResults(users).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message findAll(UserPaginationDTO dto, String role) {
        return null;
    }

    @Override
    public Message findByUserId(String id) {
        // TODO Auto-generated method stub
//		Message message = new Message();
//		User user = userDao.findByUserId(id);
//		if (user != null) {
//			Meta header = new Meta.Builder(HttpServletResponse.SC_OK).withErrCode(0).withMessage("User has found!").build();
//			UserDTO userDTO = Helper.objectMapper(user, UserDTO.class);
//			userDTO.setPassword(null);
//			userDTO.setRoleId(null);
//			Data body = new Data.Builder(null).withResults(userDTO).build();
//			
//			message.setBody(body);
//			message.setHeader(header);
//		}else {
//			Meta header = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withErrCode(6).withMessage("User not found!").build();
//			message.setHeader(header);
//		}
        return null;
    }

    @Override
     public Message save(UserDTO dto) throws DuplicateEntryException, CreateFailedException {
        // TODO Auto-generated method stub
        boolean isEmailExist = userDao.findByEmail(dto.getEmail()) != null;
        if (isEmailExist) throw new DuplicateEntryException("Email has Already Registered");
        boolean isNationalIdExist = userDao.findByNationalId(dto.getNationalId()) != null;
        if (isNationalIdExist) throw new DuplicateEntryException("National Id has Already Registered");
        User domain = Helper.objectMapper(dto,User.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        domain.setPassword(HashPassword.HashPW(domain.getPassword()));
        try {
            userDao.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("OK").build();
            return new Message.Builder(meta).build();
        }catch (Exception e){
            throw new CreateFailedException("Create New User Failed");
        }
//        user.setPassword(HashPassword.HashPW(user.getPassword()));
//        String userId = userDao.save(user);
//        return userDao.findByUserId(userId);
    }

    @Override
    public void delete(String userId) {
        userDao.delete(userId);
        // TODO Auto-generated method stub


    }

//	@Override
//	public List<Role> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
