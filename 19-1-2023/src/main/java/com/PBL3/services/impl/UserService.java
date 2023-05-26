package com.PBL3.services.impl;

import com.PBL3.daos.*;
import com.PBL3.dtos.UserDTO;
import com.PBL3.dtos.pagination.UserPaginationDTO;
import com.PBL3.models.*;
import com.PBL3.models.pagination.UserPagination;
import com.PBL3.services.INotificationService;
import com.PBL3.services.IUserService;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.helpers.HashPassword;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

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
    @Inject
    private INotificationService iNotificationService;

    @Override
    public Message findAll(String role) throws InvalidPropertiesException {
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
//        if (users == null) throw new NotFoundException("Not Found Users");
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(users).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message findAll(UserPaginationDTO dto, String id) throws InvalidPropertiesException {
        UserPagination domain = Helper.objectMapper(dto, UserPagination.class);
        if (domain.getPage() < 0) domain.setPage(1);
        String role = userDao.getUserRole(id).toUpperCase();
        System.out.println(role);
        if (!role.equals("ADMIN") && !role.equals("MODERATOR"))
            throw new InvalidPropertiesException("Invalid credentials");
        List<User> users = userDao.findAll(role, domain);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK!").build();
        Data data = new Data.Builder(null).withResults(users).build();

        Integer pages = userDao.countAllRecord(domain, role);
        Pagination pagination = new Pagination.Builder().
                withCurrentPage(domain.getPage()).
                withTotalPages((int) Math.ceil((double) pages / PER_PAGE)).
                withTotalResults(pages).build();
        return new Message.Builder(meta).withData(data).withPagination(pagination).build();
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
        boolean isEmailExist = userDao.findByEmail(dto.getEmail()) != null;
        if (isEmailExist) throw new DuplicateEntryException("Email has Already Registered");
        boolean isNationalIdExist = userDao.findByNationalId(dto.getNationalId()) != null;
        if (isNationalIdExist) throw new DuplicateEntryException("National Id has Already Registered");
        User domain = Helper.objectMapper(dto, User.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        if (dto.getBusinessId() != null) {
            String companyId = IDGeneration.generate();
            domain.setCompanyId(companyId);
        }
        domain.setPassword(HashPassword.HashPW(domain.getPassword()));
        try {
            userDao.save(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATE_FAILED);
        }
    }

    @Override
    public Message delete(String userId, String creator) throws InvalidPropertiesException, UnexpectedException, InvalidPropertiesFormatException, JsonProcessingException {
        if (creator == null) throw new InvalidPropertiesException("Invalid properties");
        String role = userDao.getUserRole(creator);
        String name = userDao.getUserName(creator);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(creator)
                .withMods(Collections.singletonList("all"))
                .withAdmin(!role.equals("Admin"))
                .withMessage("User " + userId + " was deleted by " + (role.equals("Admin") ? role : name))
                .build();

        userDao.delete(userId);
        iNotificationService.create(notification);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message findOne(String id) {
        User user = userDao.findByUserId(id);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(user).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message findAllMods() {
        List<User> users = userDao.getAllMods();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(users).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message update(UserDTO dto, String id, String userId) throws DuplicateEntryException, UpdateFailedException, NotFoundException, InvalidPropertiesException {
        if (userId == null) throw new InvalidPropertiesException("Invalid properties");
        String role = userDao.getUserRole(userId);
        String uRole = userDao.getUserRole(id);
        System.out.println(role);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMods(uRole.equals("Moderator") ? Collections.singletonList(id) : null)
                .withUsers(uRole.equals("Store") ? Collections.singletonList(id) : null)
                .withMessage("You was updated by " + role)
                .build();
        if (id == null) throw new InvalidPropertiesException("Invalid Property");
        if (dto.getEmail() != null) {
            boolean isEmailExist = userDao.findByEmail(dto.getEmail()) != null;
            if (!isEmailExist) throw new NotFoundException("User Not Found");
        }
        if (dto.getNationalId() != null) {
            boolean isNationalIdExist = userDao.findByNationalId(dto.getNationalId()) != null;
            if (!isNationalIdExist) throw new NotFoundException("User Not Found");
        }
        User oldUser = userDao.findByUserId(id);
        User user = new User();
        if (dto.getCompanyName() != null) {
            user.setCompanyName(dto.getCompanyName());
        } else {
            user.setCompanyName(oldUser.getCompanyName());
        }
        if (dto.getTaxIndentity() != null) {
            user.setTaxIndentity(dto.getTaxIndentity());
        } else {
            user.setTaxIndentity(oldUser.getTaxIndentity());
        }
        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        } else {
            user.setPhoneNumber(oldUser.getPhoneNumber());
        }
        if (dto.getFaxNumber() != null) {
            user.setFaxNumber(dto.getFaxNumber());
        } else {
            user.setFaxNumber(oldUser.getFaxNumber());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(oldUser.getEmail());
        }
        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        } else {
            user.setFullName(oldUser.getFullName());
        }
        if (dto.getNationalId() != null) {
            user.setNationalId(dto.getNationalId());
        } else {
            user.setNationalId(oldUser.getNationalId());
        }
        if (dto.getUserNumber() != null) {
            user.setUserNumber(dto.getUserNumber());
        } else {
            user.setUserNumber(oldUser.getUserNumber());
        }
        if (dto.getPassword() != null) {
            user.setPassword(HashPassword.HashPW(dto.getPassword()));
        } else {
            user.setPassword(oldUser.getPassword());
        }
        user.setId(id);
        try {
            userDao.update(user);
            iNotificationService.create(notification);
        } catch (Exception e) {
            throw new UpdateFailedException("Update User Failed");
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
        return new Message.Builder(meta).build();
    }

//	@Override
//	public List<Role> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
