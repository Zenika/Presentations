package com.zenika.rabbitmq.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CotationBuilder {

	static List<Cotation> cotations = new ArrayList<Cotation>();
	static {

		cotations.add(new Cotation("nasq", "GOOG"));
		cotations.add(new Cotation("nasq", "YHOO"));
		cotations.add(new Cotation("nasq", "IBM"));
		cotations.add(new Cotation("nasq", "ORCL"));
		cotations.add(new Cotation("ernxt", "GLE"));
		cotations.add(new Cotation("ernxt", "ALU"));
		cotations.add(new Cotation("ernxt", "EDF"));
		cotations.add(new Cotation("ernxt", "BNP"));
	}

	static public Cotation buildCotation() {
		Random r = new Random();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		Cotation cotation = cotations.get(r.nextInt(cotations.size()));
		cotation.setValeur(decimalFormat.format((Math.random()) * r.nextInt(100)));
		return cotation;
	}
}
