package com.archipov.utils;

public class MainTest {

	public static void main(String[] args) {
		
		
		TestClass firstClass = new TestClass(50, "Пятьдесят", true);

		TestClass secondClass;
		secondClass = CopyUtils.deepCopy(firstClass);
		firstClass.printData();
		secondClass.printData();
		secondClass.modification();
		System.out.println("---------");
		firstClass.printData();
		secondClass.printData();
		System.out.println("");
		System.out.println("|---|---|---|---|---|");
		System.out.println("|--Отработка String-|");
		System.out.println("|---|---|---|---|---|");
		System.out.println("");
		
		Integer firstString = 5;
		System.out.println(firstString);
		Integer secondString = CopyUtils.deepCopy(firstString);
		System.out.println("-----------------");
		System.out.println(firstString);
		System.out.println(secondString);
		


	}

}
