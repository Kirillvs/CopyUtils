package com.archipov.tests;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsNot.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.archipov.tests.classes.ComplexClass;
import com.archipov.utils.CopyUtils;
import com.archipov.utils.TestClass;

public class TestCopyUtils {
	
	
	@Test
	public void testSimpleAndEmbededObjectsUsingDeepCopy() {
		
		//1. Ïðîâåðêà êîïèðîâàíèÿ îáúåêòîâ ñïåöèàëüíîãî òåñòîâîãî êëàññà
		TestClass srcTestClass = new TestClass(15, "src", false);
		TestClass finTestClass = CopyUtils.deepCopy(srcTestClass);
		assertEquals(srcTestClass.printData(), finTestClass.printData());
		assertThat(srcTestClass, not(finTestClass));		
		finTestClass.modification();		
		assertNotEquals(srcTestClass.printData(), finTestClass.printData());
		
		//2. Ïðîâåðêà êîïèðîâàíèÿ îáúåêòîâ Integer
		Integer firstInteger = 5;
		Integer secondInteger = CopyUtils.deepCopy(firstInteger);
		assertEquals(firstInteger, secondInteger);
		assertNotSame(firstInteger, secondInteger);
		secondInteger = secondInteger + 5;
		assertNotEquals(firstInteger, secondInteger);
		
		//3 Ïðîâåðêà êîïèðîâàíèÿ îáúåêòîâ String
		String firstString = "First";
		String secondString = CopyUtils.deepCopy(firstString);
		assertEquals(firstString, secondString);
		assertNotSame(firstString, secondString);
		secondString = "Second";
		assertNotEquals(firstString, secondString);
		
		//4. Ïðîâåðêà êîïèðîâàíèÿ îáúåêòîâ ArrayList
		ArrayList<String> firstArray = new ArrayList<>();
		firstArray.add("first1");
		firstArray.add("first2");
		firstArray.add("first3");
		firstArray.add("first4");		
		ArrayList<String> secondArray = CopyUtils.deepCopy(firstArray);
		assertNotSame(firstArray, secondArray);
		for(int i = 0; i < secondArray.size(); i ++){
			assertEquals(firstArray.get(i), secondArray.get(i));
			assertNotSame(firstArray.get(i), secondArray.get(i));
		}
		for(int i = 0; i < secondArray.size(); i ++){
			secondArray.set(i, "Mod" + i);
		}
		for(int i = 0; i < secondArray.size(); i ++){
			assertNotEquals(firstArray.get(i), secondArray.get(i));
		}
		
		//5. HashMap
		HashMap<String, Integer> firstHashMap = new HashMap<>();
		firstHashMap.put("First", 1);
		firstHashMap.put("Second", 2);
		firstHashMap.put("Third", 3);		
		HashMap<String, Integer> secondHashMap = CopyUtils.deepCopy(firstHashMap);
		assertNotSame(firstHashMap, secondHashMap);
		assertEquals(firstHashMap.get("First"), secondHashMap.get("First"));
		assertNotSame(firstHashMap.get("First"), secondHashMap.get("First"));
		assertEquals(firstHashMap.get("Second"), secondHashMap.get("Second"));
		assertNotSame(firstHashMap.get("Second"), secondHashMap.get("Second"));
		assertEquals(firstHashMap.get("Third"), secondHashMap.get("Third"));
		assertNotSame(firstHashMap.get("Third"), secondHashMap.get("Third"));

		//5.1 ConcurrentHashMap
		ConcurrentHashMap<String, Double> firstConcurrentHashMap = new ConcurrentHashMap<>();
		firstConcurrentHashMap.put("Fifty", 50.0);
		firstConcurrentHashMap.put("Sixty", 60.0);
		ConcurrentHashMap<String, Double> secondConcurrentHashMap = CopyUtils.deepCopy(firstConcurrentHashMap);
		assertNotSame(firstConcurrentHashMap, secondConcurrentHashMap);
		assertEquals(firstConcurrentHashMap.get("Fifty"), secondConcurrentHashMap.get("Fifty"));
		assertNotSame(firstConcurrentHashMap.get("Fifty"), secondConcurrentHashMap.get("Fifty"));
		assertEquals(firstConcurrentHashMap.get("Sixty"), secondConcurrentHashMap.get("Sixty"));
		assertNotSame(firstConcurrentHashMap.get("Sixty"), secondConcurrentHashMap.get("Sixty"));
		
		//6. TreeMap
		TreeMap<String, Double> firstTreeMap = new TreeMap<>();
		firstTreeMap.put("Fifty", 50.0);
		firstTreeMap.put("Sixty", 60.0);
		TreeMap<String, Double> secondTreeMap = CopyUtils.deepCopy(firstTreeMap);
		assertNotSame(firstTreeMap, secondTreeMap);
		assertEquals(firstTreeMap.get("Fifty"), secondTreeMap.get("Fifty"));
		assertNotSame(firstTreeMap.get("Fifty"), secondTreeMap.get("Fifty"));
		assertEquals(firstTreeMap.get("Sixty"), secondTreeMap.get("Sixty"));
		assertNotSame(firstTreeMap.get("Sixty"), secondTreeMap.get("Sixty"));

		//7. LinkedList
		LinkedList<String> firstLinkedList = new LinkedList<>();
		firstLinkedList.add("first");
		firstLinkedList.add("second");
		firstLinkedList.add("third");
		LinkedList<String> secondLinkedList = CopyUtils.deepCopy(firstLinkedList);
		assertNotSame(firstLinkedList, secondLinkedList);
		assertEquals(firstLinkedList.get(0), secondLinkedList.get(0));
		assertNotSame(firstLinkedList.get(0), secondLinkedList.get(0));
		assertEquals(firstLinkedList.get(1), secondLinkedList.get(1));
		assertNotSame(firstLinkedList.get(1), secondLinkedList.get(1));
		assertEquals(firstLinkedList.get(2), secondLinkedList.get(2));
		assertNotSame(firstLinkedList.get(2), secondLinkedList.get(2));

		//8. HashSet
		HashSet<String> firstHashSet = new HashSet<>();
		firstHashSet.add("firstSet!!!");
		firstHashSet.add("secondSet!!!");
		HashSet<String> secondHashSet = CopyUtils.deepCopy(firstHashSet);
		assertNotSame(firstHashSet, secondHashSet);
		assertEquals(firstHashSet.toArray()[0], secondHashSet.toArray()[0]);
		assertNotSame(firstHashSet.toArray()[0], secondHashSet.toArray()[0]);
		assertEquals(firstHashSet.toArray()[1], secondHashSet.toArray()[1]);
		assertNotSame(firstHashSet.toArray()[1], secondHashSet.toArray()[1]);
		
		//9 []		
		int[] intOne = new int[3];
		intOne[0] = 5;
		intOne[1] = 6;
		intOne[2] = 7;
		int[] intTwo = CopyUtils.deepCopy(intOne);
		assertEquals(intOne[0], intTwo[0]);
		assertEquals(intOne[1], intTwo[1]);
		assertEquals(intOne[2], intTwo[2]);
		assertNotSame(intOne, intTwo);
    }
	
