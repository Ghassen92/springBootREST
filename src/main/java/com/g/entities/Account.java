package com.g.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int rib;
	private double solde;
	@JsonIgnore
	@ManyToOne
	private User user;

	public Account() {

	}

	public Account(int rib, double solde) {
		super();
		this.rib = rib;
		this.solde = solde;
	}

	public int getRib() {
		return rib;
	}

	public void setRib(int rib) {
		this.rib = rib;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}

}
