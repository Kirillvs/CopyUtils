package com.archipov.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class CopyUtils {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>T deepCopy(T obj){
		if(obj == null){
			return null;
		}else if(obj instanceof String){
			return (T) new String((String) obj);
		}else if (obj instanceof Integer) {
			return (T) new Integer((Integer) obj);
		}else if (obj instanceof Double) {
			return (T) new Double((Double) obj);
		}else if (obj instanceof Byte) {
			return (T) new Byte((Byte) obj);
		}else if (obj instanceof Short) {
			return (T) new Short((Short) obj);
		}else if (obj instanceof Long) {
			return (T) new Long((Long) obj);
		}else if (obj instanceof Float) {
			return (T) new Float((Float) obj);
		}else if (obj instanceof Character) {
			return (T) new Character((Character) obj);
		}else if (obj instanceof Boolean) {
			return (T) new Boolean((Boolean) obj);
		}else if (obj instanceof ArrayList<?>) {
			return (T) arrayListHandler((ArrayList<?>) obj);
		}else if (obj instanceof HashMap<?, ?>){
			return (T) mapHandler((Map<?, ?>) obj);
		}else if (obj instanceof ConcurrentHashMap<?, ?>){
			return (T) concurrentMapHandler((Map<?, ?>) obj);
		}else if (obj instanceof TreeMap<?, ?>){
			return (T) treeMapHandler((Map<?, ?>) obj);
		}else if (obj instanceof LinkedList<?>){
			return (T) linkedListHandler((LinkedList<?>) obj);
		}else if (obj instanceof HashSet<?>){
			return (T) hashSetHandler((HashSet<?>) obj);
		}else if (isPrimitiveArray(obj)){
			return getPrimitiveArray(obj);
		}
		
		T finObj = null;
		Class rezClass = obj.getClass();
		rezClass.cast(finObj);
		try {
			Constructor<T> constructor = getCompliteConstructor(rezClass);
			
			finObj = (T) constructor.newInstance(getParamsObjForConstructor(rezClass));
			copyFields(rezClass, obj, finObj);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {	
			//System.out.println(e.getCause());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return finObj;
	}
	
	//Ïðåäîòâðàùàåì áåñêîíå÷íóþ ðåêóðñèþ è StackOverFlowError
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T>T deepCopy(T obj, Object parrent){
		if(obj == null){
			return null;
		}else if(obj instanceof String){
			return (T) new String((String) obj);
		}else if (obj instanceof Integer) {
			return (T) new Integer((Integer) obj);
		}else if (obj instanceof Double) {
			return (T) new Double((Double) obj);
		}else if (obj instanceof Byte) {
			return (T) new Byte((Byte) obj);
		}else if (obj instanceof Short) {
			return (T) new Short((Short) obj);
		}else if (obj instanceof Long) {
			return (T) new Long((Long) obj);
		}else if (obj instanceof Float) {
			return (T) new Float((Float) obj);
		}else if (obj instanceof Character) {
			return (T) new Character((Character) obj);
		}else if (obj instanceof Boolean) {
			return (T) new Boolean((Boolean) obj);
		}else if (obj instanceof ArrayList<?>) {
			return (T) arrayListHandler((ArrayList<?>) obj);
		}else if (obj instanceof HashMap<?, ?>){
			return (T) mapHandler((Map<?, ?>) obj);
		}else if (obj instanceof ConcurrentHashMap<?, ?>){
			return (T) concurrentMapHandler((Map<?, ?>) obj);
		}else if (obj instanceof TreeMap<?, ?>){
			return (T) treeMapHandler((Map<?, ?>) obj);
		}else if (obj instanceof LinkedList<?>){
			return (T) linkedListHandler((LinkedList<?>) obj);
		}else if (obj instanceof HashSet<?>){
			return (T) hashSetHandler((HashSet<?>) obj);
		}else if (isPrimitiveArray(obj)){
			return getPrimitiveArray(obj);
		}
		
		T finObj = null;
		Class rezClass = obj.getClass();
		rezClass.cast(finObj);
		try {
			Constructor<T> constructor = getCompliteConstructor(rezClass);
			
			finObj = (T) constructor.newInstance(getParamsObjForConstructor(rezClass));
			copyFields(rezClass, obj, finObj, parrent);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return finObj;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ArrayList<?> arrayListHandler(ArrayList<?> obj) {
		ArrayList srcList = obj;
		ArrayList finList = new ArrayList();
		for(int i = 0; i < srcList.size(); i ++){
			finList.add(CopyUtils.deepCopy(srcList.get(i)));
		}
		return finList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <K, V> Map<K, V> mapHandler(Map <K, V> obj){
		Map<K, V> src = obj;
		Map<K, V> fin = new HashMap<K, V>();
		for(Map.Entry entry:src.entrySet()){
			K key = (K) CopyUtils.deepCopy(entry.getKey());
			V value = (V) CopyUtils.deepCopy(entry.getValue());
			//System.out.println(key + " - " + value);
			fin.put(key, value);
		}		
		return fin;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <K, V> Map<K, V> concurrentMapHandler(Map<K, V> obj){
		Map<K, V> src = obj;
		Map<K, V> fin = new ConcurrentHashMap<K, V>();
		for(Map.Entry entry:src.entrySet()){
			K key = (K) CopyUtils.deepCopy(entry.getKey());
			V value = (V) CopyUtils.deepCopy(entry.getValue());
			//System.out.println(key + " - " + value);
			fin.put(key, value);
		}		
		return fin;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <K, V> Map<K, V> treeMapHandler(Map<K, V> obj){
		Map<K, V> src = obj;
		Map<K, V> fin = new TreeMap<K, V>();
		for(Map.Entry entry:src.entrySet()){
			K key = (K) CopyUtils.deepCopy(entry.getKey());
			V value = (V) CopyUtils.deepCopy(entry.getValue());
			//System.out.println(key + " - " + value);
			fin.put(key, value);
		}		
		return fin;	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static LinkedList<?> linkedListHandler(LinkedList<?> obj) {
		LinkedList srcList = obj;
		LinkedList finList = new LinkedList<>();
		for(int i = 0; i < srcList.size(); i ++){
			finList.add(CopyUtils.deepCopy(srcList.get(i)));
		}
		return finList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static HashSet<?> hashSetHandler(HashSet<?> obj) {
		HashSet srcList = obj;
		HashSet finList = new HashSet<>();
		for(Object o : srcList){
			finList.add(CopyUtils.deepCopy(o));
		}
		return finList;
	}
	
	
	private static boolean isPrimitiveArray(Object obj){
		if(obj instanceof byte[] ||
			obj instanceof short[] ||
			obj instanceof int[] ||
			obj instanceof long[] ||
			obj instanceof float[] ||
			obj instanceof double[] ||
			obj instanceof char[] ||
			obj instanceof boolean[]){
			return true;
		}else{
			return false;
		}
	}
	
	//Ïåðåãðóæåííûé ìåòîä äëÿ ïðèìèòèâîâ êîíñòðóêòîðà
	private static boolean isPrimitiveArray(String type){
		if(type.equals("byte[]") ||
				type.equals("short[]") ||
				type.equals("int[]") ||
				type.equals("long[]") ||
				type.equals("float[]") ||
				type.equals("double[]") ||
				type.equals("char[]") ||
				type.equals("boolean[]")){
			return true;
		}else{
			return false;
		}
	}

	
	@SuppressWarnings("unchecked")
	private static <T>T getPrimitiveArray(T obj){
		if(obj instanceof int[]){
			int[] arr = new int[((int[]) obj).length];
			for(int i = 0; i < ((int[]) obj).length; i++){
				arr[i] = ((int[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof byte[]){
			byte[] arr = new byte[((byte[]) obj).length];
			for(int i = 0; i < ((byte[]) obj).length; i++){
				arr[i] = ((byte[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof short[]){
			short[] arr = new short[((short[]) obj).length];
			for(int i = 0; i < ((short[]) obj).length; i++){
				arr[i] = ((short[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof long[]){
			long[] arr = new long[((long[]) obj).length];
			for(int i = 0; i < ((long[]) obj).length; i++){
				arr[i] = ((long[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof float[]){
			float[] arr = new float[((float[]) obj).length];
			for(int i = 0; i < ((float[]) obj).length; i++){
				arr[i] = ((float[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof double[]){
			double[] arr = new double[((double[]) obj).length];
			for(int i = 0; i < ((double[]) obj).length; i++){
				arr[i] = ((double[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof char[]){
			char[] arr = new char[((char[]) obj).length];
			for(int i = 0; i < ((char[]) obj).length; i++){
				arr[i] = ((char[]) obj)[i];
			}
			return (T) arr;
		}else if(obj instanceof boolean[]){
			boolean[] arr = new boolean[((boolean[]) obj).length];
			for(int i = 0; i < ((boolean[]) obj).length; i++){
				arr[i] = ((boolean[]) obj)[i];
			}
			return (T) arr;
		}
		return null;
	}
	
	//Ïåðåãðóæåííûé ìåòîä äëÿ êîíñòðóêòîðà
	@SuppressWarnings("unchecked")
	private static <T>T getPrimitiveArray(T obj, String type){
		if(type.equals("int[]")){
			int[] arr = new int[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("byte[]")){
			byte[] arr = new byte[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("short[]")){
			short[] arr = new short[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("long[]")){
			long[] arr = new long[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("float[]")){
			float[] arr = new float[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("double[]")){
			double[] arr = new double[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("char[]")){
			char[] arr = new char[1];
			arr[0] = 0;
			return (T) arr;
		}else if(type.equals("boolean[]")){
			boolean[] arr = new boolean[1];
			arr[0] = false;
			return (T) arr;
		}
		return null;
	}

 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Constructor getCompliteConstructor(Class ourClass) throws NoSuchMethodException, SecurityException{
		Constructor constructor = null;
		Class[] params = new Class[ourClass.getDeclaredConstructors()[0].getParameterTypes().length];
		for(int i = 0; i < ourClass.getDeclaredConstructors()[0].getParameterTypes().length; i++){
			params[i] = ourClass.getDeclaredConstructors()[0].getParameterTypes()[i];
			//System.out.println(i + " -> " + params[i]);
		}
		constructor = ourClass.getConstructor(params);
		constructor.setAccessible(true);
		return constructor;		
	}
	
	@SuppressWarnings("rawtypes")
	private static Object[] getParamsObjForConstructor(Class ourClass) throws NoSuchMethodException, SecurityException{
		Constructor constuctor = null;
		constuctor = ourClass.getDeclaredConstructors()[0];
		constuctor.setAccessible(true);
		//constuctor = ourClass.getDeclaredConstructor(null);
		Object[] objParams = new Object[constuctor.getParameterTypes().length];
		for(int i = 0; i < constuctor.getParameterTypes().length; i++){
			//System.out.println(constuctor.getParameterTypes()[i].toString());
			//System.out.println(isPrimitiveArray(constuctor.getParameterTypes()[i].getCanonicalName()));
			//System.err.println(constuctor.getParameterTypes()[i].getCanonicalName());

			if(constuctor.getParameterTypes()[i].toString().equalsIgnoreCase("int") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("double") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("float") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("byte") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("char") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("long")){
				objParams[i] = 0;
			}else if(constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("boolean")){
				objParams[i] = false;
			}else if(isPrimitiveArray(constuctor.getParameterTypes()[i].getCanonicalName())){
				//System.out.println("111111111111111111");
				//objParams[i] = new byte[0];
				objParams[i] = getPrimitiveArray(constuctor.getParameterTypes()[i], constuctor.getParameterTypes()[i].getCanonicalName());
			}else{
				objParams[i] = null;
			}
		}
		return objParams;
	}
	
	@SuppressWarnings("rawtypes")
	private static <T> void copyFields(Class ourClass, T srcObj, T finObj) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Field[] fields = ourClass.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			fields[i].setAccessible(true);
			Field modField = Field.class.getDeclaredField("modifiers");
			modField.setAccessible(true);
			modField.setInt(fields[i], fields[i].getModifiers() & ~Modifier.FINAL);
			if(fields[i].getType().toString().equalsIgnoreCase("int") ||
					fields[i].getType().toString().equalsIgnoreCase("double") ||
					fields[i].getType().toString().equalsIgnoreCase("float") ||
					fields[i].getType().toString().equalsIgnoreCase("byte") ||
					fields[i].getType().toString().equalsIgnoreCase("char") || 
					fields[i].getType().toString().equalsIgnoreCase("boolean") ||
					fields[i].getType().toString().equalsIgnoreCase("short") ||
					fields[i].getType().toString().equalsIgnoreCase("long")){
				fields[i].set(finObj, fields[i].get(srcObj));
			}else{
				//System.out.println(fields[i].getType().toString() + "------>>>>>>>>> " + fields[i].get(srcObj));
				//fields[i].set(finObj, fields[i].get(srcObj));
				fields[i].set(finObj, CopyUtils.deepCopy(fields[i].get(srcObj), finObj));
			}
		}
	}
	
	//Ïåðåãðóæåííûé ìåòîä äëÿ ïðåäîòâðàùåíèÿ áåñêîíå÷íîé ðåêóðñèè
	@SuppressWarnings("rawtypes")
	private static <T> void copyFields(Class ourClass, T srcObj, T finObj, Object parrent) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Field[] fields = ourClass.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			fields[i].setAccessible(true);
			Field modField = Field.class.getDeclaredField("modifiers");
			modField.setAccessible(true);
			modField.setInt(fields[i], fields[i].getModifiers() & ~Modifier.FINAL);
			if(fields[i].getType().toString().equalsIgnoreCase("int") ||
					fields[i].getType().toString().equalsIgnoreCase("double") ||
					fields[i].getType().toString().equalsIgnoreCase("float") ||
					fields[i].getType().toString().equalsIgnoreCase("byte") ||
					fields[i].getType().toString().equalsIgnoreCase("char") || 
					fields[i].getType().toString().equalsIgnoreCase("boolean") ||
					fields[i].getType().toString().equalsIgnoreCase("short") ||
					fields[i].getType().toString().equalsIgnoreCase("long")){
				fields[i].set(finObj, fields[i].get(srcObj));
			}else{
				//System.out.println(fields[i].getType().toString() + "------>>>>>>>>> " + fields[i].get(srcObj));
				//fields[i].set(finObj, fields[i].get(srcObj));
				if(fields[i].get(srcObj).toString().equals(parrent.toString())){
					//System.out.println(fields[i].get(srcObj) + "------>>>>>>>>> " + parrent);
					fields[i].set(finObj, fields[i].get(srcObj));
				}else{
					fields[i].set(finObj, CopyUtils.deepCopy(fields[i].get(srcObj), finObj));
				}
			}
		}
	}

	
	
	//Âçÿòî ñî Stackoverflow http://stackoverflow.com/questions/3301635/change-private-static-final-field-using-java-reflection
	static void setFinalStaticField(Field field, Object newValue) throws Exception {
		field.setAccessible(true);		
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		field.set(null, newValue);		
	}
}
