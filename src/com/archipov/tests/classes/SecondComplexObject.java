package com.archipov.tests.classes;

public class SecondComplexObject {
	
	private ComplexClass parrentComplex;
	private double id;

	public SecondComplexObject(ComplexClass cs) {
		parrentComplex = cs;
		id = Math.random()*1000;		
	}
	
	public String printID(){
		//System.out.println("Second obj - " + id + "l; ParrentFirst - " + parrentComplex.getID());
		return "Second obj - " + id + "l; ParrentFirst - " + parrentComplex.getID();
	}
	
	public double getID(){
		return this.id;
	}
	/*
	@Override
	public String toString() {
		Double idToString = new Double(id);
		return idToString.toString();
	}
	*/

}
