package com.archipov.utils;

public class TestClass {
	
	
	private int first;
	private String second;
	private boolean third;
	
	/*public TestClass() {
		first = 0;
		second = "<->";
		third = true;
	}*/
	
	public TestClass(int _first, String _second, boolean _third){
		first = _first;
		second = _second;
		third = _third;
	}
	
	public void modification(){
		first = 100500;
		second = "Сто";
		third = false;
	}
	
	public void printData(){
		System.out.println(first + " | " + second + " | " + third);
	}

}
