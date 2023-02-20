package com.PBL3.models;

public class User extends AbstractModel {
	private String userId;
	private Integer roleId;
	private String fullName;
	private String nationalId;
	private Integer gender;
	private String phoneNumber;
	private String email;
//	private String userName;
	private String password;
	private Integer action = 1;
	private Role role ;
	
//	public static  User withUserDTO(UserDTO tempUser) {
//		User user =new User();
//		user.setRoleId(tempUser.getRoleId());
//		user.setFirstName(tempUser.getFirstName());
//		user.setLastName(tempUser.getLastName());
//		user.setGender(tempUser.getGenderId());
//		user.setNationalId(tempUser.getNationalId());
//		user.setPhoneNumber(tempUser.getPhoneNumber());
//		user.setEmail(tempUser.getEmail());
//		user.setPassword(tempUser.getPassword());
//		return user;
//	}
	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullname) {
		this.fullName = fullname;
	}



	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
