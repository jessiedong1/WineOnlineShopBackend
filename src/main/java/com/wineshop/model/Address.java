package com.wineshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="address")
public class Address implements Serializable{
	private static final long serialVersionUID  =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column
	private String firstName;
	
	@Column
	private String  lastName;
	
	@Column
	private String  line1;
	
	@Column
	private String  line2;
	
	@Column
	private String  state;
	
	@Column
	private int  zip;
	
	@Column
	private String  country;
	
	@Column
	private String  email;
	
	@Column
	private long phone;
	
//	@JsonIgnore
//    @OneToOne(mappedBy = "address")
//    private Order order;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Address(Long id, String firstName, String lastName, String line1, String line2, String state, int zip,
			String country, String email, long phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.line1 = line1;
		this.line2 = line2;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.email = email;
		this.phone = phone;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	

}
