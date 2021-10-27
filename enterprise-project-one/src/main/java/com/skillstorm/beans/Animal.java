package com.skillstorm.beans;

import java.util.Objects;

/**
 * Represents an animal 
 * 
 * @author Matthew
 *
 */
public class Animal {

	private int id;
	private String name;
	private String scientific;
	private int lifespan;
	private double weight;
	private String diet;
	private String status;
	private String picurl;
	
	/**
	 * Constructor for Animal
	 * 
	 * @param id - id of the animal in the db
	 * @param name - name of animal
	 * @param scientific - sci name of animal
	 * @param lifespan - lifespan of animal
	 * @param weight - weight of animal
	 * @param diet - diet of animal
	 * @param status - status of animal
	 * @param picurl - url picture of animal
	 *
	 */
	public Animal(int id, String name, String scientific, int lifespan, double weight, String diet, String status,
			String picurl) {
		this.id = id;
		this.name = name;
		this.scientific = scientific;
		this.lifespan = lifespan;
		this.weight = weight;
		this.diet = diet;
		this.status = status;
		this.picurl = picurl;
	}
	
	/**
	 * Constructor for Animal
	 *
	 * @param name - name of animal
	 * @param scientific - sci name of animal
	 * @param lifespan - lifespan of animal
	 * @param weight - weight of animal
	 * @param diet - diet of animal
	 * @param status - status of animal
	 * @param picurl - url picture of animal
	 *
	 */
	public Animal(String name, String scientific, int lifespan, double weight, String diet, String status,
			String picurl) {
		this.name = name;
		this.scientific = scientific;
		this.lifespan = lifespan;
		this.weight = weight;
		this.diet = diet;
		this.status = status;
		this.picurl = picurl;
	}

	/**
	 * Constructor for Animal with no params
	 *
	 */
	public Animal() {
		super();
	}
	
	/**
	 * Returns the id
	 * 
	 * @return id
	 *
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id
	 * 
	 * @param id
	 *
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name
	 * 
	 * @return name
	 *
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the sci name
	 * 
	 * @return sci name
	 *
	 */
	public String getScientific() {
		return scientific;
	}
	
	/**
	 * Sets the sci name
	 * 
	 * @param sci name
	 *
	 */
	public void setScientific(String scientific) {
		this.scientific = scientific;
	}

	/**
	 * Returns the lifespan
	 * 
	 * @return lifespan
	 *
	 */
	public int getLifespan() {
		return lifespan;
	}

	/**
	 * Sets the lifespan
	 * 
	 * @param lifespan
	 *
	 */
	public void setLifespan(int lifespan) {
		this.lifespan = lifespan;
	}

	/**
	 * Returns the weight
	 * 
	 * @return weight
	 *
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight
	 * 
	 * @param weight
	 *
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Returns the diet
	 * 
	 * @return diet
	 *
	 */
	public String getDiet() {
		return diet;
	}

	/**
	 * Sets the diet
	 * 
	 * @param diet
	 *
	 */
	public void setDiet(String diet) {
		this.diet = diet;
	}

	/**
	 * Returns the status
	 * 
	 * @return status
	 *
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status
	 * 
	 * @param status
	 *
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the picurl
	 * 
	 * @return picurl
	 *
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * Sets the picurl
	 * 
	 * @param picurl
	 *
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	/**
	 * Hashcode override
	 */
	@Override
	public int hashCode() {
		return Objects.hash(diet, id, lifespan, name, picurl, scientific, status, weight);
	}

	/**
	 * Equals override
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(diet, other.diet) && id == other.id && lifespan == other.lifespan
				&& Objects.equals(name, other.name) && Objects.equals(picurl, other.picurl)
				&& Objects.equals(scientific, other.scientific) && Objects.equals(status, other.status)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}

	/**
	 * ToString override
	 */
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", scientific=" + scientific + ", lifespan=" + lifespan
				+ ", weight=" + weight + ", diet=" + diet + ", status=" + status + ", picurl=" + picurl + "]";
	}
	
	
	
}
