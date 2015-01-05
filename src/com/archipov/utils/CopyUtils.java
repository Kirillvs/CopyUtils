package com.archipov.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class CopyUtils {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>T deepCopy(T obj){
		if(obj instanceof String){
			return (T) new String((String) obj);
		}else if (obj instanceof ArrayList<?>) {
			return (T) arrayListHandler((ArrayList<?>) obj);
		}
		
		T finObj = null;
		Class rezClass = obj.getClass();
		rezClass.cast(finObj);
		try {
			Constructor<T> constructor = getCompliteConstructor(rezClass);
			//Object[] args = {5, "five", false};
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finObj;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static ArrayList<?> arrayListHandler(ArrayList<?> obj) {
		ArrayList srcList = obj;
		ArrayList desList = new ArrayList();
		for(int i = 0; i < srcList.size(); i ++){
			desList.add(CopyUtils.deepCopy(srcList.get(i)));
		}
		return desList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Constructor getCompliteConstructor(Class ourClass) throws NoSuchMethodException, SecurityException{
		Constructor constructor = null;
		Class[] params = new Class[ourClass.getConstructors()[0].getParameterCount()];
		for(int i = 0; i < ourClass.getConstructors()[0].getParameterCount(); i++){
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
		Object[] objParams = new Object[constuctor.getParameterCount()];
		for(int i = 0; i < constuctor.getParameterCount(); i++){
			if(constuctor.getParameters()[i].getType().toString().equals("int") ||
					constuctor.getParameters()[i].getType().toString().equals("double") ||
					constuctor.getParameters()[i].getType().toString().equals("float") ||
					constuctor.getParameters()[i].getType().toString().equals("byte") ||
					constuctor.getParameters()[i].getType().toString().equals("char") || 
					constuctor.getParameters()[i].getType().toString().equals("long")){
				objParams[i] = 0;
			}else if(constuctor.getParameters()[i].getType().toString().equals("boolean")){
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
			//System.out.println("------>>>>>>>>> " + modField.getName() + "___" );
			modField.setAccessible(true);
			modField.setInt(fields[i], fields[i].getModifiers() & ~Modifier.FINAL);
			fields[i].set(finObj, fields[i].get(srcObj));
		}
	}
	
	
	//Взято со Stackoverflow http://stackoverflow.com/questions/3301635/change-private-static-final-field-using-java-reflection
	static void setFinalStaticField(Field field, Object newValue) throws Exception {
		field.setAccessible(true);		
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		field.set(null, newValue);
	}
}
