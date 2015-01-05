package com.archipov.utils;

import java.util.ArrayList;

public class Main {

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
		
		/*
		System.out.println("|---|---|---|---|---|");
		System.out.println("|--Отработка Integr-|");
		System.out.println("|---|---|---|---|---|");
		System.out.println("");
		
		Integer firstInteger = 5;
		System.out.println(firstInteger);
		Integer secondInteger = CopyUtils.deepCopy(firstInteger);
		System.out.println("-----------------");
		System.out.println(firstInteger);
		System.out.println(secondInteger);
		System.out.println("----Изменение 1-го-----");
		firstInteger = firstInteger - 1;
		System.out.println(firstInteger);
		System.out.println(secondInteger);

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
		
		System.out.println("|---|---|---|---|---|");
		System.out.println("|Отработка ArrayList|");
		System.out.println("|---|---|---|---|---|");
		System.out.println("");
		
		ArrayList<String> firstArray = new ArrayList<>();
		firstArray.add("first1");
		firstArray.add("first2");
		firstArray.add("first3");
		firstArray.add("first4");		
		System.out.println(firstArray);		
		ArrayList<String> secondArray = CopyUtils.deepCopy(firstArray);
		System.out.println("-----------------");
		System.out.println(firstArray);
		System.out.println(secondArray);
		for(int i = 0; i < secondArray.size(); i ++){
			secondArray.set(i, "Mod" + i);
		}
		System.out.println("----Изменение 1-го-----");
		System.out.println(firstArray);
		System.out.println(secondArray);
		*/

	}

}
