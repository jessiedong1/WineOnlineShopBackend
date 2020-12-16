package com.wineshop.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="variety")
public class Variety implements  Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="variety_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="grape_type")
	private String grapeType;
	
	@Column(name="wine_type")
	private String wineType;
	
//	@JsonIgnore
//    @OneToMany(mappedBy = "variety")
//    private Set<Wine> wines;

	public Variety() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGrapeType() {
		return grapeType;
	}

	public void setGrapeType(String grapeType) {
		this.grapeType = grapeType;
	}

	public String getWinetype() {
		return wineType;
	}

	public void setWinetype(String winetype) {
		this.wineType = winetype;
	}

//	public Set<Wine> getWines() {
//		return wines;
//	}
//
//	public void setWines(Set<Wine> wines) {
//		this.wines = wines;
//	}
//	

}
