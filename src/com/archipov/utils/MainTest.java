package com.archipov.utils;

public class MainTest {

	public static void main(String[] args) {
		
		TestClass firstClass = new TestClass(50, "ֿÿעüהוסÿע", true);
		//TestClass firstClass = new TestClass();		

		TestClass secondClass;
		secondClass = CopyUtils.deepCopy(firstClass);
		firstClass.printData();
		secondClass.printData();
		secondClass.modification();
		System.out.println("---------");
		firstClass.printData();
		secondClass.printData();
		System.out.println("|---|---|---|---|---|");
	}

}
