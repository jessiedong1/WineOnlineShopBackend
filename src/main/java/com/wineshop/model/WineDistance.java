package com.wineshop.model;


public class WineDistance implements Comparable<WineDistance> {
	private Wine wine;
	private double distance;
	public Wine getWine() {
		return wine;
	}
	public void setWine(Wine wine) {
		this.wine = wine;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public WineDistance(Wine wine, double distance) {
		super();
		this.wine = wine;
		this.distance = distance;
	}
	public WineDistance() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WineDistance [wine=" + wine + ", distance=" + distance + "]";
	}
	@Override
	public int compareTo(WineDistance o) {
		// TODO Auto-generated method stub
		if(this.distance==o.getDistance())
			return 0;
		if(this.distance<o.getDistance())
			return 1;
		else
			return -1;
	
	}
	
}
