package com.disablecamerashuttersoundexample;

import java.lang.reflect.Field;

public class ReflectionUtil {
	public static int getIntFieldIfExists(Class<?> klass, String fieldName,
			Class<?> obj, int defaultVal) {
		try {
			Field f = klass.getDeclaredField(fieldName);
			return f.getInt(obj);
		} catch (Exception e) {
			return defaultVal;
		}
	}
	
	public static boolean getBooleanFieldIfExists(Class<?> klass, String fieldName,
			Class<?> obj, boolean defaultVal) {
		try {
			Field f = klass.getDeclaredField(fieldName);
			return f.getBoolean(obj);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	public static boolean hasField(Class<?> klass, String fieldName) {
		try {
			klass.getDeclaredField(fieldName);
			return true;
		} catch (NoSuchFieldException e) {
			return false;
		}
	}

	public static boolean hasMethod(String className, String methodName,
			Class<?>... parameterTypes) {
		try {
			Class<?> klass = Class.forName(className);
			klass.getDeclaredMethod(methodName, parameterTypes);
			return true;
		} catch (Throwable th) {
			return false;
		}
	}

	public static boolean hasMethod(Class<?> klass, String methodName,
			Class<?>... paramTypes) {
		try {
			klass.getDeclaredMethod(methodName, paramTypes);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
}
