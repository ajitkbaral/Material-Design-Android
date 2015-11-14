package com.pagodalabs.materialdesign.entities;

import java.io.Serializable;

public class Professional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer professionalId;
	private Integer categoryId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;
	private Integer activation;

	public Professional() {
	}

	public Professional(Integer professionalId, Integer categoryId,
			String firstName, String lastName, String email, String phone,
			String description, Integer activation) {
		super();
		this.professionalId = professionalId;
		this.categoryId = categoryId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.activation = activation;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getActivation() {
		return activation;
	}

	public void setActivation(Integer activation) {
		this.activation = activation;
	}

	@Override
	public String toString() {
		return "Profession [professionalId=" + professionalId + ", categoryId="
				+ categoryId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone
				+ ",description=" + description + ", activation=" + activation
				+ "]";
	}

}
