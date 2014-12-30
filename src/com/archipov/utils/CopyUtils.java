package com.archipov.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CopyUtils {
	
	
	public static <T>T deepCopy(T obj){
		
		T finObj = null;
		Class rezClass = obj.getClass();
		rezClass.cast(finObj);
		try {
			Constructor<T> constructor = getCompliteConstructor(rezClass);
			//getParamsObjForConstructor(rezClass);
			System.out.println("|\\-/| " + rezClass.getDeclaredField("second"));
			Object[] args = {5, "five", false};
			
			finObj = (T) constructor.newInstance(getParamsObjForConstructor(rezClass));

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
	
	private static Constructor getCompliteConstructor(Class ourClass) throws NoSuchMethodException, SecurityException{
		Constructor constructor = null;
		Class[] params = new Class[ourClass.getConstructors()[0].getParameterCount()];
		for(int i = 0; i < ourClass.getConstructors()[0].getParameterCount(); i++){
			params[i] = ourClass.getConstructors()[0].getParameterTypes()[i];
			System.out.println(i + " -> " + params[i]);
		}
		constructor = ourClass.getConstructor(params);
		constructor.setAccessible(true);
		return constructor;		
	}
	
	private static Object[] getParamsObjForConstructor(Class ourClass){
		Object[] objParams = new Object[ourClass.getConstructors()[0].getParameterCount()];
		for(int i = 0; i < ourClass.getConstructors()[0].getParameterCount(); i++){
			System.out.println("------>>>>>>>>> " + ourClass.getConstructors()[0].getParameters()[i].getType());
			if(ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("int") ||
					ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("double") ||
					ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("float") ||
					ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("byte") ||
					ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("char") || 
					ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("long")){
				objParams[i] = 0;
			}else if(ourClass.getConstructors()[0].getParameters()[i].getType().toString().equals("boolean")){
				objParams[i] = false;
			}else{
				objParams[i] = null;
			}
		}
		return objParams;
	}
	
	private static <T> void copyFields(Class ourClass, T srcObj, T finObj){
		
	}
}
