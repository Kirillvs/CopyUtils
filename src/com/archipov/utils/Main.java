package com.archipov.utils;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		
		TestClass firstClass = new TestClass(50, "ֿÿעüהוסÿע", true);

		TestClass secondClass;
		secondClass = CopyUtils.deepCopy(firstClass);
		firstClass.printData();
		secondClass.printData();
		secondClass.modification();
		System.out.println("---------");
		firstClass.printData();
		secondClass.printData();
		System.out.println("");		

	}

}
