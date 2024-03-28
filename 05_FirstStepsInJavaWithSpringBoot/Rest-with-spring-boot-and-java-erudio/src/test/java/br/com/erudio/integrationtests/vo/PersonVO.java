package br.com.erudio.integrationtests.vo;

import java.io.Serializable;
import java.util.Objects;

public class PersonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;

	public PersonVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirst_Name() {
		return firstName;
	}

	public void setFirst_Name(String firstName) {
		this.firstName = firstName;
	}

	public String getLast_Name() {
		return lastName;
	}

	public void setLast_Name(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, gender, id, lastName, address);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(address, other.address);
	}
	
	
}
