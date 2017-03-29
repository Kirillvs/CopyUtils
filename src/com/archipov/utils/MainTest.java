package com.archipov.utils;

public class MainTest {

	public static void main(String[] args) {
		
		
		TestClass firstClass = new TestClass(50, "���������", true);

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
		System.out.println("|--��������� Integr-|");
		System.out.println("|---|---|---|---|---|");
		System.out.println("");
		
		Integer firstInteger = 5;
		System.out.println(firstInteger);
		Integer secondInteger = CopyUtils.deepCopy(firstInteger);
		System.out.println("-----------------");
		System.out.println(firstInteger);
		System.out.println(secondInteger);
		System.out.println("----��������� 1-��-----");
		firstInteger = firstInteger - 1;
		System.out.println(firstInteger);
		System.out.println(secondInteger);

		System.out.println("|---|---|---|---|---|");
		System.out.println("|--��������� String-|");
		System.out.println("|---|---|---|---|---|");
		System.out.println("");
		
		String firstString = "FIRST!!!!";
		System.out.println(firstString);
		String secondString = CopyUtils.deepCopy(firstString);
		System.out.println("-----------------");
		System.out.println(firstString);
		System.out.println(secondString);
		System.out.println("----��������� 1-��-----");
		firstString = "MODiF!!!";
		System.out.println(firstString);
		System.out.println(secondString);
	}

}
