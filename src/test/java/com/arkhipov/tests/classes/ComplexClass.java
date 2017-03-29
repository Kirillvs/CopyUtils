package com.archipov.tests.classes;

public class ComplexClass {
	
	private double id;
	SecondComplexObject sco;
	
	public ComplexClass() {
		id = Math.random()*1000;
		sco = new SecondComplexObject(this);
	}
	
	public void printID(){
		//System.out.println("First obj - " + id);
		sco.printID();
	}
	
	public double getID(){
		return this.id;
	}
	
	public SecondComplexObject getChild(){
		return sco;
	}
	
	@Override
	public String toString() {
		Double idToString = new Double(id);
		return idToString.toString();
	}	

}
