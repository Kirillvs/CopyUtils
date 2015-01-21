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
		if(obj instanceof String){
			return (T) new String((String) obj);
		}else if (obj instanceof Integer) {
			return (T) new Integer((Integer) obj);
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
	
	//ѕредотвращаем бесконечную рекурсию и StackOverFlowError
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T>T deepCopy(T obj, Object parrent){
		if(obj instanceof String){
			return (T) new String((String) obj);
		}else if (obj instanceof Integer) {
			return (T) new Integer((Integer) obj);
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


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Constructor getCompliteConstructor(Class ourClass) throws NoSuchMethodException, SecurityException{
		Constructor constructor = null;
		Class[] params = new Class[ourClass.getConstructors()[0].getParameterTypes().length];
		for(int i = 0; i < ourClass.getConstructors()[0].getParameterTypes().length; i++){
			params[i] = ourClass.getConstructors()[0].getParameterTypes()[i];
			//System.out.println(i + " -> " + params[i]);
		}
		constructor = ourClass.getConstructor(params);
		constructor.setAccessible(true);
		return constructor;		
	}
	
	@SuppressWarnings("rawtypes")
	private static Object[] getParamsObjForConstructor(Class ourClass) throws NoSuchMethodException, SecurityException{
		Constructor constuctor = null;
		constuctor = ourClass.getConstructors()[0];
		constuctor.setAccessible(true);
		//constuctor = ourClass.getDeclaredConstructor(null);
		Object[] objParams = new Object[constuctor.getParameterTypes().length];
		for(int i = 0; i < constuctor.getParameterTypes().length; i++){
			//System.out.println(constuctor.getParameterTypes()[i].toString());
			if(constuctor.getParameterTypes()[i].toString().equalsIgnoreCase("int") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("double") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("float") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("byte") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("char") ||
					constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("long")){
				objParams[i] = 0;
			}else if(constuctor.getParameterTypes()[i].toString().toString().equalsIgnoreCase("boolean")){
				objParams[i] = false;
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
					//fields[i].getType().toString().equalsIgnoreCase("class java.lang.String") ||
					//fields[i].getType().toString().equalsIgnoreCase("class java.lang.Integer") ||
					fields[i].getType().toString().equalsIgnoreCase("class java.lang.Class") ||
					fields[i].getType().toString().equalsIgnoreCase("long")){
				fields[i].set(finObj, fields[i].get(srcObj));
			}else{
				//System.out.println(fields[i].getType().toString() + "------>>>>>>>>> " + fields[i].get(srcObj));
				//fields[i].set(finObj, fields[i].get(srcObj));
				fields[i].set(finObj, CopyUtils.deepCopy(fields[i].get(srcObj), finObj));
			}
		}
	}
	
	//ѕерегруженный метод дл€ предотвращени€ бесконечной рекурсии
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
					//fields[i].getType().toString().equalsIgnoreCase("class java.lang.String") ||
					//fields[i].getType().toString().equalsIgnoreCase("class java.lang.Integer") ||
					fields[i].getType().toString().equalsIgnoreCase("class java.lang.Class") ||
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

	
	
	//¬з€то со Stackoverflow http://stackoverflow.com/questions/3301635/change-private-static-final-field-using-java-reflection
	static void setFinalStaticField(Field field, Object newValue) throws Exception {
		field.setAccessible(true);		
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		field.set(null, newValue);		
	}
}
