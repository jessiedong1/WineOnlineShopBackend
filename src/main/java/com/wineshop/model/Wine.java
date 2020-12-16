package com.wineshop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="wine")
public class Wine implements Serializable{
	private static final long serialVersionUID  =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="point")
	private double point;
	
	@Column(name="price")
	private double price;
	
	@Column(name="description", length=500)
	private String description;
	
	@Column(name="taster_name")
	private String taster_name;
	
	@Column(name="taster_twitter_handle")
	private String taster_twitter_handle;
	
	@Column(name="winery")
	private String winery;
	
 	@Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    

    @ManyToOne
    @JoinColumn(name = "variety_id")
    private Variety variety;
    
    @Column(name = "descriptors")
    private String descriptors;
    
    @Column(name="year")
    private String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDescriptors() {
		return descriptors;
	}

	public void setDescriptors(String descriptors) {
		this.descriptors = descriptors;
	}

	public Wine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaster_name() {
		return taster_name;
	}

	public void setTaster_name(String taster_name) {
		this.taster_name = taster_name;
	}

	public String getTaster_twitter_handle() {
		return taster_twitter_handle;
	}

	public void setTaster_twitter_handle(String taster_twitter_handle) {
		this.taster_twitter_handle = taster_twitter_handle;
	}

	public String getWinery() {
		return winery;
	}

	public void setWinery(String winery) {
		this.winery = winery;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Variety getVariety() {
		return variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", title=" + title + ", point=" + point + ", price=" + price + ", description="
				+ description + ", taster_name=" + taster_name + ", taster_twitter_handle=" + taster_twitter_handle
				+ ", winery=" + winery + ", imageUrl=" + imageUrl + ", active=" + active + ", unitsInStock="
				+ unitsInStock + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated + ", region=" + region
				+ ", variety=" + variety + ", descriptors=" + descriptors + "]";
	}

	

}
