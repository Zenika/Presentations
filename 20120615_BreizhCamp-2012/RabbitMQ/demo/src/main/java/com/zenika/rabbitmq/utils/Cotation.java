package com.zenika.rabbitmq.utils;

import java.io.Serializable;

public class Cotation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String marche;
	private String nom;
	private String valeur = "0.0";

	public Cotation(String marche, String nom) {
		this.marche = marche;
		this.nom = nom;
	}

	public String getMarche() {
		return marche;
	}

	public void setMarche(String marche) {
		this.marche = marche;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t").append(marche);
		sb.append("\t").append(nom);
		sb.append("\t").append(valeur);
		return sb.toString();
	}

}
