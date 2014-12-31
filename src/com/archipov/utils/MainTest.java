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
		
		String firstString = "FIRST!!!!";
		System.out.println(firstString);
		String secondString = CopyUtils.deepCopy(firstString);
		System.out.println("-----------------");
		System.out.println(firstString);
		System.out.println(secondString);
		System.out.println("----Изменение 1-го-----");
		firstString = "MODiF!!!";
		System.out.println(firstString);
		System.out.println(secondString);
	}

}
