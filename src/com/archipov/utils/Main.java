package com.archipov.utils;

import java.util.ArrayList;
import java.util.HashMap;
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
		TreeMap<String, Integer> tree = new TreeMap<>();
		tree.put("one", 1);
		ConcurrentHashMap<String, Integer> testMap = new ConcurrentHashMap<>();
		testMap.put("first", 2);
		System.out.println(tree instanceof Map);
		int z =5;
		int za = CopyUtils.deepCopy(z);
		System.out.println(z + " - " + za);	
		za = za + 15;
		System.out.println(z + " - " + za);	
	}

}
