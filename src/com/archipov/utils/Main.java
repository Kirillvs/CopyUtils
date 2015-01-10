package com.archipov.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

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
		LinkedList<Integer> asd = new LinkedList<>();
		asd.add(2);
		System.out.println(asd instanceof Map);
	}

}
