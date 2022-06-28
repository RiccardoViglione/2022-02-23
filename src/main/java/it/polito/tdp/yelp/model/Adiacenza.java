package it.polito.tdp.yelp.model;

public class Adiacenza {
private Review r1;
private Review r2;
private Double peso;

public Adiacenza(Review r1, Double peso) {
	super();
	this.r1 = r1;
	this.peso = peso;
}
public Adiacenza(Review r1, Review r2, Double peso) {
	super();
	this.r1 = r1;
	this.r2 = r2;
	this.peso = peso;
}
public Review getR1() {
	return r1;
}
public void setR1(Review r1) {
	this.r1 = r1;
}
public Review getR2() {
	return r2;
}
public void setR2(Review r2) {
	this.r2 = r2;
}
public Double getPeso() {
	return peso;
}
public void setPeso(Double peso) {
	this.peso = peso;
}
@Override
public String toString() {
	return this.getR1().getReviewId()+"  # ARCHI USCENTI: "+this.getPeso();
}

}
