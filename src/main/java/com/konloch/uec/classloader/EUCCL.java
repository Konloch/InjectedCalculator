package com.konloch.uec.classloader;

/**
 * @author Konloch
 * @since 10/15/2023
 */
public class EUCCL extends ClassLoader
{
	public Class<?> defineClass(String name, byte[] bytecode)
	{
		return defineClass(name, bytecode, 0, bytecode.length);
	}
}