	@Test
	public void testComplexObjectsUsingDeepCopyMethod(){
		ComplexClass q1 = new ComplexClass();
		//q1.printID();
		ComplexClass w1 = CopyUtils.deepCopy(q1);
		//w1.printID();
		
		assertEquals(q1.toString(), w1.toString());
		assertEquals(q1.getChild().printID(), w1.getChild().printID());
		assertNotSame(q1, w1);
		assertNotSame(q1.getChild(), w1.getChild());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testCrashNullPointerUsingDeepCopyMethod(){
		boolean crashNullPointerHappen = false;
		String test;
		try{
			test = CopyUtils.deepCopy(null);
		}catch(NullPointerException npe){
			crashNullPointerHappen = true;
		}
		assertFalse(crashNullPointerHappen);
	}
	
	
		
	@Test
	public void testCrashInvocationTargetExceptionUsingDeepCopyMethod(){
		//Ýêñåïøí ëîâèòñÿ â DeepCopy è íå ëîâèòñÿ òóò, íî ñàì ìåòîä êðàøèòñÿ
		boolean crashInvocationTargetException = false;
		byte[] ba = {15, 25, 33, 44};
		ByteArrayInputStream test = new ByteArrayInputStream(ba);
		ByteArrayInputStream finTest;
		finTest = CopyUtils.deepCopy(test);
		assertFalse(crashInvocationTargetException);
	}
	
	@Test
	public void testByteArrayInputStreamUsingDeepCopyMethod(){
		byte[] ba = {15, 25, 33, 44};
		ByteArrayInputStream test = new ByteArrayInputStream(ba);
		ByteArrayInputStream finTest;
		finTest = CopyUtils.deepCopy(test);
		int c, d;
		while(((c = finTest.read()) != -1) && ((d = test.read()) != 01)){
			assertEquals(c, d);
		}
		assertNotSame(test, finTest);
	}

	
	

}
