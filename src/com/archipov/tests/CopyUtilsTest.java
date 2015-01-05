package com.archipov.tests;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.archipov.utils.CopyUtils;
import com.archipov.utils.TestClass;

public class CopyUtilsTest {
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testDeepCopy() {
		
		//1. �������� ����������� �������� ������������ ��������� ������
		TestClass srcTestClass = new TestClass(15, "src", false);
		TestClass finTestClass = CopyUtils.deepCopy(srcTestClass);
		assertEquals(srcTestClass.printData(), finTestClass.printData());
		assertThat(srcTestClass, not(finTestClass));		
		finTestClass.modification();		
		assertNotEquals(srcTestClass.printData(), finTestClass.printData());
		
		//2. �������� ����������� �������� Integer
		Integer firstInteger = 5;
		Integer secondInteger = CopyUtils.deepCopy(firstInteger);
		assertEquals(firstInteger, secondInteger);
		assertNotSame(firstInteger, secondInteger);
		secondInteger = secondInteger + 5;
		assertNotEquals(firstInteger, secondInteger);
		
		//3 �������� ����������� �������� String
		String firstString = "FIRST!!!!";
		String secondString = CopyUtils.deepCopy(firstString);
		assertEquals(firstString, secondString);
		assertNotSame(firstString, secondString);
		secondString = "Second";
		assertNotEquals(firstString, secondString);
		
		//4. �������� ����������� �������� ArrayList
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




		
    }

}
